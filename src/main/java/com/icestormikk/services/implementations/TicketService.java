package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Ticket;
import com.icestormikk.domain.cinema.TicketStatus;
import com.icestormikk.repositories.implementations.TicketRepository;

import java.util.Set;

public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public static TicketService create(TicketService service, Integer sessionId, int seat) throws Exception {
        Ticket ticket = new Ticket(null, sessionId, seat, TicketStatus.Booked);
        return new TicketService(TicketRepository.save(service.ticketRepository, ticket));
    }

    public static Set<Ticket> getAll(TicketService service) {
        return TicketRepository.findAll(service.ticketRepository);
    }

    public static Ticket getById(TicketService service, int id) {
        return TicketRepository.findById(service.ticketRepository, id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public static Ticket getBySessionIdAndSeat(TicketService service, Integer sessionId, int seat) {
        return TicketRepository.findAll(service.ticketRepository).stream().filter((ticket) -> ticket.getSessionId().equals(sessionId) && ticket.getSeat() == seat).findFirst().orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public static TicketService updateById(TicketService service, Integer id, Ticket entity) throws Exception {
        return new TicketService(TicketRepository.updateById(service.ticketRepository, id, entity));
    }

    public static TicketService deleteById(TicketService service, Integer id) throws Exception {
        return new TicketService(TicketRepository.deleteById(service.ticketRepository, id));
    }
}
