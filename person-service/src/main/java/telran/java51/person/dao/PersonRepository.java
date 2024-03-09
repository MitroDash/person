package telran.java51.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java51.person.dto.CityDto;
import telran.java51.person.model.Person;


public interface PersonRepository extends CrudRepository<Person, Integer> {
	Stream<Person> findByNameIgnoreCase(String name);
	
	Stream<Person> findByBirthDateBetween(LocalDate dateFrom, LocalDate dateTo);
	
	Stream<Person> findByAddressCityIgnoreCase(String city);
	
	@Query("select new telran.java51.person.dto.CityDto(p.address.city, count(p)) from Person p group by p.address.city order by count(p) desc")
	List<CityDto> getCitiesPopulation();

}
