package client;

import core.ws.Calculator;
import core.ws.CalculatorService;
import core.ws.Person;

public class Test {

	public static void main(String[] args) {

		try {

			CalculatorService calcService = new CalculatorService();
			Calculator calcStub = calcService.getCalculatorPort();

			Person person = new Person();
			person.setName("David Katz");
			String msg = calcStub.greet(person);
			System.out.println(msg);
			int val;

			val = calcStub.add(100, 30);
			System.out.println(val);
			val = calcStub.sub(100, 30);
			System.out.println(val);
			val = calcStub.mul(100, 30);
			System.out.println(val);
			val = calcStub.div(100, 30);
			System.out.println(val);

		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("end of main");

	}
}
