package ru.rest.sevice;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rest.dto.PersonDto;
import ru.rest.exception.ServiceException;
import ru.rest.model.Person;
import ru.rest.repository.PersonRepository;
import java.util.Collections;

@Service
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonService(PersonRepository personRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Person save(PersonDto personDto) throws ServiceException {
        var person = new Person();
        try {
            person.setLogin(personDto.getLogin());
            person.setPassword(bCryptPasswordEncoder.encode(personDto.getPassword()));
            personRepository.save(person);
        } catch (Exception e) {
            throw new ServiceException("The person with this login already exists", e);
        }
        return person;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var person = personRepository.findByLogin(login);
        if (person == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(
                person.getLogin(),
                person.getPassword(),
                Collections.emptyList());
    }

        public Person findByLogin(String login) {
        return personRepository.findByLogin(login);
    }
}
