package activity.core

import groovy.transform.Canonical
import groovy.transform.Immutable;
import groovy.transform.ToString

import org.joda.time.DateTime

import com.fasterxml.jackson.annotation.*

@ToString
@Immutable
final class DailyConditions {
	
	String date
	int highTempInFahrenheit
	int lowTempInFahrenheit
	String conditions
	int rainTotalInInches
	int snowTotalInInches
	int averageWindInMilesPerHour
	int maxWindInMilesPerHour
	int averageHumidity
	int maxHumidity

}
