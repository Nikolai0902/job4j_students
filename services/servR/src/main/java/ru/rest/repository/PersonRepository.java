package ru.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.rest.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    Person findByLogin(String login);
}
