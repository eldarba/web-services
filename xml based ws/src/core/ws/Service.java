package core.ws;

import javax.xml.ws.Endpoint;

public class Service {

	public static void main(String[] args) {

		String url = "http://localhost:8080/calc";
		Calculator calc = new Calculator();
		System.out.println("wsdl is published at: " + url + "?wsdl");

		Endpoint.publish(url, calc);
	}

}
