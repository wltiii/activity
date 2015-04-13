  package activity.core

import activity.core.DailyConditions;

import com.fasterxml.jackson.annotation.JsonProperty

import groovy.transform.Immutable;
import groovy.transform.ToString

@ToString
@Immutable
final class ActivitySuggestion {
    @JsonProperty
	Conditions conditions
    @JsonProperty
	String activity
}
