package ru.serv_r.repository;

import org.springframework.data.repository.CrudRepository;
import ru.serv_r.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    Person findByLogin(String login);
}
