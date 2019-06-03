package core.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import database.Person;

@Path("service1")
public class MyRESTfulService1 {

	private static List<Person> persons = new ArrayList<>();

	// http://localhost:8080/RESTful_ws/rest/service1/sum?a=5&b=7
	@Path("sum")
	@GET
	public String sum(@QueryParam("a") int a, @QueryParam("b") int b) {
		int resVal = a + b;
		String res = String.valueOf(resVal);
		return res;
	}

	// http://localhost:8080/RESTful_ws/rest/service1/greet/Sam
	@Path("greet/{name}")
	@GET
	public String greet(@PathParam("name") String user) {
		return "Hello " + user;
	}

	// http://localhost:8080/RESTful_ws/rest/service1/addperson/101/Dan/22
	@Path("addperson/{id}/{name}/{age}")
	@GET
	public Void addPerson(@PathParam("id") int id, @PathParam("name") String name, @PathParam("age") int age) {
		Person p = new Person();
		p.setId(id);
		p.setName(name);
		p.setAge(age);
		persons.add(p);
		System.out.println(persons);
		return null;
	}

	// http://localhost:8080/RESTful_ws/rest/service1/get.people
	@Path("get.people")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Person> getPeople() {
		return persons;
	}

}

/*
 * method legitimate return types:
 * 
 * 1. String
 * 
 * 2. objects of classes with @XmlRootElement
 * 
 * 3. Collection
 * 
 * 4. Void (the method ends with - return null;)
 */
