package ru.server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.server.domain.Student;

import java.util.List;

public interface StudentsRepository extends CrudRepository<Student, Integer> {

    List<Student> findAll();

    Student findByCardNumber(int cardNumber);

}
