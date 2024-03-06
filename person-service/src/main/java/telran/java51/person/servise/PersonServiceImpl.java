package telran.java51.person.servise;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java51.person.dao.PersonRepository;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.CityDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.exception.PersonNotFoundException;
import telran.java51.person.model.Address;
import telran.java51.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonServise{
	
	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<PersonDto> findByCity(String city) {
		Address address = new Address(city, null, null);
		return personRepository.findByAddressMatchesAddressIgnoreCase(address)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findByAges(Integer ageFrom, Integer ageTo) {
		LocalDate dateFrom = LocalDate.now().minusYears(ageFrom);
		LocalDate dateTo = LocalDate.now().minusYears(ageTo);
		return personRepository.findByBirthDateBetween(dateFrom, dateTo)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PersonDto updateName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(name);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override 
	public Iterable<PersonDto> findByName(String name) {
		return personRepository.findByNameIgnoreCase(name)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<CityDto> getCityPopulation() {
		List<Person> persons = (List<Person>) personRepository.findAll();
		Map<String, Long> populations = persons.stream()
				.map(p -> p.getAddress().getCity())
		        .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
		return populations.entrySet().stream()
				.map(e -> new CityDto(e.getKey(), e.getValue()))
				.collect(Collectors.toList());
	}

	@Override
	public PersonDto updateAddress(Integer id, AddressDto newAddress) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		if (newAddress != null) {
			person.setAddress(modelMapper.map(newAddress, Address.class));
		}
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

}
