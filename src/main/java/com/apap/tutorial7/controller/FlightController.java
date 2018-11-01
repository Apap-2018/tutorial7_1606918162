package com.apap.tutorial7.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.service.FlightService;

/**
 * FlightController
 */
@RestController
@RequestMapping("flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping(value = "/all")
    public @ResponseBody List<FlightModel> viewAll(){
    	List<FlightModel> flight = flightService.getAll();
    	return flight;
    }

    @PostMapping(value = "/add")
    public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
    	flightService.addFlight(flight);
    	return flight;
    }
    
    @GetMapping(value = "/view/{flightNumber}")
    public FlightModel flightView(@PathVariable("flightNumber") String flightNumber) {
    	FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
    	return flight;
    }

    @DeleteMapping(value = "/delete/{flightId}")
    public String deleteFlight(@PathVariable("flightId") long flightId) {
    	FlightModel flight = flightService.getFlightDetailByFlightId(flightId);
    	flightService.deleteFlight(flight);
    	return "flight has been deleted";
    }

    @PutMapping(value="/update/{flightId}")
    public String updateFlightSubmit(@PathVariable("flightId") long flightId,
    		@RequestParam("destination") String destination,
    		@RequestParam("origin") String origin,
    		@RequestParam("time") Date time) {
    	FlightModel flight = flightService.getFlightDetailByFlightId(flightId);
    	if (flight.equals(null)) {
    		return "Couldn't find your flight";
    	}
    	
    	flight.setDestination(destination);
    	flight.setOrigin(origin);
    	flight.setTime(time);
    	flightService.addFlight(flight);
    	return "update";
    }

}