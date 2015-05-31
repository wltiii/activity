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
			buildDailyConditions(json.conditions, 0, new ArrayList<DailyConditions>())

		new Forecast(city: json.city, state: json.state, conditions: conditions)

	}

	private String buildPath(String state, String city) {
		def apipath = "/forecast/states/${state}/cities/${city}"
		apipath
	}

	@TailRecursive
	private List<DailyConditions> buildDailyConditions(forecastedConditions, int count, List<DailyConditions> dailyConditions) {
		if (count == forecastedConditions.size) {
			dailyConditions
		}
		else {
			def condition = new DailyConditions(
				date: forecastedConditions[count].date,
				highTempInFahrenheit: forecastedConditions[count].highTempInFahrenheit as Integer,
				lowTempInFahrenheit: forecastedConditions[count].lowTempInFahrenheit as Integer,
				conditions: forecastedConditions[count].conditions,
				rainTotalInInches: forecastedConditions[count].rainTotalInInches as Integer,
				snowTotalInInches: forecastedConditions[count].snowTotalInInches as Integer,
				averageWindInMilesPerHour: forecastedConditions[count].averageWindInMilesPerHour as Integer,
				maxWindInMilesPerHour: forecastedConditions[count].maxWindInMilesPerHour as Integer,
				averageHumidity: forecastedConditions[count].averageHumidity as Integer,
				maxHumidity: forecastedConditions[count].maxhumidity as Integer
			)
			buildDailyConditions(forecastedConditions, ++count, dailyConditions << condition)
		}
	}

	def retrieveFromProvider(String apiPath) {
		println "ForecastClient.retrieveFromProvider($apiPath)"
		def client = new RESTClient('http://127.0.0.1:9002')
		def resp = client.get( path : apiPath)
		println "ForecastClient.retrieveFromProvider.resp --> $resp"
		resp.data
	}

}
