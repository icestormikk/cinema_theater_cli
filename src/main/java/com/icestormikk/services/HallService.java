package com.icestormikk.services;

import com.icestormikk.domain.cinema.Hall;

public interface HallService extends IService<Hall, Integer> {
    Hall getByCinemaIdAndNumber(Integer cinemaId, int hallNumber) throws Exception;
    HallService create(Integer cinemaId, int hallNumber, int seats) throws Exception;
}
