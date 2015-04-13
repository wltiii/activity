package activity.resources

import activity.core.DailySuggestions
import activity.core.ActivitySuggestion
import activity.serviceadapter.ForecastClientDouble
import activity.serviceadapter.SuggestionClientDouble
import spock.lang.Specification
import com.google.common.base.Optional

class ActivityResourceSpec extends Specification {
	
	ActivityResource activityResource
	
	def setup() {
		activityResource = new ActivityResource('IL', 'Chicago', new ForecastClientDouble(), new SuggestionClientDouble())
	}
	
	def 'suggestActivity returns suggestion for default city and date'() {
		when:
		def suggestedActivities = activityResource.suggestActivity('IL', 'Chicago')
		
		then:
		suggestedActivities.city == 'Chicago'
//		suggestedActivities.activities == [new ActivitySuggestion("2015-07-14T13:30:00", "rollerblading")]
	}

	def 'suggestActivity returns suggestion for requested city and dates'() {
		when:
		def suggestedActivities = activityResource.suggestActivity('MN', 'Minneapolis')
		
		then:
		suggestedActivities.city == 'Minneapolis'
//		suggestedActivities.activities == [new ActivitySuggestion("1997-07-14T13:30:00", "swimming"), new ActivitySuggestion("1997-07-15T13:30:00", "biking")]
	}

}
