package patientintake;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DateTimeConverterShould {

	@Test
	void convertTodayStringCorrectly() {
		LocalDateTime result = DateTimeConverter.converterStringToDateTime("today 1:00 pm", LocalDate.of(2022,  03, 29));
		assertEquals(result, LocalDateTime.of(2022, 03, 29, 13, 00));
	}
	
	@Test
	void convertCorrectPatternToDateTime() {
		LocalDateTime result = DateTimeConverter.converterStringToDateTime("03/29/2022 1:00 pm", LocalDate.of(2022,  03, 29));
		assertEquals(result, LocalDateTime.of(2022, 03, 29, 13, 00));
	}

	@Test
	void throwExcpetionIfINcorrectPatternProvided() {
		Throwable error = assertThrows(RuntimeException.class, 
				() -> DateTimeConverter.converterStringToDateTime("03/29/2022 100 pm", LocalDate.of(2022,  03, 29)));
		
		assertEquals("Unable to create date time from: [03/29/2022 100 PM], please enter with format [M/d/yyyy h:mm a]", error.getMessage());
	}

}
