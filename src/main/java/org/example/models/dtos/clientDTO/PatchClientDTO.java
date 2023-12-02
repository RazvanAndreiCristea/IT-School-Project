package org.example.models.dtos.clientDTO;

import jakarta.validation.constraints.Email;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatchClientDTO {
    private int age;
    private String firstName;
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
}