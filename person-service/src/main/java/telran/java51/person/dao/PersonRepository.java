package telran.java51.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import telran.java51.person.dto.CityDto;
import telran.java51.person.model.Child;
import telran.java51.person.model.Employee;
import telran.java51.person.model.Person;


public interface PersonRepository extends CrudRepository<Person, Integer> {
	Stream<Person> findByNameIgnoreCase(String name);
	
	Stream<Person> findByBirthDateBetween(LocalDate dateFrom, LocalDate dateTo);
	
	@Query("select p from Person p where p.address.city=:cityName")
	Stream<Person> findByAddressCityIgnoreCase(@Param("cityName") String city);
	
	@Query("select new telran.java51.person.dto.CityDto(p.address.city, count(p)) from Person p group by p.address.city order by count(p) desc")
	List<CityDto> getCitiesPopulation();
	
	Stream<Employee> findEmployeeBySalaryBetween(int min, int max);
	
	Stream<Child> findChildBy();

}
