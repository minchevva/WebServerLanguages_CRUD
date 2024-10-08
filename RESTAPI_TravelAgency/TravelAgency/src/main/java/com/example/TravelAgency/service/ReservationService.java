package com.example.TravelAgency.service;

import com.example.TravelAgency.dto.*;
import com.example.TravelAgency.model.Holiday;
import com.example.TravelAgency.model.Reservation;
import com.example.TravelAgency.repository.HolidayRepository;
import com.example.TravelAgency.repository.ReservationRepository;
import com.example.TravelAgency.service.serviceI.ReservationServiceI;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationServiceI {

    private final ReservationRepository reservationRepository;
    private final HolidayRepository holidayRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseReservationDTO createReservation(CreateReservationDTO createReservationDTO) {
        if (!holidayRepository.existsById(createReservationDTO.getHoliday())) {
            return null;
        }

        Reservation reservation = new Reservation();
        reservation.setContactName(createReservationDTO.getContactName());
        reservation.setPhoneNumber(createReservationDTO.getPhoneNumber());
        reservation.setHoliday(holidayRepository.findById(createReservationDTO.getHoliday()).get());

        reservation = reservationRepository.save(reservation);

        return new ResponseReservationDTO(
                reservation.getId(), reservation.getContactName(), reservation.getPhoneNumber(),
                new ResponseHolidayDTO(
                        reservation.getHoliday().getId(),
                        new ResponseLocationDTO(
                                reservation.getHoliday().getLocation().getId(),
                                reservation.getHoliday().getLocation().getStreet(),
                                reservation.getHoliday().getLocation().getNumber(),
                                reservation.getHoliday().getLocation().getCity(),
                                reservation.getHoliday().getLocation().getCountry(),
                                reservation.getHoliday().getLocation().getImageUrl()
                        ),
                        reservation.getHoliday().getTitle(), reservation.getHoliday().getStartDate(),
                        reservation.getHoliday().getDuration(), reservation.getHoliday().getPrice(), reservation.getHoliday().getFreeSlots()));
    }

    @Override
    public void deleteReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> {
            final String errorMessage =
                    String.format("Reservation not found for id = %s", id);
            return new IllegalArgumentException(errorMessage);
        });

        reservationRepository.delete(reservation);

    }

    @Override
    public List<ResponseReservationDTO> getAllReservation() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(r->modelMapper.map(r,ResponseReservationDTO.class)).toList();
    }

    @Override
    public ResponseReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> {
            final String errorMessage =
                    String.format("Reservation not found for id = %s", id);
            return new IllegalArgumentException(errorMessage);
        });

        return modelMapper.map(reservation, ResponseReservationDTO.class);
    }

    @Override
    public ResponseReservationDTO updateReservation(UpdateReservationDTO updateReservation) {
        Reservation reservation = reservationRepository.findById(updateReservation.getId()).orElseThrow(() -> {
            final String errorMessage =
                    String.format("Reservation not found for id = %s", updateReservation.getId());
            return new IllegalArgumentException(errorMessage);
        });

        Holiday holiday = holidayRepository.findById(updateReservation.getHoliday()).orElseThrow(() -> {
            final String errorMessage =
                    String.format("Reservation not found for id = %s", updateReservation.getId());
            return new IllegalArgumentException(errorMessage);
        });

        reservation.setContactName(updateReservation.getContactName());
        reservation.setPhoneNumber(updateReservation.getPhoneNumber());
        reservation.setHoliday(holiday);
        reservationRepository.save(reservation);

        return modelMapper.map(reservation, ResponseReservationDTO.class);
    }

}


