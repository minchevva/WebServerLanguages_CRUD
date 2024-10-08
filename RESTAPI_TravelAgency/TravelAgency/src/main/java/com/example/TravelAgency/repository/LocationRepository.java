package com.example.TravelAgency.repository;

import com.example.TravelAgency.model.Location;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Long> {

}