package org.ta.database;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@SpringBootApplication
public class DatabaseServiceApplication {

	public static void main(String[] args) {
		String v = "13/11/2019 03:15 PM";
		DateTimeFormatter toTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
		System.out.println("Inu -->> "+v);

		DateTimeFormatter fromTimeformat =
				new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss").appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter();

		LocalDateTime localDate = LocalDateTime.parse(v,toTimeFormat);
		System.out.println("Outu -->> "+localDate.format(fromTimeformat));

		//SpringApplication.run(DatabaseServiceApplication.class, args);

	}

}
