package com.travel.exercise.controller;


import java.util.*;

import com.travel.exercise.entity.Flight;
import com.travel.exercise.service.FlightServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class FlightControllerTest {

	private MockMvc mockMvc;

	@Mock
	private FlightServiceImpl flightService;

	@InjectMocks
	private FlightController flightController;

	Flight flight_record1 = new Flight("101", "AMS", "BOM", "10:00:00", "22:00:00", 560);
	Flight flight_record2 = new Flight("102", "DEL", "AMS", "07:00:00", "19:00:00", 640);
	Flight flight_record3 = new Flight("103", "HYD", "PSG", "21:00:00", "08:00:00", 510);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
	}

	@Test
	public void getFlightRoutesBySourceDestination_success() throws Exception {
		List<Flight> flightList = new ArrayList<>(Arrays.asList(flight_record1,flight_record2,flight_record3));
		List destination1 = new ArrayList(Arrays.asList("price","desc"));
		Mockito.when(flightService.getFlightInformation("AMS","BOM", destination1)).thenReturn(flightList);
		mockMvc.perform(MockMvcRequestBuilders.get("/flight/routes?origin=AMS&destination=BOM")
						.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.flightResponseList", hasSize(3)))
				.andExpect(jsonPath("$.flightResponseList[0].origin",is("AMS")));
	}


}