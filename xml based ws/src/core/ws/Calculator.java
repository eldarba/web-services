package core.ws;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class Calculator {

	public int add(int a, int b) {
		int res = a + b;
		return res;
	}

	public String greet(Person person) {
		String name = person.getName();
		return "Hello " + name;
	}
	
	public int sub(int a, int b) {
		int res = a - b;
		return res;
	}

	public int mul(int a, int b) {
		int res = a * b;
		return res;
	}

	public int div(int a, int b) {
		int res = a / b;
		return res;
	}


}
