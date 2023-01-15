package com.sg.citylistapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sg.citylistapp.entity.City;
import com.sg.citylistapp.model.CityResponse;
import com.sg.citylistapp.repository.CityListRepository;

@ExtendWith(MockitoExtension.class)
public class CityListServiceTest {

	@Mock
	private CityListRepository repository;

	@InjectMocks
	private CityListService service;

	@Test
	public void getCityListTest() {
		City city = new City(1, "Hathras", "www.hts.vom");
		Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(city));
		List<CityResponse> result = service.getCities();
		assertEquals(city.getName(), result.get(0).getName());
		assertEquals(city.getUrl(), result.get(0).getUrl());
	}

	@Test
	public void getCityByIdTest() {
		City city = new City(11, "Hathras", "www.hts.vom");
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(city));
		CityResponse result = service.getCityById(11L);
		assertEquals(11L, result.getId());
		assertEquals("Hathras", result.getName());
	}

	@Test
	public void getCityListBYNameTest() {
		City city = new City(11, "Hathras", "www.hts.vom");
		Mockito.when(repository.findByNameContainingIgnoreCase(anyString()))
				.thenReturn(Collections.singletonList(city));
		List<CityResponse> result = service.getCityByName("HATHRAS");
		assertEquals(11L, result.get(0).getId());
		assertEquals("Hathras", result.get(0).getName());
	}

	@Test
	public void getCityListBuyWhenCityNotFound() {
		Mockito.when(repository.findByNameContainingIgnoreCase(anyString())).thenReturn(Collections.EMPTY_LIST);
		List<CityResponse> result = service.getCityByName("HATHRAS");
		assertEquals(0, result.size());
	}

}
