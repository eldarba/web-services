package core.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import database.Person;

@Path("service2")
public class MyRESTfulService2 {

	/*
	 * CRUD Actions
	 * 
	 * create = http request method: POST
	 * 
	 * read = http request method: GET
	 * 
	 * update = http request method: PUT
	 * 
	 * delete = http request method: DELETE
	 */

	private static List<Person> persons = new ArrayList<>();

	@Path("person.create/{id}/{name}/{age}")
	@POST
	public Void createPerson(@PathParam("id") int id, @PathParam("name") String name, @PathParam("age") int age) {
		Person p = new Person();
		p.setId(id);
		p.setName(name);
		p.setAge(age);
		persons.add(p);
		System.out.println(persons);
		return null;
	}

	@Path("person.read/{id}")
	@GET
	public Person readPerson(@PathParam("id") int id) {
		Person p = new Person();
		p.setId(id);
		int index = persons.indexOf(p);

		if (index != -1) {
			return persons.get(index);
		} else {
			return null;
		}
	}

	@Path("person.read.all")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Person> readPeople() {
		return persons;
	}

	@Path("person.update/{id}/{name}/{age}")
	@PUT
	public Void updatePerson(@PathParam("id") int id, @PathParam("name") String name, @PathParam("age") int age) {
		Person p = new Person();
		p.setId(id);
		p.setName(name);
		p.setAge(age);
		int index = persons.indexOf(p);

		if (index != -1) {
			Person p2 = persons.get(index);
			p2.setName(p.getName());
			p2.setAge(p.getAge());
		}

		return null;
	}

	@Path("person.delete/{id}")
	@DELETE
	public Void deletePerson(@PathParam("id") int id) {
		Person p = new Person();
		p.setId(id);
		persons.remove(p);
		return null;
	}
}
