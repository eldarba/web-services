package client;

import core.ws.GlobalWeather;
import core.ws.GlobalWeatherSoap;

public class Test1 {

	/*
	 * Global Weather Soap:
	 * 
	 * This WSDL generates 2 service operations:
	 * 
	 * 1. GetCitiesByCountry (to get a list of all the cities for any country)
	 * 2. GetWeather (to get weather for any city/country combination).
	 * 
	 * WSDL URL: http://www.webservicex.com/globalweather.asmx?wsdl
	 */

	public static void main(String[] args) {
		GlobalWeather service = new GlobalWeather();
		GlobalWeatherSoap stub = service.getGlobalWeatherSoap();
		String s = stub.getCitiesByCountry("japan");
		System.out.println(s);

		System.out.println("=====");

		s = stub.getWeather("Ichikawa", "Japan");
		System.out.println(s);

	}

}
