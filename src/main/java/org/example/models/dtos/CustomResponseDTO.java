package org.example.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomResponseDTO {
    private Object responseObject;
    private String responseMessage;
}
