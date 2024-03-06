package telran.java51.person.dao;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import telran.java51.person.model.Address;
import telran.java51.person.model.Person;



public interface PersonRepository extends CrudRepository<Person, Integer> {
	Stream<Person> findByNameIgnoreCase(String name);
	
	Stream<Person> findByBirthDateBetween(LocalDate dateFrom, LocalDate dateTo);
	
	Stream<Person> findByAddressMatchesAddressIgnoreCase(Address address);

}
