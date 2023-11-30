package org.example.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
public class Client {
    private long id;
    private int age;
    private String firstName;
    private String lastName;
    private String email;
}