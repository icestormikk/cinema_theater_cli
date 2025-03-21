package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Ticket;
import com.icestormikk.domain.cinema.TicketStatus;
import com.icestormikk.repositories.TicketRepository;
import com.icestormikk.services.TicketService;

import java.util.Set;

public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public TicketService create(Integer sessionId, int seat) throws Exception {
        Ticket ticket = new Ticket(null, sessionId, seat, TicketStatus.Booked);
        TicketRepository updatedRepo = (TicketRepository) ticketRepository.save(ticket);
        return new TicketServiceImpl(updatedRepo);
    }

    @Override
    public Set<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getById(int id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public Ticket getBySessionIdAndSeat(Integer sessionId, int seat) {
        return ticketRepository.findAll().stream().filter((ticket) -> ticket.getSessionId().equals(sessionId) && ticket.getSeat() == seat).findFirst()
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public TicketService updateById(Integer id, Ticket entity) throws Exception {
        TicketRepository updatedRepo = (TicketRepository) ticketRepository.updateById(id, entity);
        return new TicketServiceImpl(updatedRepo);
    }

    @Override
    public TicketService deleteById(Integer id) throws Exception {
        TicketRepository updatedRepo = (TicketRepository) ticketRepository.deleteById(id);
        return new TicketServiceImpl(updatedRepo);
    }
}
