package Jason.u5_w2_d5.service;

import Jason.u5_w2_d5.entities.Employee;
import Jason.u5_w2_d5.exceptions.NotFoundException;
import Jason.u5_w2_d5.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Cerca per id e se non trova lancia eccezione
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    // Salva/aggiorna dipendente
    public Employee save(Employee e) {
        return employeeRepository.save(e);
    }


    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // Aggiorna lo salva
    public Employee findByIdAndUpdate(Long id, Employee body) {
        Employee existing = findById(id);
        existing.setUsername(body.getUsername());
        existing.setName(body.getName());
        existing.setSurname(body.getSurname());
        existing.setEmail(body.getEmail());
        return employeeRepository.save(existing);
    }

    // Verifica esistenza e cancella  dipendente
    public void findByIdAndDelete(Long id) {
        Employee existing = findById(id);
        employeeRepository.delete(existing);
    }
}
