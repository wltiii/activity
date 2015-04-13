package activity.core

import groovy.transform.Canonical
import groovy.transform.Immutable;
import groovy.transform.ToString

import org.joda.time.DateTime

import com.fasterxml.jackson.annotation.*

@ToString
@Immutable
final class Conditions {
	
	int highTempInFahrenheit
	int lowTempInFahrenheit
	int precipitation
	int averageWindInMilesPerHour
	int maxWindInMilesPerHour
	int averageHumidity
	int maxHumidity

}
