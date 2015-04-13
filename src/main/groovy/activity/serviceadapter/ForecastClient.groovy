package activity.serviceadapter

import activity.core.Forecast
import com.fasterxml.jackson.databind.ObjectMapper
import groovyx.net.http.*

class ForecastClient {

	Forecast retrieveConditions(final String state, final String city) {
		String apiPath = buildPath(state, city)
		
		String json = retrieveFromProvider(apiPath)
		
		ObjectMapper mapper = new ObjectMapper()
		
		Forecast forecast = mapper.readValue(json, Forecast.class)

	}
	
	private String buildPath(String state, String city) {
		def apipath = "/forecast/states/${state}/cities/${city}"
		apipath
	}

	def retrieveFromProvider(String apiPath) {
		println "ForecastClient.retrieveFromProvider($apiPath)"
		def client = new RESTClient('http://127.0.0.1:9002')
		def resp = client.get( path : apiPath)
		resp
	}

}
