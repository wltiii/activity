package activity

import activity.core.DailySuggestions
import activity.core.ActivitySuggestion
import activity.resources.ActivityResource
import activity.serviceadapter.ForecastClientDouble
import activity.serviceadapter.SuggestionClientDouble
import specification.ResourceSpec
import spock.lang.Specification
import spock.lang.Shared

class ActivitiesApiSpec extends ResourceSpec {
	@Shared DailySuggestions suggestedActivities
	
	@Override
	void setUpResources() {
		jersey.addResource(new ActivityResource('IL', 'Chicago', new ForecastClientDouble(), new SuggestionClientDouble()))
	}
	
	def 'GET request returns suggestion for default city'() {
		when:
		def result = jersey.client().resource('/activities/states/IL/cities/Chicago').get(DailySuggestions) 
		
		then:
		result.city == 'Chicago'
		// TODO validate more than just the city name
//		result.activities == [new ActivitySuggestion("1997-07-14T13:30:00", "swimming"), new ActivitySuggestion("1997-07-15T13:30:00", "biking")]
	}

}
