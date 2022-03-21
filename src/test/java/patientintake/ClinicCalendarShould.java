package patientintake;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;

public class ClinicCalendarShould {

	
	//Deixo o construtor explicito se n�o d� erro
	public ClinicCalendarShould() {
		
	}
	
	@Test
	public void allowEntryOfAnAppointment() {
		ClinicCalendar calendar = new ClinicCalendar();
		calendar.addAppointment("Jim", "Weaver", "avery", "09/01/2022 10:00 pm");
		
		List<PatientAppointment> appointments = calendar.getAppointments();
		
		//vou testar se a lista � vazia
		assertNotNull(appointments);
		
		//teste se est� sendo inclu�do apenas 1 registro na lista
		// primeiro parametro � o valor esperado
		// segundo � o valor de retorno
		assertEquals(1, appointments.size());
		
		//farei a verifica��o de campo a campo
		PatientAppointment enteredAppt = appointments.get(0);
		assertEquals("Jim", enteredAppt.getPatientFirstName());
		assertEquals("Weaver", enteredAppt.getPatientLastName());
		assertEquals(Doctor.avery, enteredAppt.getDoctor());
		//usamos a formata��o de data no valor que recebemos.
		assertEquals("9/1/2022 10:00 PM", 
				enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));
		
	}

}
