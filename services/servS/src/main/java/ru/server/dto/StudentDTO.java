package ru.server.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentDTO {

    private int cardNumber;
    private String faculty;
    private String surname;
    private String name;
    private String patronymic;
    private String photo;
}