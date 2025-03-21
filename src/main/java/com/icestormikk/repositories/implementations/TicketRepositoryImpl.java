package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Ticket;
import com.icestormikk.repositories.TicketRepository;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketRepositoryImpl implements TicketRepository {
    private final StrictHashSet<Ticket> tickets;
    private final AtomicInteger nextId;

    public TicketRepositoryImpl() {
        this.tickets = new StrictHashSet<>();
        this.nextId = new AtomicInteger(0);
    }

    private TicketRepositoryImpl(StrictHashSet<Ticket> tickets, AtomicInteger nextId) {
        this.tickets = tickets;
        this.nextId = nextId;
    }

    @Override
    public Set<Ticket> findAll() {
        return Set.copyOf(tickets);
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return tickets.stream().filter((ticket) -> ticket.getId() == id).findFirst();
    }

    @Override
    public TicketRepository save(Ticket ticket) throws Exception {
        Ticket newTicket = ticket.withId(nextId.getAndIncrement());

        if(tickets.contains(newTicket))
            throw new Exception("Ticket object with such data already exists");

        StrictHashSet<Ticket> newTickets = new StrictHashSet<>(tickets);
        newTickets.add(newTicket);

        return new TicketRepositoryImpl(newTickets, nextId);
    }

    @Override
    public TicketRepository updateById(Integer id, Ticket ticket) throws Exception {
        Optional<Ticket> oldTicket = findById(id);

        if (oldTicket.isEmpty())
            throw new Exception("Ticket object with such data not found");

        Ticket newTicket = oldTicket.get().withSeat(ticket.getSeat()).withSessionId(ticket.getSessionId()).withStatus(ticket.getStatus());

        StrictHashSet<Ticket> updatedTickets = new StrictHashSet<>();
        for (Ticket t : tickets)
            updatedTickets.add(Objects.equals(t.getId(), ticket.getId()) ? newTicket : t);

        return new TicketRepositoryImpl(updatedTickets, nextId);
    }

    @Override
    public TicketRepository deleteById(Integer id) throws Exception {
        Optional<Ticket> ticketOpt = findById(id);

        if (ticketOpt.isEmpty())
            throw new Exception("Ticket object with such id not found");

        StrictHashSet<Ticket> updatedTickets = new StrictHashSet<>();
        for (Ticket t : tickets)
            if(!Objects.equals(t.getId(), ticketOpt.get().getId()))
                updatedTickets.add(t);

        return new TicketRepositoryImpl(updatedTickets, nextId);
    }
}
