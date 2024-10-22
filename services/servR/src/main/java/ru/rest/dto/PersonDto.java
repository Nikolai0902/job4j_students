package ru.rest.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private String login;
    private String password;
}
