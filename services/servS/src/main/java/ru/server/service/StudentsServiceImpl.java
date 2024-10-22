package ru.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.server.domain.Student;
import ru.server.repository.StudentsRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentsServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;

    @Override
    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    @Override
    public Student findStudentsCard(int cardNumber) {
        return studentsRepository.findByCardNumber(cardNumber);
    }
}
