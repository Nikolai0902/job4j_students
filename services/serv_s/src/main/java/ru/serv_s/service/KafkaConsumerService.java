package ru.serv_s.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import ru.serv_s.domain.Student;
import ru.serv_s.exception.ServiceException;
import ru.serv_s.mapper.StudentToXML;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaConsumerService {

    public final StudentsService soapService;
    private final StudentToXML mapperToXML;

    @KafkaListener(topics = "requests-all", groupId = "ALL")
    @SendTo
    public String allStudents() {
        log.info("Веб-сервис S получил запрос");
        var student = soapService.findAll();
        log.info("Веб-сервис S передал ответ брокер очередей сообщений");
        return mapperToXML.getAllStudentsDtoXml(student);
    }

    @KafkaListener(topics = "requests-one", groupId = "ONE")
    @SendTo
    public String oneStudents(String string) throws ServiceException {
        log.info("Веб-сервис S получил запрос");
        Student student;
        try {
            student = soapService.findStudentsCard(Integer.parseInt(string));
        } catch (Exception e) {
            throw new ServiceException("Students not found", e);
        }
        log.info("Веб-сервис S передал ответ брокер очередей сообщений");
        return mapperToXML.getStudentsDtoXml(student);
    }
}
