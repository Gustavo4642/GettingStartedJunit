package patientintake;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.format.DateTimeFormatter;
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
		
		//vou testar se a lista é vazia
		assertNotNull(appointments);
		
		//teste se está sendo incluído apenas 1 registro na lista
		// primeiro parametro é o valor esperado
		// segundo é o valor de retorno
		assertEquals(1, appointments.size());
		
		//farei a verificação de campo a campo
		PatientAppointment enteredAppt = appointments.get(0);
		assertEquals("Jim", enteredAppt.getPatientFirstName());
		assertEquals("Weaver", enteredAppt.getPatientLastName());
		assertEquals(Doctor.avery, enteredAppt.getDoctor());
		//usamos a formatação de data no valor que recebemos.
		assertEquals("9/1/2022 10:00 PM", 
				enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));
		
	}

}
