package com.example.TravelAgency.service.serviceI;

import com.example.TravelAgency.dto.*;

import java.util.List;


public interface LocationServiceI {
    ResponseLocationDTO createLocation(CreateLocationDTO createLocationDTO);

    void deleteLocationById(Long id);

    List<ResponseLocationDTO> getAllLocations();

    ResponseLocationDTO getLocationById(Long id);

    ResponseLocationDTO updateLocation(UpdateLocationDTO updateLocationDTO);
}
