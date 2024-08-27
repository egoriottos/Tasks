package org.example.crud.commands.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crud.entity.Order;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseWithInfo {
    private String name;
    private String email;
    private List<Order> orders;
}
