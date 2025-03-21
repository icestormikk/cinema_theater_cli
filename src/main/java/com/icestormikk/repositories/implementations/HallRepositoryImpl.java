package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Hall;
import com.icestormikk.repositories.HallRepository;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class HallRepositoryImpl implements HallRepository {
    private final StrictHashSet<Hall> halls;
    private final AtomicInteger nextId;

    public HallRepositoryImpl() {
        this.halls = new StrictHashSet<>();
        this.nextId = new AtomicInteger(0);
    }

    private HallRepositoryImpl(StrictHashSet<Hall> users, AtomicInteger nextId) {
        this.halls = users;
        this.nextId = nextId;
    }

    @Override
    public Set<Hall> findAll() {
        return Set.copyOf(halls);
    }

    @Override
    public Optional<Hall> findById(int id) {
        return halls.stream().filter((hall) -> hall.getId() == id).findFirst();
    }

    @Override
    public HallRepository save(Hall hall) throws Exception {
        Hall newHall = hall.withId(nextId.getAndIncrement());

        if(halls.contains(newHall))
            throw new Exception("Hall object with such data already exists");

        StrictHashSet<Hall> newHalls = new StrictHashSet<>(halls);
        newHalls.add(newHall);

        return new HallRepositoryImpl(newHalls, nextId);
    }

    @Override
    public HallRepository updateById(Integer id, Hall hall) throws Exception {
        Optional<Hall> oldHall = findById(id);

        if (oldHall.isEmpty())
            throw new Exception("Hall object with such data not found");

        Hall newHall = hall.withCinemaId(hall.getCinemaId()).withHallNumber(hall.getHallNumber()).withSeats(hall.getSeats())
                .withSessionIds(hall.getSessionIds());

        StrictHashSet<Hall> updatedHalls = new StrictHashSet<>();
        for (Hall c : halls)
            updatedHalls.add(Objects.equals(c.getId(), hall.getId()) ? newHall : c);

        return new HallRepositoryImpl(updatedHalls, nextId);
    }

    @Override
    public HallRepository deleteById(Integer id) throws Exception {
        Optional<Hall> hallOpt = findById(id);

        if (hallOpt.isEmpty())
            throw new Exception("Hall object with such id not found");

        StrictHashSet<Hall> updatedCinemas = new StrictHashSet<>();
        for (Hall u : halls)
            if(!Objects.equals(u.getId(), hallOpt.get().getId()))
                updatedCinemas.add(u);

        return new HallRepositoryImpl(updatedCinemas, nextId);
    }
}
