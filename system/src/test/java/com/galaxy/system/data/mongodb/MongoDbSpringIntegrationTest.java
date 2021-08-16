package com.galaxy.system.data.mongodb;


import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.galaxy.system.data.mongodb.model.Planet;
import com.galaxy.system.data.mongodb.model.SystemDay;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class MongoDbSpringIntegrationTest {

	@DisplayName("given object to save" + " when save object using MongoDB template" + " then object is saved")
	@Test
	public void test(@Autowired MongoTemplate mongoTemplate) {
		// given
		DBObject objectToSave = BasicDBObjectBuilder.start().add("6119e83fd488822419fb38e4", getDayData()).get();

		// when
		mongoTemplate.save(objectToSave, "systemDay");

		// then
		mongoTemplate.findAll(DBObject.class, "systemDay");
		
		Assertions.assertNotNull(mongoTemplate.findAll(DBObject.class, "systemDay"),"test");
		
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
