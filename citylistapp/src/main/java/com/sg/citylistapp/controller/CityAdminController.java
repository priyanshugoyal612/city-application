package com.sg.citylistapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.citylistapp.model.CityRequest;
import com.sg.citylistapp.model.CityResponse;
import com.sg.citylistapp.service.CityListService;

@RestController
@RequestMapping("/admin")
public class CityAdminController {
	
	@Autowired
	private CityListService cityListService;
	
	
	@GetMapping("/")
	public String admin()
	{
		return "Hi I am admin";
	}
	
	
	/**
	 * 
	 * This operations update the city details on the basis of Id.
	 * 
	 * @param id
	 * @param city
	 * @return CityResponse
	 */
	@PatchMapping("/new/{id}")
	public ResponseEntity<CityResponse> update(@PathVariable("id") Long id, @RequestBody CityRequest city) {
		//logger.info("updating city on the basis of id :", id);
		CityResponse cityRes = cityListService.update(id, city);
		return new ResponseEntity<>(cityRes, HttpStatus.OK);
	}
	
}
