package patientintake;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;

public class ClinicCalendarShould {

	// Deixo o construtor explicito se n�o d� erro
	public ClinicCalendarShould() {

	}

	@Test
	public void allowEntryOfAnAppointment() {
		ClinicCalendar calendar = new ClinicCalendar();
		calendar.addAppointment("Jim", "Weaver", "avery", "09/01/2022 10:00 pm");

		List<PatientAppointment> appointments = calendar.getAppointments();

		// vou testar se a lista � vazia
		assertNotNull(appointments);

		// teste se est� sendo inclu�do apenas 1 registro na lista
		// primeiro parametro � o valor esperado
		// segundo � o valor de retorno
		assertEquals(1, appointments.size());

		// farei a verifica��o de campo a campo
		PatientAppointment enteredAppt = appointments.get(0);
		assertEquals("Jim", enteredAppt.getPatientFirstName());
		assertEquals("Weaver", enteredAppt.getPatientLastName());
		// assertSame aponta para o mesmo objeto na mem�ria. Para o caso de enum, esse
		// teste � para funcioanr
		assertSame(Doctor.avery, enteredAppt.getDoctor());
		// usamos a formata��o de data no valor que recebemos.
		assertEquals("9/1/2022 10:00 PM",
				enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));

	}

	@Test
	public void returnTrueForHasAppointmentsIfTereAreAppointments() {
		ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
		calendar.addAppointment("Jim", "Weaver", "avery", "09/01/2022 10:00 pm");
		assertTrue(calendar.hasAppointment(LocalDate.of(2022, 9, 1)));

	}

	@Test
	public void returnFasleForHasAppointmentsIfTereAreNoAppointments() {
		ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
		assertFalse(calendar.hasAppointment(LocalDate.of(2022, 9, 1)));
	}

	@Test
	public void returnCurrentDaysAppointments() {
		ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
		calendar.addAppointment("Jim", "Weaver", "avery", "03/24/2022 2:00 pm");
		calendar.addAppointment("Nayara", "Serodio", "avery", "03/24/2022 3:00 pm");
		calendar.addAppointment("Luiz", "Gustavo", "avery", "03/25/2022 2:00 pm");

		assertEquals(2, calendar.getTodayAppointments().size());

		// se eu tratar dessa forma abaixo, vai dar erro, pois o tamanho dos arrays s�o
		// diferentes.
		// assertEquals(calendar.getTodayAppointments(), calendar.getAppointments());
		
		// para testar cole��es, eu uso o assertIterableEquals. Ele trabalha com o for
		// dentro - S� funciona o m�todo abaixo se houver 2 lan�amentos no calendar acima
		//assertIterableEquals(calendar.getTodayAppointments(), calendar.getAppointments());

	}
}
