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
public class Employee extends Person {

	private static final long serialVersionUID = -1194535848440558101L;
	
	String companyString;
	int salary;

	public Employee(Integer id, String name, LocalDate birthDate, Address address, String companyString, int salary) {
		super(id, name, birthDate, address);
		this.companyString = companyString;
		this.salary = salary;
	}

}
