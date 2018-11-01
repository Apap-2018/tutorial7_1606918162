package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.FlightModel;

/**
 * FlightService
 */
public interface FlightService {
    FlightModel addFlight(FlightModel flight);
    
    void deleteByFlightNumber(String flightNumber);

    FlightModel getFlightDetailByFlightNumber(String flightNumber);

	FlightModel getFlightDetailByFlightId(long id);
	
	List<FlightModel> getAll();
	
	void deleteFlight(FlightModel flight);
}