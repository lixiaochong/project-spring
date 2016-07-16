package com.coco.springdata;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.Repository;


//@RepositoryDefinition(domainClass=Person.class,idClass=Integer.class)
public interface PersonRepository extends Repository<Person, Integer>{
	
	//根据 lastName 来获取对应的 Person
	Person getByLastName(String lastName);
	
	//根据 address 来获取对应的 Person
//	Person getByAddress(String adress);
	
	//WHERE LASTNAME LIKE ?% AND ID < ?
	List<Person> getByLastNameStartingWithAndIdLessThan(String lastName,Integer id);
	
	//WHERE lastName LIKE %? AND id < ?
	List<Person> getByLastNameEndingWithAndIdLessThan(String lastName,Integer id);
	
	//WHERE email IN (?, ?, ?) AND birth < ?
	List<Person> getByEmailInAndBirthLessThan(List<String> emails,Date birth);
	
	//WHERE a.id > ?
	List<Person> getByAddressIdGreaterThan(Integer id);
}
