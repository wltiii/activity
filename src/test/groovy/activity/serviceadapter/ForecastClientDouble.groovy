package activity.serviceadapter

import activity.core.Forecast

import groovy.json.JsonSlurper

class ForecastClientDouble extends ForecastClient {

	@Override
	def retrieveFromProvider(String apiPath) {
		if (apiPath ==~ "/forecast/states/(IL|MN)/cities/(Chicago|Minneapolis)") {
			chicagoForecast()
		}
		else {
			throw new IllegalArgumentException("API path is invalid. Path is $apiPath")
		}
	}

	private def chicagoForecast() {
		def data = """{"state":"IL","city":"Chicago","conditions":[{"date":"2012-07-03T00:00:00.000-05:00","highTempInFahrenheit":80,"lowTempInFahrenheit":55,"conditions":"Clear","rainTotalInInches":0,"snowTotalInInches":0,"maxWindInMilesPerHour":17,"averageWindInMilesPerHour":14,"averageHumidity":72,"maxHumidity":82},{"date":"2012-07-04T00:00:00.000-05:00","highTempInFahrenheit":72,"lowTempInFahrenheit":55,"conditions":"Partly Cloudy","rainTotalInInches":0,"snowTotalInInches":0,"maxWindInMilesPerHour":15,"averageWindInMilesPerHour":12,"averageHumidity":79,"maxHumidity":89},{"date":"2012-07-05T00:00:00.000-05:00","highTempInFahrenheit":70,"lowTempInFahrenheit":55,"conditions":"Partly Cloudy","rainTotalInInches":0,"snowTotalInInches":0,"maxWindInMilesPerHour":15,"averageWindInMilesPerHour":13,"averageHumidity":77,"maxHumidity":87},{"date":"2012-07-06T00:00:00.000-05:00","highTempInFahrenheit":72,"lowTempInFahrenheit":55,"conditions":"Partly Cloudy","rainTotalInInches":0,"snowTotalInInches":0,"maxWindInMilesPerHour":11,"averageWindInMilesPerHour":9,"averageHumidity":70,"maxHumidity":84},{"date":"2012-07-07T00:00:00.000-05:00","highTempInFahrenheit":73,"lowTempInFahrenheit":55,"conditions":"Fog","rainTotalInInches":0,"snowTotalInInches":0,"maxWindInMilesPerHour":11,"averageWindInMilesPerHour":9,"averageHumidity":75,"maxHumidity":85},{"date":"2012-07-08T00:00:00.000-05:00","highTempInFahrenheit":72,"lowTempInFahrenheit":55,"conditions":"Fog","rainTotalInInches":0,"snowTotalInInches":0,"maxWindInMilesPerHour":11,"averageWindInMilesPerHour":9,"averageHumidity":79,"maxHumidity":88},{"date":"2012-07-09T00:00:00.000-05:00","highTempInFahrenheit":70,"lowTempInFahrenheit":55,"conditions":"Partly Cloudy","rainTotalInInches":0,"snowTotalInInches":0,"maxWindInMilesPerHour":10,"averageWindInMilesPerHour":9,"averageHumidity":85,"maxHumidity":92},{"date":"2012-07-10T00:00:00.000-05:00","highTempInFahrenheit":75,"lowTempInFahrenheit":59,"conditions":"Partly Cloudy","rainTotalInInches":0,"snowTotalInInches":0,"maxWindInMilesPerHour":10,"averageWindInMilesPerHour":8,"averageHumidity":78,"maxHumidity":90},{"date":"2012-07-11T00:00:00.000-05:00","highTempInFahrenheit":73,"lowTempInFahrenheit":59,"conditions":"Partly Cloudy","rainTotalInInches":0,"snowTotalInInches":0,"maxWindInMilesPerHour":11,"averageWindInMilesPerHour":8,"averageHumidity":76,"maxHumidity":93},{"date":"2012-07-12T00:00:00.000-05:00","highTempInFahrenheit":73,"lowTempInFahrenheit":57,"conditions":"Clear","rainTotalInInches":0,"snowTotalInInches":0,"maxWindInMilesPerHour":11,"averageWindInMilesPerHour":8,"averageHumidity":71,"maxHumidity":89}]}"""

		new JsonSlurper().parseText(data)
	}

}
