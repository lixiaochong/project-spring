package com.coco.springdata.test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coco.springdata.Person;
import com.coco.springdata.PersonRepository;
import com.coco.springdata.PersonService;

public class SpringDataTest {

	private ApplicationContext ctx = null;
	private PersonRepository personRepository = null;
	
	private PersonService personService;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personRepository = ctx.getBean(PersonRepository.class);
		personService = ctx.getBean(PersonService.class);
	}
	
	@Test
	public void testModifying(){
		personService.updatePersonEmail("aaa@coco.com",1);
	}
	
	@Test
	public void testNativeQuery(){
		long counts = personRepository.getTotalPersonCounts();
		System.out.println(counts);
	}
	
	@Test
	public void testQueryAnnotationLikeParam2(){
		List<Person> persons = personRepository.testQueryAnnotationLikeParam2("tt", "N");
		System.out.println(persons.size());
	}
	
	@Test
	public void testQueryAnnotationLikeParam(){
		List<Person> persons = personRepository.testQueryAnnotationLikeParam("F", "xx");
		System.out.println(persons.size());
	}
	
	@Test
	public void testQueryAnnotationParams2(){
		List<Person> persons = personRepository.testQueryAnnotationParams2("cc@coco.com", "CC");
		System.out.println(persons);
	}
	
	@Test
	public void testQueryAnnotationParams1(){
		List<Person> persons = personRepository.testQueryAnnotationParams1("XX", "xx@coco.com");
		System.out.println(persons);
	}
	
	@Test
	public void testQueryAnnotation(){
		Person person = personRepository.getMaxIdPerson();
		System.out.println(person);
	}
	
	@Test
	public void testKeyWords2(){
		List<Person> persons = personRepository.getByAddressIdGreaterThan(1);
		System.out.println(persons);
	}
	
	@Test
	public void testKeyWords() {
		List<Person> persons = personRepository
				.getByLastNameStartingWithAndIdLessThan("X", 12);
		System.out.println(persons);

		persons = personRepository
				.getByLastNameEndingWithAndIdLessThan("X", 16);
		System.out.println(persons);

		persons = personRepository.getByEmailInAndBirthLessThan(
				Arrays.asList("aa@coco.com", "cc@coco.com", "ff@coco.com"),
				new Date());
		System.out.println(persons);
	}

//	@Test
//	public void testAdress() {
//		Person person = personRepository.getByAddress("aa");
//		System.out.println(person);
//	}

	@Test
	public void testHelloWorldSpringData() {
		Person person = personRepository.getByLastName("AA");
		System.out.println(person);
	}

	@Test
	public void testJpa() {

	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
