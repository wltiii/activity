package activity.serviceadapter

import activity.core.ActivitySuggestion;
import activity.core.Conditions

import com.fasterxml.jackson.databind.ObjectMapper

import groovyx.net.http.*

class SuggestionClient {

	ActivitySuggestion retrieveSuggestion(Conditions conditions) {
		String apiPath = buildPath(conditions)
		
		String json = retrieveFromProvider(apiPath)
		
		ObjectMapper mapper = new ObjectMapper()
		
		ActivitySuggestion suggestion = mapper.readValue(json, ActivitySuggestion.class)

	}

	private String buildPath(Conditions conditions) {
		def apipath = "/suggestions/${conditions.highTempInFahrenheit}/${conditions.lowTempInFahrenheit}/${conditions.precipitation}/${conditions.averageWindInMilesPerHour}/${conditions.maxWindInMilesPerHour}/${conditions.averageHumidity}/${conditions.maxHumidity}"
		apipath
	}

	def retrieveFromProvider(String apiPath) {
		def client = new RESTClient('http://127.0.0.1:9004')
		def resp = client.get( path : apiPath)
		resp
	}

}
