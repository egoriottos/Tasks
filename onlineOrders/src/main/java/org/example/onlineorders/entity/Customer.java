package org.example.onlineorders.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Min(2)
    @Max(30)
    private String firstname;
    @Column(nullable = false)
    @Min(2)
    @Max(30)
    private String lastname;
    @Email
    private String email;
    @Size(min = 11, max = 12)
    private String phone;
}
