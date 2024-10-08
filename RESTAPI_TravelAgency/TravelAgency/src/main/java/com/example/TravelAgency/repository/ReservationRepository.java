package com.example.TravelAgency.repository;

import com.example.TravelAgency.model.Reservation;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends ListCrudRepository<Reservation, Long> {

}