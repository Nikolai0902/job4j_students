package ru.serv_s.repository;

import org.springframework.data.repository.CrudRepository;
import ru.serv_s.domain.Student;

import java.util.List;

public interface StudentsRepository extends CrudRepository<Student, Integer> {

    List<Student> findAll();

    Student findByCardNumber(int cardNumber);

}
