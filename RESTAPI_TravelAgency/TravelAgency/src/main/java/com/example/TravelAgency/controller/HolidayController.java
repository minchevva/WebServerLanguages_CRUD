package com.example.TravelAgency.controller;


import com.example.TravelAgency.dto.CreateHolidayDTO;
import com.example.TravelAgency.dto.ResponseHolidayDTO;
import com.example.TravelAgency.dto.UpdateHolidayDTO;
import com.example.TravelAgency.service.serviceI.HolidayServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/holidays")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class HolidayController {

    private final HolidayServiceI holidayService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseHolidayDTO> createHoliday(@RequestBody CreateHolidayDTO createHolidayDTO) {
        try {
            ResponseHolidayDTO response = holidayService.createHoliday(createHolidayDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        try {
            holidayService.deleteHolidayById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ResponseHolidayDTO>> getAllHolidays(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) Integer duration) {
        try {
            List<ResponseHolidayDTO> holidays = holidayService.getAllHolidaysByFilters(city,country, startDate, duration);
            return new ResponseEntity<>(holidays, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseHolidayDTO> getHolidayById(@PathVariable Long id) {
        try {
            ResponseHolidayDTO holiday = holidayService.getHolidayById(id);
            return new ResponseEntity<>(holiday, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseHolidayDTO> updateHoliday(@RequestBody UpdateHolidayDTO updateHolidayDTO) {
        try {
            ResponseHolidayDTO holiday = holidayService.updateHoliday(updateHolidayDTO);
            return new ResponseEntity<>(holiday, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
