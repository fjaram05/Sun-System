package com.galaxy.system.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.galaxy.system.data.mongodb.model.Planet;
import com.galaxy.system.data.mongodb.model.SystemDay;
import com.galaxy.system.data.mongodb.repository.SystemDayRepository;
import com.galaxy.system.services.SystemServices;

@SpringBootTest
@AutoConfigureMockMvc
class SystemControllerTest {

	@Autowired
	@InjectMocks
	private SystemController controller;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	@InjectMocks
	SystemServices systemServices;
	
	@Autowired
	@MockBean
	private SystemDayRepository systemRepository;
	
	@Test
	public void getDayTest() throws Exception {
		SystemDay systemDay = getDayData();
		Mockito.doReturn(systemDay).when(systemRepository).findByDay(LocalDate.now());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/findDay").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders()).param("dateInfo", LocalDate.now().toString())).andExpect(status().isOk());
	}
	
	@Test
	public void getDayTestError() throws Exception  {
		SystemDay systemDay = getDayData();
		Mockito.doReturn(systemDay).when(systemRepository).findByDay(LocalDate.now());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/findDay").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders()).param("dateInfo", LocalDate.now().minusDays(1).toString()))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void getDayNumberTest() throws Exception {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/findNumberDay").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders()).param("day", "1")).andExpect(status().isOk());
	}

	@Test
	public void getDayNumberTestError() throws Exception {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/findNumberDay").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders()).param("day", "2"))
			.andExpect(status().is4xxClientError());
	}

	@Test
	public void getDayNumberTestError2() throws Exception {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/findNumberDay").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders()).param("day", "2"))
			.andExpect(status().is4xxClientError());
	}

	@Test
	public void setJobYearsTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/jobYears").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders()).param("dateIni", LocalDate.now().toString()).param("dateFin", LocalDate.now().plusDays(1).toString())).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void setJobYearsTestError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/jobYears").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders()).param("dateIni", LocalDate.now().toString()).param("dateFin", LocalDate.now().toString()))
			.andExpect(status().is4xxClientError());
	}

	@Test
	public void setJobYearsTestError2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/jobYears").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders()).param("dateIni", LocalDate.now().toString()).param("dateFin", LocalDate.now().plusYears(21).toString()))
			.andExpect(status().is4xxClientError());
	}

	@Test
	public void setJobPeriodTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/jobPeriod").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders())).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void getPeriodDataTest() throws Exception {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/periodData").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders())).andExpect(status().isOk());
	}

	@Test
	public void getPeriodDataTestError() throws Exception {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/periodData").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders())).andExpect(status().is4xxClientError());
	}

	@Test
	public void getDrySpellsTest() throws Exception {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/drySpells").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders())).andExpect(status().isOk());
	}

	@Test
	public void getDrySpellsTestError() throws Exception {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/drySpells").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders())).andExpect(status().is4xxClientError());
	}

	@Test
	public void getRainyPeriodsTest() throws Exception {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rainyPeriods").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders())).andExpect(status().isOk());
	}

	@Test
	public void getRainyPeriodsTestError() throws Exception {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rainyPeriods").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders())).andExpect(status().is4xxClientError());
	}

	@Test
	public void getOptimalConditionsTest() throws Exception {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/optimalConditions").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders())).andExpect(status().isOk());
	}

	@Test
	public void getOptimalConditionsTestError() throws Exception {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/optimalConditions").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders())).andExpect(status().is4xxClientError());
	}

	@Test
	public void getMaximumRainDayTest() throws Exception {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/maximumRainDay").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders())).andExpect(status().isOk());
	}

	@Test
	public void getMaximumRainDayTestError() throws Exception {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/maximumRainDay").contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)).headers(new HttpHeaders())).andExpect(status().is4xxClientError());
	}
	
	
	/**
	 * @return
	 */
	private SystemDay getDayData() {
		SystemDay day = new SystemDay();
		day.setDay(LocalDate.now());
		day.setBetasoide(new Planet(0,0));
		day.setFeringi(new Planet(1, 1));
		day.setVulcano(new Planet(2, 2));
		day.setId("1");
		day.setInnerSun(false);
		day.setLine(false);
		day.setOptimalPressure(false);
		day.setRainPeriod(false);
		day.setPerimeter(1);
		return day;
	}

}
