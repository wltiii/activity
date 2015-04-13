package activity.serviceadapter

import activity.core.ActivitySuggestion
import activity.core.Conditions
import spock.lang.Specification

class SuggestionClientSpec extends Specification {
	
	SuggestionClient client = new SuggestionClientDouble()

	def 'spec retrieves suggestion'() {
		when: "Weather is perfect for golf"
 		Conditions conditions = new Conditions(82, 64, 0, 4, 6, 30, 50)
		ActivitySuggestion suggestion = client.retrieveSuggestion(conditions)
		
		then: "playing golf is suggested"
		suggestion.activity == "play golf"
	}

}
