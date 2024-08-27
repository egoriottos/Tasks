package org.example.crud.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crud.configuration.View;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    private Long id;
    @JsonView(View.Public.class)
    @NotNull
    @Size(min =2, max = 30)
    private String name;
    @JsonView(View.Public.class)
    @Pattern(regexp = ".*@.*",message = "wrong mail")
    @Column(unique = true, nullable = false)
    private String email;
    @OneToMany
    @JsonView(View.Public.class)
    private List<Order> orders;
    @JsonView(View.Public.class)
    private LocalDateTime createdAt;
    @JsonView(View.Public.class)
    private LocalDateTime updatedAt;
}
