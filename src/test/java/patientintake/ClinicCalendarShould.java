package patientintake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClinicCalendarShould {

	private ClinicCalendar calendar;

	// Deixo o construtor explicito se não dá erro
	public ClinicCalendarShould() {

	}

	@BeforeAll
	static void testClassSetup() {
		System.out.println("Before all...");
	}
	
	@BeforeEach
	public void init() {
		System.out.println("before each");
		this.calendar = new ClinicCalendar(LocalDate.of(2022, 3, 29));
	}

	@Test
	public void allowEntryOfAnAppointment() {
		System.out.println("Test class...");
		calendar.addAppointment("Jim", "Weaver", "avery", "09/01/2022 10:00 pm");

		List<PatientAppointment> appointments = calendar.getAppointments();

		// vou testar se a lista é vazia
		assertNotNull(appointments);

		// teste se está sendo incluído apenas 1 registro na lista
		// primeiro parametro é o valor esperado
		// segundo é o valor de retorno
		assertEquals(1, appointments.size());

		// farei a verificação de campo a campo
		PatientAppointment enteredAppt = appointments.get(0);
		assertEquals("Jim", enteredAppt.getPatientFirstName());
		assertEquals("Weaver", enteredAppt.getPatientLastName());
		// assertSame aponta para o mesmo objeto na memória. Para o caso de enum, esse
		// teste é para funcioanr
		assertSame(Doctor.avery, enteredAppt.getDoctor());
		// usamos a formatação de data no valor que recebemos.
		assertEquals("9/1/2022 10:00 PM",
				enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));

	}

	@Test
	public void returnTrueForHasAppointmentsIfTereAreAppointments() {
		System.out.println("Test class...");
		calendar.addAppointment("Jim", "Weaver", "avery", "09/01/2022 10:00 pm");
		assertTrue(calendar.hasAppointment(LocalDate.of(2022, 9, 1)));

	}

	@Test
	public void returnFasleForHasAppointmentsIfTereAreNoAppointments() {
		System.out.println("Test class...");
		assertFalse(calendar.hasAppointment(LocalDate.of(2022, 9, 1)));
	}

	@Test
	public void returnCurrentDaysAppointments() {
		System.out.println("Test class...");
		calendar.addAppointment("Jim", "Weaver", "avery", "03/29/2022 2:00 pm");
		calendar.addAppointment("Nayara", "Serodio", "avery", "03/29/2022 3:00 pm");
		calendar.addAppointment("Luiz", "Gustavo", "avery", "03/31/2022 2:00 pm");

		assertEquals(2, calendar.getTodayAppointments().size());

		// se eu tratar dessa forma abaixo, vai dar erro, pois o tamanho dos arrays são
		// diferentes.
		// assertEquals(calendar.getTodayAppointments(), calendar.getAppointments());

		// para testar coleções, eu uso o assertIterableEquals. Ele trabalha com o for
		// dentro - Só funciona o método abaixo se houver 2 lançamentos no calendar
		// acima
		// assertIterableEquals(calendar.getTodayAppointments(),
		// calendar.getAppointments());

	}
	
	@AfterEach
	void tearDownEachTest() {
		System.out.println("After each...");
	}
	
	@AfterAll
	static void tearDownAllTest() {
		System.out.println("After all...");
	}
	
}
