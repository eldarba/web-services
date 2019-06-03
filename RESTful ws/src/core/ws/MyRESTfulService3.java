package core.ws;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import database.Person;
import database.PersonDaoDb;

/*this demo is working with adatabase*/
@Path("service3")
public class MyRESTfulService3 {

	private PersonDaoDb personDao = new PersonDaoDb();

	@Context
	private HttpServletRequest request;

	// http://localhost:8080/RESTful_ws/rest/service3/person-create
	// method = post
	// headers:
	// Content-Type: application/json
	// body:
	// {"id":"101", "name":"David", "age":"25"}
	@Path("person-create")
	@Consumes({ MediaType.APPLICATION_JSON })
	@POST
	public Void createPerson(Person person) throws SQLException {
		personDao.addPerson(person);
		return null;
	}

	// http://localhost:8080/RESTful_ws/rest/service3/person-read/101
	@Path("person-read/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Person readPerson(@PathParam("id") int personId) throws SQLException {
		Person person = personDao.getPerson(personId);
		return person;
	}

	// http://localhost:8080/RESTful_ws/rest/service3/person-read-all
	@Path("person-read-all")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Person> readPeople() throws SQLException {
		return personDao.getAllPeople();
	}

	// http://localhost:8080/RESTful_ws/rest/service3/person-update
	// method = put
	// headers:
	// Content-Type: application/json
	// body:
	// {"id":"101", "name":"David", "age":"25"}
	@Path("person-update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@PUT
	public String updatePerson(Person person) throws SQLException {
		return personDao.updatePerson(person);
	}

	// http://localhost:8080/RESTful_ws/rest/service3/person-delete/101
	@Path("person-delete/{id}")
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	public String deletePerson(@PathParam("id") int personId) throws SQLException {
		String html = "<div>" + personDao.deletePerson(personId) + "</div>";
		return html;
	}

	// http://localhost:8080/RESTful_ws/rest/service3/person-delete-all
	@Path("person-delete-all")
	@DELETE
	public String deleteAll() throws SQLException {
		return personDao.deleteAll();
	}
}
