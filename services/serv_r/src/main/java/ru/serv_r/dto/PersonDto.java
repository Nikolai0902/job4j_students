package ru.serv_r.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private String login;
    private String password;
}
