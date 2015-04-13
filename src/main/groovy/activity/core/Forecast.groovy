package activity.core

import com.fasterxml.jackson.annotation.JsonProperty

import groovy.transform.Immutable;
import groovy.transform.ToString
import groovy.transform.Canonical

@ToString
@Immutable
final class Forecast {
    @JsonProperty
    String state
    @JsonProperty
    String city
    @JsonProperty
    List<DailyConditions> conditions

}
