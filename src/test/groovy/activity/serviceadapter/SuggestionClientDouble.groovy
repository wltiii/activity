package activity.serviceadapter

import activity.core.ActivitySuggestion
import activity.core.Conditions
import activity.core.DailyConditions

class SuggestionClientDouble extends SuggestionClient {

	@Override
	String retrieveFromProvider(String apiPath) {
		
		if (apiPath ==~ "/suggestions/\\d+/\\d+/\\d+/\\d+/\\d+/\\d+/\\d+") {
			golfSuggestion()
		}
		else {
			throw new IllegalArgumentException("API path is invalid. Path is $apiPath")
		}
	}

	private String golfSuggestion() {
		"""{"conditions":{"highTempInFahrenheit":82,"lowTempInFahrenheit":64,"precipitation":0,"maxWindInMilesPerHour":4,"averageWindInMilesPerHour":6,"averageHumidity":30,"maxHumidity":50}, "activity":"play golf"}"""
	}
	
}
