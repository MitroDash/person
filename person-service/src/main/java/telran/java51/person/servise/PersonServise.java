package telran.java51.person.servise;

import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.CityDto;
import telran.java51.person.dto.PersonDto;

public interface PersonServise {
	
	Boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById(Integer id);
	
	Iterable<PersonDto> findByCity(String city);
	
	Iterable<PersonDto> findByAges(Integer ageFrom, Integer ageTo);
	
	PersonDto updateName(Integer id, String name);
	
	Iterable<PersonDto> findByName(String name);
	
	Iterable<CityDto> getCityPopulation();
	
	PersonDto updateAddress(Integer id, AddressDto newAddress);
	
	PersonDto deletePerson(Integer id);

}
