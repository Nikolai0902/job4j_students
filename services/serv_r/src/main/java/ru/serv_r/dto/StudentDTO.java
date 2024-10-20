package ru.serv_r.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

    private int cardNumber;
    private String faculty;
    private String surname;
    private String name;
    private String patronymic;
    private String photo;
}
