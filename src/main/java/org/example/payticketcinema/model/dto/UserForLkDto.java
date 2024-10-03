package org.example.payticketcinema.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.payticketcinema.model.entity.Ticket;
import org.example.payticketcinema.model.enums.Role;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserForLkDto {

    private String username;
    private String phone;
    private String city;
    private BigDecimal balance;
    private Role role = Role.USER;
    private List<Ticket> tickets = new ArrayList<>();
}
