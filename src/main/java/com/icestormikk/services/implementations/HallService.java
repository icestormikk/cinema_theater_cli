package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Hall;
import com.icestormikk.repositories.implementations.HallRepository;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;

public class HallService {
    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public static HallService create(HallService service, Integer cinemaId, int hallNumber, int seats) throws Exception {
        Hall hall = new Hall(null, cinemaId, hallNumber, seats, new StrictHashSet<>());
        return new HallService(HallRepository.save(service.hallRepository, hall));
    }

    public static StrictHashSet<Hall> getAll(HallService service) {
        return HallRepository.findAll(service.hallRepository);
    }

    public static Hall getById(HallService service, int id) {
        return HallRepository.findById(service.hallRepository, id).orElseThrow(() -> new RuntimeException("Hall not found"));
    }

    public static Hall getByCinemaIdAndNumber(HallService service, Integer cinemaId, int hallNumber) {
        return HallRepository.findAll(service.hallRepository).stream()
                .filter((hall) -> Objects.equals(hall.getCinemaId(), cinemaId) && hall.getHallNumber() == hallNumber)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Hall not found"));
    }

    public static HallService updateById(HallService service, Integer id, Hall hall) throws Exception {
        return new HallService(HallRepository.updateById(service.hallRepository, id, hall));
    }

    public static HallService deleteById(HallService service, Integer id) throws Exception {
        return new HallService(HallRepository.deleteById(service.hallRepository, id));
    }
}
