package com.example.TravelAgency.repository;


import com.example.TravelAgency.model.Holiday;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends ListCrudRepository<Holiday, Long> {

}