package activity.serviceadapter

import activity.core.Forecast
import spock.lang.Specification

class ForecastClientSpec extends Specification {
	
	ForecastClient client = new ForecastClientDouble()
	
	def 'spec retrieves forecast'() {
		when:
		Forecast forecast = client.retrieveConditions("IL", "Chicago")
		
		then:
		forecast.state == "IL"
		forecast.city == "Chicago"
	}

}
