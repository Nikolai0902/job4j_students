package ru.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rest.exception.ControllerException;
import ru.rest.exception.ServiceException;
import ru.rest.dto.StudentReplyDTO;
import ru.rest.sevice.KafkaProducerService;

import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "students")
@AllArgsConstructor
@Slf4j
public class StudentsController {

    private final KafkaProducerService producerService;

    @Operation(
            summary = "Получение информации о всех студентах",
            description = "Отправляет запрос через брокера веб-серверу S,"
                    + " получает от него список всех студентов в БД"
    )
    @GetMapping("/allStudents")
    public ResponseEntity<?> allStudents() throws ControllerException {
        log.info("Пользователь отправил запрос Get-allStudents");
        List<StudentReplyDTO> list;
        try {
            list = producerService.allStudents();
        } catch (ServiceException e) {
            throw new ControllerException("can't get a list of Students", e);
        }
        log.info("Веб-сервис R отправляет результат метода oneStudents клиену в браузер");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение информации о студенте по номеру зачетки",
            description = "Отправляет запрос через брокера веб-серверу S,"
                    + " получает от него информацию о сущносте в БД"
    )
    @GetMapping("/oneStudents/{cardNumber}")
    public ResponseEntity<?> oneStudents(@PathVariable int cardNumber) throws ControllerException {
        log.info("Пользователь отправил запрос Get-oneStudents");
        StudentReplyDTO student = null;
        try {
            student = producerService.oneStudents(cardNumber);
        } catch (ServiceException e) {
            throw new ControllerException("can't get of Student", e);
        }
        log.info("Веб-сервис R отправляет результат метода oneStudents клиену в браузер");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
