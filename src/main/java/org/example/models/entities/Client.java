package org.example.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "age")
    private int age;
    @Column(name = "first_name")
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 characters")
    private String firstName;
    @Column(name = "last_name")
    @Size(min = 2, max = 20, message = "Last name should be between 2 and 20 characters")
    private String lastName;
    @Column(unique = true, name = "email")
    private String email;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Clients_Electronics",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "electronic_product_id"))
    private Set<ElectronicProduct> electronicProducts;

    public Client(int age, String firstName, String lastName, String email) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}