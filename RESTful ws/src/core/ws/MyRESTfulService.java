package core.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("service")
public class MyRESTfulService {

	// http://localhost:8080/RESTful_ws/rest/service/sum?a=5&b=9
	@Path("sum")
	@GET
	public String sum(@QueryParam("a") int a, @QueryParam("b") int b) {
		// Response resp = Response.status(Response.Status.OK).entity(String.valueOf(a +
		// b)).build();
		return String.valueOf(a + b);
	}

	// http://localhost:8080/RESTful_ws/rest/service/greet/John
	@Path("greet/{user}")
	@GET
	public String greet(@PathParam("user") String user) {
		return "Hello " + user;
	}

}
