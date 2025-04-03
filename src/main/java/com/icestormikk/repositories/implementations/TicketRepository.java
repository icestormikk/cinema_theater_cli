package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Ticket;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;

public class TicketRepository {
    private final StrictHashSet<Ticket> tickets;

    public TicketRepository() {
        this.tickets = new StrictHashSet<>();
    }

    private TicketRepository(StrictHashSet<Ticket> tickets) {
        this.tickets = tickets;
    }

    public static StrictHashSet<Ticket> findAll(TicketRepository repository) {
        return new StrictHashSet<>(repository.tickets);
    }

    public static Optional<Ticket> findById(TicketRepository repository, int id) {
        return repository.tickets.stream().filter((ticket) -> ticket.getId() == id).findFirst();
    }

    public static TicketRepository save(TicketRepository repository, Ticket ticket) throws Exception {
        StrictHashSet<Ticket> oldTickets = new StrictHashSet<>(repository.tickets);
        Ticket newTicket = ticket.withId(oldTickets.size() + 1);

        if(oldTickets.contains(newTicket))
            return new TicketRepository(oldTickets);

        return new TicketRepository(oldTickets.with(newTicket));
    }

    public static TicketRepository updateById(TicketRepository repository, Integer id, Ticket ticket) throws Exception {
        StrictHashSet<Ticket> oldTickets = new StrictHashSet<>(repository.tickets);
        Optional<Ticket> oldTicket = findById(repository, id);

        if (oldTicket.isEmpty())
            return new TicketRepository(oldTickets);

        Ticket newTicket = oldTicket.get().withSeat(ticket.getSeat()).withSessionId(ticket.getSessionId()).withStatus(ticket.getStatus());

        StrictHashSet<Ticket> updatedTickets = new StrictHashSet<>();
        oldTickets.stream().map(t -> Objects.equals(t.getId(), ticket.getId()) ? newTicket : t).forEach(updatedTickets::add);

        return new TicketRepository(updatedTickets);
    }

    public static TicketRepository deleteById(TicketRepository repository, Integer id) throws Exception {
        StrictHashSet<Ticket> oldTickets = new StrictHashSet<>(repository.tickets);
        Optional<Ticket> ticketOpt = findById(repository, id);

        return ticketOpt.map(ticket -> new TicketRepository(oldTickets.without(ticket))).orElseGet(() -> new TicketRepository(oldTickets));
    }
}
