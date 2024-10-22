package ru.rest.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentReplyDTO {

    private int cardNumber;
    private String faculty;
    private String surname;
    private String name;
    private String patronymic;
    private byte[] photo;
}
