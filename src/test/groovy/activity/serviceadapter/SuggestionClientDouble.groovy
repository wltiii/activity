package activity.serviceadapter

import groovy.json.JsonSlurper

class SuggestionClientDouble extends SuggestionClient {

	@Override
	def retrieveFromProvider(String apiPath) {

		if (apiPath ==~ "/suggestions/\\d+/\\d+/\\d+/\\d+/\\d+/\\d+/\\d+") {
			golfSuggestion()
		}
		else {
			throw new IllegalArgumentException("API path is invalid. Path is $apiPath")
		}
	}

	def golfSuggestion() {
		def data = """{"conditions":{"highTempInFahrenheit":82,"lowTempInFahrenheit":64,"precipitation":0,"maxWindInMilesPerHour":4,"averageWindInMilesPerHour":6,"averageHumidity":30,"maxHumidity":50}, "activity":"play golf"}"""

		new JsonSlurper().parseText(data)
	}

}
