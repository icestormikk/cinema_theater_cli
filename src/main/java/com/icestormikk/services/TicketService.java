package com.icestormikk.services;

import com.icestormikk.domain.cinema.Ticket;

public interface TicketService extends IService<Ticket, Integer> {
    Ticket getBySessionIdAndSeat(Integer sessionId, int seat);
    TicketService create(Integer sessionId, int seat) throws Exception;
}
