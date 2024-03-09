package telran.java51.person.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Child extends Person {
	
	private static final long serialVersionUID = 2034419160865530531L;
	
	String kindergarten;

	public Child(Integer id, String name, LocalDate birthDate, Address address, String kindergarten) {
		super(id, name, birthDate, address);
		this.kindergarten = kindergarten;
	}
	
	

}
