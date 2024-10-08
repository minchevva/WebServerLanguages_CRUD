package com.example.TravelAgency.service;


import com.example.TravelAgency.dto.CreateLocationDTO;
import com.example.TravelAgency.dto.ResponseLocationDTO;
import com.example.TravelAgency.dto.UpdateLocationDTO;
import com.example.TravelAgency.model.Location;
import com.example.TravelAgency.repository.LocationRepository;
import com.example.TravelAgency.service.serviceI.LocationServiceI;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService implements LocationServiceI {

    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public ResponseLocationDTO createLocation(CreateLocationDTO createLocationDTO) {
        Location location = new Location();

        location.setStreet(createLocationDTO.getStreet());
        location.setNumber(createLocationDTO.getNumber());
        location.setCity(createLocationDTO.getCity());
        location.setCountry(createLocationDTO.getCountry());
        location.setImageUrl(createLocationDTO.getImageUrl());

        location = locationRepository.save(location);

        return new ResponseLocationDTO(location.getId(), location.getStreet(), location.getNumber(),
                location.getCity(), location.getCountry(), location.getImageUrl());
    }

    @Transactional
    @Override
    public void deleteLocationById(Long id) {
        Location location = locationRepository.findById(id).orElseThrow(() -> {
            final String errorMessage =
                    String.format("Location not found for id = %s", id);
            return new IllegalArgumentException(errorMessage);
        });

        locationRepository.delete(location);
    }

    @Override
    public List<ResponseLocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();

        return locations.stream().map(l->modelMapper.map(l,ResponseLocationDTO.class)).toList();
    }

    @Override
    public ResponseLocationDTO getLocationById(Long id) {
        Location location = locationRepository.findById(id).orElseThrow(() -> {
            final String errorMessage =
                    String.format("Location not found for id = %s", id);
            return new IllegalArgumentException(errorMessage);
        });

        return modelMapper.map(location, ResponseLocationDTO.class);
    }

    @Transactional
    @Override
    public ResponseLocationDTO updateLocation(UpdateLocationDTO updateLocationDTO) {
        Location currentLocation = locationRepository.findById(updateLocationDTO.getId()).orElse(null);

        if (currentLocation != null) {
            currentLocation.setStreet(updateLocationDTO.getStreet());
            currentLocation.setNumber(updateLocationDTO.getNumber());
            currentLocation.setCity(updateLocationDTO.getCity());
            currentLocation.setCountry(updateLocationDTO.getCountry());
            currentLocation.setImageUrl(updateLocationDTO.getImageUrl());

            currentLocation = locationRepository.save(currentLocation);

            return new ResponseLocationDTO(currentLocation.getId(), currentLocation.getStreet(), currentLocation.getNumber(),
                    currentLocation.getCity(), currentLocation.getCountry(), currentLocation.getImageUrl());
        } else {
            return null;
        }
    }
}
