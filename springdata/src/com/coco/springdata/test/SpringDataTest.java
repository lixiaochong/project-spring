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

public class SpringDataTest {

	private ApplicationContext ctx = null;
	private PersonRepository personRepository = null;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personRepository = ctx.getBean(PersonRepository.class);
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
