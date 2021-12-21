package com.store.zumic;

import com.store.zumic.models.City;
import com.store.zumic.utils.ConvertStringToEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@Tag("enumConverter")
@DisplayName("StringToEnumClassConverter should")
@SpringBootTest
class ZumicApplicationTests {

	@Test
	@DisplayName("convert String to Enum correctly")
	public void whenConvertedIntoEnum_thenGetsConvertedCorrectly(){
		log.info("enum stufff..............");
		City enumCity = ConvertStringToEnum.convertStringToEnum("LAGOS");

		assertTrue(enumCity == City.LAGOS);
	}

	@Test
	@DisplayName("convert String to Enum correctly regardless of case")
	public void whenConvertedIntoEnum_thenGetsConvertedCorrectly_caseSensitive(){
		log.info("enum stufff..............");
		City enumCity = ConvertStringToEnum.convertStringToEnum("lagos");

		assertTrue(enumCity == City.LAGOS);
	}

	//convert enum to string

	//make it is case sentive i.e convert to UPPER CLASS


	@Test
	@Disabled
	@DisplayName("throw an illegal exception")
	void whenInvalidCityIsInputted_thenThrowsException(){
		Throwable error =
				assertThrows(IllegalArgumentException.class, () ->  ConvertStringToEnum.convertStringToEnum("tade"));
		assertEquals("put correct message", error.getMessage());
	}

	@Test
	@DisplayName("throw an illegal exception with a defined error message")
	void whenInvalidCityIsInputted_thenThrowsExceptionWithDYI(){
		City city = ConvertStringToEnum.convertStringToEnum("agege");
		assertEquals(city, City.LAGOS, () -> "No Enum constant" +city);
	}




}
