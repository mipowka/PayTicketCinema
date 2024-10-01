package org.example.payticketcinema.service;

import lombok.RequiredArgsConstructor;
import org.example.payticketcinema.model.entity.Ticket;
import org.example.payticketcinema.model.entity.User;
import org.example.payticketcinema.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public Ticket saveTicket(Ticket ticket, User user) {
        ticket.setLine(1);
        ticket.setSeat(2);
        ticket.setMovieName("Ирония судьбы");
        ticket.setPrice(new BigDecimal("100"));
        ticket.setMovieDate(LocalDate.of(2024, 12, 30));
        ticket.setOwner(user);
        ticket.setOwnerName(user.getUsername());
        return ticketRepository.save(ticket);
    }
}
