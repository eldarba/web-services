package client;

import core.ws.StockQuote;
import core.ws.StockQuoteSoap;

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
		StockQuote service = new StockQuote();
		StockQuoteSoap stub = service.getStockQuoteSoap();
		String s = stub.getQuote("A");
		System.out.println(s);

		System.out.println("=====");

	}

}
