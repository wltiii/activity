package activity.resources

import activity.core.Conditions
import activity.core.DailyConditions
import activity.core.DailySuggestions
import activity.core.ActivitySuggestion
import activity.core.Forecast
import activity.serviceadapter.ForecastClient
import activity.serviceadapter.SuggestionClient

import com.google.common.base.Optional
import com.codahale.metrics.annotation.Timed

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.PathParam
import javax.ws.rs.core.MediaType

import groovy.transform.EqualsAndHashCode;
import groovy.transform.TailRecursive
import groovy.transform.ToString;
import groovy.json.JsonSlurper

import java.util.concurrent.atomic.AtomicLong

@Path("/activities")
@Produces(MediaType.APPLICATION_JSON)
@EqualsAndHashCode
@ToString
public final class ActivityResource {
	final String defaultState
	final String defaultCity
	
	private final ForecastClient forecastClient
	private final SuggestionClient suggestionClient
	

    public ActivityResource(String defaultState, String defaultCity) {
        this(defaultState, defaultCity, new ForecastClient(), new SuggestionClient())
    }

    public ActivityResource(String defaultState, String defaultCity, ForecastClient forecastClient, SuggestionClient suggestionClient) {
        this.defaultState = defaultState
        this.defaultCity = defaultCity
		this.forecastClient = forecastClient
		this.suggestionClient = suggestionClient
    }

    @GET
    @Timed
	@Path("states/{states}/cities/{cities}")
    public DailySuggestions suggestActivity(@PathParam("states") String state, @PathParam("cities") String city) {
		
		final Forecast forecast = forecastClient.retrieveConditions(state, city)
	
		final List<ActivitySuggestion> dailySuggestions = buildDailySuggestions(forecast.conditions, new ArrayList<ActivitySuggestion>())
		
        new DailySuggestions(state: state, city: city, activities: dailySuggestions)
    }

	@TailRecursive	
	List<ActivitySuggestion> buildDailySuggestions(List<DailyConditions> dailyConditions, List<ActivitySuggestion> dailySuggestions) {
		if (!dailyConditions) {
			dailySuggestions
		}
		else {
			final DailyConditions dayCondition = dailyConditions.head()
			final int totalPrecipitation = dayCondition.rainTotalInInches + dayCondition.snowTotalInInches
			final Conditions conditions = new Conditions(dayCondition.highTempInFahrenheit,dayCondition.lowTempInFahrenheit,totalPrecipitation,dayCondition.averageWindInMilesPerHour,dayCondition.maxWindInMilesPerHour,dayCondition.averageHumidity,dayCondition.maxHumidity)
			final ActivitySuggestion activitySuggestion = suggestionClient.retrieveSuggestion(conditions)
			buildDailySuggestions(dailyConditions.tail(), dailySuggestions << activitySuggestion)
		}
	}
	
}
