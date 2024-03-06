package telran.java51.person.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.CityDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.servise.PersonServise;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
	final PersonServise personServise;
	
	@PostMapping
	public Boolean addPerson(@RequestBody PersonDto personDto) {
		return personServise.addPerson(personDto);
	}
	
	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personServise.findPersonById(id);
	}
	
	@GetMapping("/city/{city}")
	public Iterable<PersonDto> findByCity(@PathVariable String city){
		return personServise.findByCity(city);
	}
	
	@GetMapping("/ages/{ageFrom}/{ageTo}")
	public Iterable<PersonDto> findByAges(@PathVariable Integer ageFrom, @PathVariable Integer ageTo){
		return personServise.findByAges(ageFrom, ageTo);
	}
	
	@PutMapping("/{id}/name/{name}")
	public PersonDto updateName(@PathVariable Integer id, @PathVariable String name) {
		return personServise.updateName(id, name);
	}
	
	@GetMapping("/name/{name}")
	public Iterable<PersonDto> findByName(@PathVariable String name){
		return personServise.findByName(name);
	}
	
	@GetMapping("/population/city")
	public Iterable<CityDto> getCityPopulation(){
		return personServise.getCityPopulation();
	}
	
	@PutMapping("/{id}/address")
	public PersonDto updateAddress(@PathVariable Integer id, @RequestBody AddressDto newAddress) {
		return personServise.updateAddress(id, newAddress);
	}
	
	@DeleteMapping("/{id}")
	public PersonDto deletePerson(@PathVariable Integer id) {
		return personServise.deletePerson(id);
	}
}
