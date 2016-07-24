package com.coco.springdata;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


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
	
	//查询 id 值最大的那个 Person
	//使用 @Query 注解可以自定义 JPQL 语句以实现更灵活的查询
	@Query("SELECT p FROM Person p WHERE p.id = (SELECT MAX(p2.id) FROM Person p2)")
	Person getMaxIdPerson();
	
	//为 @Query 注解传递参数的方式1: 使用占位符.
	@Query("SELECT p FROM Person p WHERE p.lastName = ?1 and p.email = ?2")
	List<Person> testQueryAnnotationParams1(String lastName, String email);
	
	//为 @Query 注解传递参数的方式2: 命名参数的方式. 
	@Query("SELECT p FROM Person p WHERE p.lastName =:lastName and p.email =:email")
	List<Person> testQueryAnnotationParams2(@Param("email") String email,@Param("lastName") String lastName);
	
	//SpringData 允许在占位符上添加 %%.
	@Query("SELECT p FROM Person p WHERE p.lastName like %?1% or p.email like %?2%")
	List<Person> testQueryAnnotationLikeParam(String lastName,String email);
	
	//SpringData 允许在占位符上添加 %%.
	@Query("SELECT p FROM Person p WHERE p.lastName like %:lastName or p.email like %:email%" )
	List<Person> testQueryAnnotationLikeParam2(@Param("email") String email,@Param("lastName") String lastName);
	
	//设置 nativeQuery=true 即可以使用原生的 SQL 查询
	@Query(value="SELECT count(id) FROM jpa_persons",nativeQuery=true)
	long getTotalPersonCounts();
	
	//可以通过自定义的 JPQL 完成 UPDATE 和 DELETE 操作. 注意: JPQL 不支持使用 INSERT
	//在 @Query 注解中编写 JPQL 语句, 但必须使用 @Modifying 进行修饰. 以通知 SpringData, 这是一个 UPDATE 或 DELETE 操作
	//UPDATE 或 DELETE 操作需要使用事务, 此时需要定义 Service 层. 在 Service 层的方法上添加事务操作. 
	//默认情况下, SpringData 的每个方法上有事务, 但都是一个只读事务. 他们不能完成修改操作!
	@Modifying
	@Query("UPDATE Person p SET p.email =:email WHERE p.id = :id")
	void updatePersonEmail(@Param("email")String email,@Param("id") Integer id);
	
	
	
	
	
}
