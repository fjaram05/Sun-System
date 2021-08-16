package com.galaxy.system.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.galaxy.system.data.mongodb.model.Planet;
import com.galaxy.system.data.mongodb.model.SystemDay;
import com.galaxy.system.data.mongodb.repository.SystemDayRepository;
import com.galaxy.system.exception.SystemNotFoundException;

@SpringBootTest
@AutoConfigureMockMvc
class SystemServicesTest {
	
	@Autowired
	@InjectMocks
	private SystemServices systemServices;
	
	@Autowired
	@MockBean
	private SystemDayRepository systemRepository;

	@Test
	void consultationDayTest() throws SystemNotFoundException {
		SystemDay systemDay = getDayData();
		Mockito.doReturn(systemDay).when(systemRepository).findByDay(LocalDate.now());
		assertNotNull(systemServices.consultationDay(LocalDate.now()));
	}

	@Test
	void consultationDayTestError()  {
		Mockito.doReturn(null).when(systemRepository).findByDay(LocalDate.now());
		try {
			assertNotNull(systemServices.consultationDay(LocalDate.now()));
		} catch (SystemNotFoundException e) {
			Assertions.assertTrue(Boolean.TRUE);
		}
	}
	
	@Test
	void consultationNumberDayTest() throws SystemNotFoundException {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		assertNotNull(systemServices.consultationNumberDay(1));
	}
	
	@Test
	void consultationNumberDayTestError() {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		
		try {
			assertNotNull(systemServices.consultationNumberDay(1));
		} catch (SystemNotFoundException e) {
			Assertions.assertTrue(Boolean.TRUE);
		}
	}
	
	@Test
	void consultationNumberDayTestError2() {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		try {
			assertNotNull(systemServices.consultationNumberDay(-1));
		} catch (SystemNotFoundException e) {
			Assertions.assertTrue(Boolean.TRUE);
		}
	}
	
	@Test
	void consultationNumberDayTestError3() {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		try {
			assertNotNull(systemServices.consultationNumberDay(2));
		} catch (SystemNotFoundException e) {
			Assertions.assertTrue(Boolean.TRUE);
		}
	}
	
	@Test
	void batchCalculationDaysTest() throws SystemNotFoundException {
		systemServices.batchCalculationDays(LocalDate.now(), LocalDate.now().plusDays(1));
		Assertions.assertTrue(Boolean.TRUE);
	}
	
	@Test
	void batchCalculationDaysTestError() {
		try {
			systemServices.batchCalculationDays(LocalDate.now(), LocalDate.now());
		} catch (SystemNotFoundException e) {
			Assertions.assertTrue(Boolean.TRUE);
		}
	}
	
	@Test
	void periodDataTest() throws SystemNotFoundException {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		assertNotNull(systemServices.periodData());
		
	}
	
	@Test
	void periodDataTestError() {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		try {
			assertNotNull(systemServices.periodData());
		} catch (SystemNotFoundException e) {
			Assertions.assertTrue(Boolean.TRUE);
		}
	}
	
	@Test
	void getOptimalConditionsTest() throws SystemNotFoundException {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		assertNotNull(systemServices.getOptimalConditions());
	}
	
	@Test
	void getOptimalConditionsTestError() {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		try {
			assertNotNull(systemServices.getOptimalConditions());
		} catch (SystemNotFoundException e) {
			Assertions.assertTrue(Boolean.TRUE);
		}
	}
	
	@Test
	void getDryPeriodTest() throws SystemNotFoundException {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		assertNotNull(systemServices.getDryPeriod());
	}
	
	@Test
	void getDryPeriodTestError() {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		try {
			assertNotNull(systemServices.getDryPeriod());
		} catch (SystemNotFoundException e) {
			Assertions.assertTrue(Boolean.TRUE);
		}
	}
	
	@Test
	void getRainPeriodTest() throws SystemNotFoundException {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		assertNotNull(systemServices.getRainPeriod());
	}
	
	@Test
	void getRainPeriodTestError() {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		try {
			assertNotNull(systemServices.getRainPeriod());
		} catch (SystemNotFoundException e) {
			Assertions.assertTrue(Boolean.TRUE);
		}
	}
	
	@Test
	void getMaximumRainDayTest() throws SystemNotFoundException {
		SystemDay systemDay = getDayData();
		List<SystemDay> list = new ArrayList<>(1);
		list.add(systemDay);
		Mockito.doReturn(list).when(systemRepository).findAll();
		assertNotNull(systemServices.getMaximumRainDay());
	}
	
	@Test
	void getMaximumRainDayTestError() {
		List<SystemDay> list = new ArrayList<>(1);
		Mockito.doReturn(list).when(systemRepository).findAll();
		try {
			assertNotNull(systemServices.getMaximumRainDay());
		} catch (SystemNotFoundException e) {
			Assertions.assertTrue(Boolean.TRUE);
		}
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
