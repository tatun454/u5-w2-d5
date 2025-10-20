package Jason.u5_w2_d5.controllers;

import Jason.u5_w2_d5.entities.Employee;
import Jason.u5_w2_d5.exceptions.BadRequestException;
import Jason.u5_w2_d5.payloads.employees.NewEmployeePayload;
import Jason.u5_w2_d5.payloads.employees.NewEmployeeResponseDTO;
import Jason.u5_w2_d5.service.EmployeeService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Cloudinary cloudinary;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewEmployeeResponseDTO createEmployee(@RequestBody @Valid NewEmployeePayload body) {
        Employee e = new Employee();
        e.setUsername(body.getUsername());
        e.setName(body.getName());
        e.setSurname(body.getSurname());
        e.setEmail(body.getEmail());
        Employee saved = employeeService.save(e);
        return new NewEmployeeResponseDTO(saved.getId());
    }

    @GetMapping("")
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee body) {
        return employeeService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.findByIdAndDelete(id);
    }


    @PostMapping(path = "/{id}/avatar", consumes = {"multipart/form-data"})
    public Employee uploadAvatar(@PathVariable Long id, @RequestPart("file") MultipartFile file) {
        // Verifica presenza file
        if (file == null || file.isEmpty()) {
            throw new BadRequestException("File mancante");
        }
        // Verifica tipo:(immagine)
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new BadRequestException("Tipo file non supportato: è richiesta un'immagine");
        }
        // Verifica dimensione massima 5MB
        long maxSize = 5L * 1024L * 1024L; // 5MB
        if (file.getSize() > maxSize) {
            throw new BadRequestException("File troppo grande. Massimo 5MB");
        }

        try {
            // Upload su Cloudinary e recupero URL
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            Object urlObj = uploadResult.get("secure_url");
            if (urlObj == null) {
                throw new BadRequestException("Upload fallito: nessuna URL restituita");
            }
            String url = urlObj.toString();
            Employee existing = employeeService.findById(id);
            existing.setAvatarUrl(url);
            return employeeService.save(existing);
        } catch (Exception e) {
            // Rappresenta errore di upload
            throw new BadRequestException("Impossibile caricare l'immagine: " + e.getMessage());
        }
    }
}
