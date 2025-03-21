package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Hall;
import com.icestormikk.repositories.HallRepository;
import com.icestormikk.services.HallService;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Set;

public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;

    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public HallService create(Integer cinemaId, int hallNumber, int seats) throws Exception {
        Hall hall = new Hall(null, cinemaId, hallNumber, seats, new StrictHashSet<>());
        HallRepository updatedRepo = (HallRepository) hallRepository.save(hall);
        return new HallServiceImpl(updatedRepo);
    }

    @Override
    public Set<Hall> getAll() {
        return hallRepository.findAll();
    }

    @Override
    public Hall getById(int id) {
        return hallRepository.findById(id).orElseThrow(() -> new RuntimeException("Hall not found"));
    }

    @Override
    public Hall getByCinemaIdAndNumber(Integer cinemaId, int hallNumber) {
        return hallRepository.findAll().stream()
                .filter((hall) -> Objects.equals(hall.getCinemaId(), cinemaId) && hall.getHallNumber() == hallNumber)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Hall not found"));
    }

    @Override
    public HallService updateById(Integer id, Hall hall) throws Exception {
        HallRepository updatedRepo = (HallRepository) hallRepository.updateById(id, hall);
        return new HallServiceImpl(updatedRepo);
    }

    @Override
    public HallService deleteById(Integer id) throws Exception {
        HallRepository updatedRepo = (HallRepository) hallRepository.deleteById(id);
        return new HallServiceImpl(updatedRepo);
    }
}
