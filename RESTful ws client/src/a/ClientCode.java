package a;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ClientCode {

	public static void main(String[] args) {

		ClientConfig clientConfig = new DefaultClientConfig();
		Client client = Client.create(clientConfig);
		WebResource service = client.resource("http://localhost:8080/RESTful_ws/rest/service2/");

		String response;

		// CREATE
		service.path("person.create/101/Avi/25").post();
		service.path("person.create/102/Yosi/32").post();
		service.path("person.create/103/Lea/17").post();

		// READ
		response = service.path("person.read/102").accept(MediaType.APPLICATION_XML).get(String.class);
		System.out.println(response);

		// UPDATE
		service.path("person.update/102/John/50").put();

		// DELETE
		service.path("person.delete/102").delete();

		// READ all
		response = service.path("person.read.all").accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(response);

	}

}
