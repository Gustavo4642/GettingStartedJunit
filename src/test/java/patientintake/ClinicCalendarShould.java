package patientintake;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class ClinicCalendarShould {

	
	//Deixo o construtor explicito se não dá erro
	public ClinicCalendarShould() {
		
	}
	
	@Test
	public void allowEntryOfAnAppointment() {
		ClinicCalendar calendar = new ClinicCalendar();
		calendar.addAppointment("Jim", "Weaver", "avery", "09/01/2022 10:00 pm");
		
		List<PatientAppointment> appointments = calendar.getAppointments();
		
		assertNotNull(appointments);
		assertEquals(1, appointments.size());
	}

}
