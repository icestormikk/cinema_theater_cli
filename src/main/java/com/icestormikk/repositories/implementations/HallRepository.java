package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Hall;
import com.icestormikk.utils.SafeHashSet;

import java.util.Objects;
import java.util.Optional;

public class HallRepository {
    private final SafeHashSet<Hall> halls;

    public HallRepository() {
        this.halls = new SafeHashSet<>();
    }

    private HallRepository(SafeHashSet<Hall> users) {
        this.halls = users;
    }

    public static SafeHashSet<Hall> findAll(HallRepository repository) {
        return new SafeHashSet<>(repository.halls);
    }

    public static Optional<Hall> findById(HallRepository repository, int id) {
        return repository.halls.stream().filter((hall) -> hall.getId() == id).findFirst();
    }

    public static HallRepository save(HallRepository repository, Hall hall) throws Exception {
        SafeHashSet<Hall> oldHalls = new SafeHashSet<>(repository.halls);
        Hall newHall = hall.withId(oldHalls.size() + 1);

        if(oldHalls.contains(newHall))
            return new HallRepository(oldHalls);

        return new HallRepository(SafeHashSet.with(oldHalls, newHall));
    }

    public static HallRepository updateById(HallRepository repository, Integer id, Hall hall) throws Exception {
        SafeHashSet<Hall> oldHalls = new SafeHashSet<>(repository.halls);
        Optional<Hall> oldHall = HallRepository.findById(repository, id);

        if (oldHall.isEmpty())
            return new HallRepository(repository.halls);

        Hall newHall = oldHall.get().withCinemaId(hall.getCinemaId()).withHallNumber(hall.getHallNumber()).withSeats(hall.getSeats())
                .withSessionIds(hall.getSessionIds());

        SafeHashSet<Hall> updatedHalls = new SafeHashSet<>();
        oldHalls.stream().map(c -> Objects.equals(c.getId(), hall.getId()) ? newHall : c).forEach(updatedHalls::add);

        return new HallRepository(updatedHalls);
    }

    public static HallRepository deleteById(HallRepository repository, Integer id) throws Exception {
        SafeHashSet<Hall> oldHalls = new SafeHashSet<>(repository.halls);
        Optional<Hall> hallOpt = HallRepository.findById(repository, id);

        return hallOpt.map(hall -> new HallRepository(SafeHashSet.without(oldHalls, hall))).orElseGet(() -> new HallRepository(oldHalls));
    }
}
