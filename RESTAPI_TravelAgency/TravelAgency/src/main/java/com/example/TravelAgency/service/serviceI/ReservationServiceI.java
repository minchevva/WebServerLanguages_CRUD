package com.example.TravelAgency.service.serviceI;

import com.example.TravelAgency.dto.*;

import java.util.List;


public interface ReservationServiceI {
    ResponseReservationDTO createReservation(CreateReservationDTO createReservationDTO);

    void deleteReservationById(Long id);

    List<ResponseReservationDTO> getAllReservation();

    ResponseReservationDTO getReservationById(Long id);

    ResponseReservationDTO updateReservation(UpdateReservationDTO updateReservationDTO);
}
