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
				println "forecastCondition is $forecastCondition"
				def dc = new DailyConditions(forecastCondition)
				println "dailyCondition is $dc"
				dc
				// new DailyConditions(
				// 	date: forecastCondition.date,
				// 	highTempInFahrenheit: forecastCondition.highTempInFahrenheit as Integer,
				// 	lowTempInFahrenheit: forecastCondition.lowTempInFahrenheit as Integer,
				// 	conditions: forecastCondition.conditions,
				// 	rainTotalInInches: forecastCondition.rainTotalInInches as Integer,
				// 	snowTotalInInches: forecastCondition.snowTotalInInches as Integer,
				// 	averageWindInMilesPerHour: forecastCondition.averageWindInMilesPerHour as Integer,
				// 	maxWindInMilesPerHour: forecastCondition.maxWindInMilesPerHour as Integer,
				// 	averageHumidity: forecastCondition.averageHumidity as Integer,
				// 	maxHumidity: forecastCondition.maxhumidity as Integer
				// )
			}

		new Forecast(city: json.city, state: json.state, conditions: conditions)

	}

	private String buildPath(String state, String city) {
		def apipath = "/forecast/states/${state}/cities/${city}"
		apipath
	}

	def retrieveFromProvider(String apiPath) {
		println "ForecastClient.retrieveFromProvider($apiPath)"
		def client = new RESTClient('http://127.0.0.1:9002')
		def resp = client.get( path : apiPath)
		println "ForecastClient.retrieveFromProvider.resp --> $resp"
		resp.data
	}

}
