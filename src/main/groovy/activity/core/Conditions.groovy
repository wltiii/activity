package activity.core

import groovy.transform.Canonical

@Canonical
final class Conditions {

	int highTempInFahrenheit
	int lowTempInFahrenheit
	int precipitation
	int averageWindInMilesPerHour
	int maxWindInMilesPerHour
	int averageHumidity
	int maxHumidity

}
