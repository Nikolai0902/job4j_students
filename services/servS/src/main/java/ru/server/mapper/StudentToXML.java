package ru.server.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import ru.server.domain.Student;
import ru.server.dto.StudentDTO;
import ru.server.service.MinioServiceImpl;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class StudentToXML {

    public final MinioServiceImpl minioService;

    public String getStudentsDtoXml(Student students) {
        byte[] image = minioService.getFileDTO(String.valueOf(students.getCardNumber()));
        String base64String = Base64.encodeBase64String(image);
        StudentDTO studentsDTO = StudentDTO.builder()
                .cardNumber(students.getCardNumber())
                .faculty(students.getFaculty())
                .surname(students.getSurname())
                .name(students.getName())
                .surname(students.getSurname())
                .patronymic(students.getPatronymic())
                .photo(base64String)
                .build();
        String xmlStudents = "";
        try {
            xmlStudents = new ObjectMapper().writeValueAsString(studentsDTO);
        } catch (JsonProcessingException e) {
            log.error("Error serializing the Student object in xml: ", e);
        }
        return xmlStudents;
    }

    public String getAllStudentsDtoXml(List<Student> studentsList) {
        List<StudentDTO> studentsDTOS = studentsList.stream()
                .map(s -> StudentDTO.builder()
                        .cardNumber(s.getCardNumber())
                        .faculty(s.getFaculty())
                        .surname(s.getSurname())
                        .name(s.getName())
                        .surname(s.getSurname())
                        .patronymic(s.getPatronymic())
                        .photo(Base64.encodeBase64String(minioService.getFileDTO(s.getFaculty())))
                        .build()
                ).toList();
        String xmlListStudents = "";
        try {
            xmlListStudents = new ObjectMapper().writeValueAsString(studentsDTOS);
        } catch (JsonProcessingException e) {
            log.error("Error serializing the StudentList object in xml: ", e);
        }
        return xmlListStudents;
    }
}
