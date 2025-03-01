package patientintake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClinicCalendar {

	private List<PatientAppointment> appointments;
	private LocalDate today;

	public ClinicCalendar() {
		this.appointments = new ArrayList<>();
	}

	public ClinicCalendar(LocalDate today) {
		this.today = today;
		this.appointments = new ArrayList<>();
	}

	public void addAppointment(String patientFirstName, String patientLastName, String doctorKey, String dateTime) {

		Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
		LocalDateTime localDateTime = DateTimeConverter.converterStringToDateTime(dateTime, today);

		PatientAppointment appointment = new PatientAppointment(patientFirstName, patientLastName, localDateTime, doc);
		appointments.add(appointment);
	}

//	private LocalDateTime convertToDateTimeFromString(String dateTime) {
//		LocalDateTime localDateTime;
//		try {
//
//			if (dateTime.toLowerCase().startsWith("today")) {
//				String[] parts = dateTime.split(" ", 2);
//				LocalTime time = LocalTime.parse(parts[1].toUpperCase(),
//						DateTimeFormatter.ofPattern("h:mm a", Locale.US));
//				localDateTime = LocalDateTime.of(today, time);
//				
//			} else {
//				localDateTime = LocalDateTime.parse(dateTime.toUpperCase(),
//						DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
//			}
//		} catch (Throwable t) {
//			throw new RuntimeException("Unable to create date time from: [" + dateTime.toUpperCase()
//					+ "], please enter with format [M/d/yyyy h:mm a]");
//		}
//		return localDateTime;
//	}

	public List<PatientAppointment> getAppointments() {
		return this.appointments;
	}

	public List<PatientAppointment> getTodayAppointments() {
		return appointments.stream().filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(today))
				.collect(Collectors.toList());
	}

	public boolean hasAppointment(LocalDate date) {
		// esse met�do retorna true se houver compromissos agendados no dia.
		return appointments.stream().anyMatch(appt -> appt.getAppointmentDateTime().toLocalDate().equals(date));
	}
}
