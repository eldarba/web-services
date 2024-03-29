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
import javax.ws.rs.core.Response;

import database.Person;
import database.PersonDaoDb;

/*this demo is working with adatabase and Response*/
@Path("service4")
public class MyRESTfulService4 {

	private PersonDaoDb personDao = new PersonDaoDb();

	// inject servlet objects (request) in the service
	@Context
	private HttpServletRequest request;

	// http://localhost:8080/RESTful_ws/rest/service4/person-create
	// method = post
	// headers:
	// Content-Type: application/json
	// body:
	// {"id":"101", "name":"David", "age":"25"}
	@Path("person-create")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@POST
	public Response createPerson(Person person) {
		try {
			personDao.addPerson(person);
			return Response.status(Response.Status.OK).entity(person).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
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
	// optional headers:
	// Accept: application/xml
	// Accept: application/json
	@Path("person-read-all")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
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
