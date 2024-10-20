package ru.serv_r.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.serv_r.dto.PersonDto;
import ru.serv_r.exception.ControllerException;
import ru.serv_r.exception.ServiceException;
import ru.serv_r.sevice.PersonService;

@RestController
@Tag(name = "person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @Operation(
            summary = "Регистрация пользователя"
    )
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody PersonDto personDto) throws ControllerException {
        try {
            var result = this.personService.save(personDto);
            return ResponseEntity.ok(result);
        } catch (ServiceException e) {
            if (e.getCause() instanceof DataIntegrityViolationException) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            throw new ControllerException("can't add a person", e);
        }
    }
}
