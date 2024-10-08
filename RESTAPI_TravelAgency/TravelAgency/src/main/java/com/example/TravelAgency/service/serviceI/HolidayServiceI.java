package com.example.TravelAgency.service.serviceI;

import com.example.TravelAgency.dto.CreateHolidayDTO;
import com.example.TravelAgency.dto.ResponseHolidayDTO;
import com.example.TravelAgency.dto.UpdateHolidayDTO;

import java.util.Date;
import java.util.List;


public interface HolidayServiceI {

    ResponseHolidayDTO createHoliday(CreateHolidayDTO createHolidayDTO);

    void deleteHolidayById(Long id);

    List<ResponseHolidayDTO> getAllHolidaysByFilters(String city,String country, Date startDate, Integer duration);

    ResponseHolidayDTO getHolidayById(Long id);

    ResponseHolidayDTO updateHoliday(UpdateHolidayDTO updateHolidayDTO);

}
