package activity.serviceadapter

import activity.core.Forecast
import activity.core.DailyConditions
import groovy.json.JsonSlurper
import groovy.transform.TailRecursive
import groovyx.net.http.*

class ForecastClient {

	Forecast retrieveConditions(final String state, final String city) {
		String apiPath = buildPath(state, city)

		final def json = retrieveFromProvider(apiPath)

		Forecast f = new Forecast(city: json.city, state: json.state)

		final List<DailyConditions> conditions =
			json.conditions.collect { forecastCondition ->
				new DailyConditions(forecastCondition)
			}

		new Forecast(city: json.city, state: json.state, conditions: conditions)

	}

	private String buildPath(String state, String city) {
		def apipath = "/forecast/states/${state}/cities/${city}"
		apipath
	}

	def retrieveFromProvider(String apiPath) {
		def client = new RESTClient('http://127.0.0.1:9002')
		def resp = client.get( path : apiPath)
		resp.data
	}

}
