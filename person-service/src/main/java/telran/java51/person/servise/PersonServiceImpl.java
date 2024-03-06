package telran.java51.person.servise;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
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

	@Transactional(readOnly = true)
	@Override
	public Iterable<PersonDto> findByCity(String city) {
		return personRepository.findByAddressCityIgnoreCase(city)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<PersonDto> findByAges(Integer ageFrom, Integer ageTo) {
		LocalDate dateFrom = LocalDate.now().minusYears(ageFrom);
		LocalDate dateTo = LocalDate.now().minusYears(ageTo);
		return personRepository.findByBirthDateBetween(dateFrom, dateTo)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public PersonDto updateName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(name);
//		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional(readOnly = true)
	@Override 
	public Iterable<PersonDto> findByName(String name) {
		return personRepository.findByNameIgnoreCase(name)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<CityDto> getCityPopulation() {
		return personRepository.getCitiesPopulation();
	}

	@Transactional
	@Override
	public PersonDto updateAddress(Integer id, AddressDto newAddress) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		if (newAddress != null) {
			person.setAddress(modelMapper.map(newAddress, Address.class));
		}
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional
	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

}
