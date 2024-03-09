package telran.java51.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable{
	private static final long serialVersionUID = 8689333119925486177L;
	@Id
	Integer id;
	@Setter
	String name;
	LocalDate birthDate;
	@Embedded
	@Setter
	Address address;

}
