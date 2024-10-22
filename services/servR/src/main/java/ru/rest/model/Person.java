package ru.rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name = "person")
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
}
