package activity.core

import groovy.transform.Canonical

@Canonical
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
