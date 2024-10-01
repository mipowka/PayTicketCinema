package org.example.payticketcinema.service;

import lombok.RequiredArgsConstructor;
import org.example.payticketcinema.mapping.UserMapper;
import org.example.payticketcinema.model.dto.UserForLk;
import org.example.payticketcinema.model.entity.Ticket;
import org.example.payticketcinema.model.entity.User;
import org.example.payticketcinema.repository.TicketRepository;
import org.example.payticketcinema.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final UserMapper userMapper;

    public User saveUser(User user) {
        Ticket ticket = new Ticket();
        ticket.setOwner(user);
        ticket.setOwnerName(user.getUsername());
        ticket.setLine(1);
        ticket.setSeat(2);
        ticket.setMovieName("Ирония судьбы");
        ticket.setPrice(new BigDecimal("100"));
        ticket.setMovieDate(LocalDate.of(2024, 12, 30));
        ticketRepository.save(ticket);
        return userRepository.save(user);
    }

    public boolean loginUser(User userFromForm) {
        var userByName = userRepository.findByUsername(userFromForm.getUsername());
        if (userByName == null) {
            return false;
        }
        if (!(userByName.getPassword().equals(userFromForm.getPassword()) &&
                userByName.getUsername().equals(userFromForm.getUsername())
        )) {
            return false;
        }

        return true;
    }

    public UserForLk getUserByUsername(String username) {
        User byUsername = userRepository.findByUsername(username);
        UserForLk userForLk = userMapper.toDto(byUsername);

        return userForLk;
    }

}
