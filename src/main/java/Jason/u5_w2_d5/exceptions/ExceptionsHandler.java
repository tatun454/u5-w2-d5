package Jason.u5_w2_d5.exceptions;

import Jason.u5_w2_d5.payloads.errors.ErrorsPayload;
import Jason.u5_w2_d5.payloads.errors.ErrorsPayloadWithList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayloadWithList handleBadRequest(BadRequestException e) {
        // Converte BadRequestException in ErrorsPayloadWithList
        List<String> errorsMessages = new ArrayList<>();
        if (e.getErrorsList() != null)
            errorsMessages = e.getErrorsList().stream().map(err -> err.getDefaultMessage()).toList();
        return new ErrorsPayloadWithList(e.getMessage(), new Date(), errorsMessages);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayloadWithList handleValidation(MethodArgumentNotValidException e) {
        // Converte errori di validazione in ErrorsPayloadWithList
        List<String> errors = e.getBindingResult().getAllErrors().stream().map(org.springframework.validation.ObjectError::getDefaultMessage).toList();
        return new ErrorsPayloadWithList("Validation error", new Date(), errors);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotFoundException e) {
        // Converte NotFoundException in ErrorsPayload
        return new ErrorsPayload(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleGeneric(Exception e) {
        // Log dell'errore e risposta generica 500
        e.printStackTrace();
        return new ErrorsPayload("General Error. please wait...", LocalDateTime.now());
    }

}
