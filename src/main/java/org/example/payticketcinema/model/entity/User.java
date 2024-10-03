package org.example.payticketcinema.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.payticketcinema.model.enums.Role;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    private String phone;
    private String city;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    List<Ticket> tickets = new ArrayList<>();


}
