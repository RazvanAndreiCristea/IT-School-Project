package org.example.models.dtos.clientDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.models.entities.ElectronicProduct;
import org.example.models.entities.Order;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseClientDTO {
    private Long id;
    private int age;
    private String firstName;
    private String lastName;
    private String email;
    private List<Order> orders;
    private Set<ElectronicProduct> electronicProducts;
}