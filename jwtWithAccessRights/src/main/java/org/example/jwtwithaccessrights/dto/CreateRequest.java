package org.example.jwtwithaccessrights.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jwtwithaccessrights.entity.enums.Roles;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequest {
    private String username;
    private String password;
    private Roles role;
}
