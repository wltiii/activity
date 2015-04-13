      package activity.core

import com.fasterxml.jackson.annotation.JsonProperty

import groovy.transform.Immutable
import groovy.transform.ToString
import groovy.transform.Canonical
import groovy.transform.TupleConstructor

@ToString
@Immutable
@TupleConstructor
final class DailySuggestions {
	@JsonProperty
    private String state

	@JsonProperty
    private String city

    @JsonProperty
    private List<ActivitySuggestion> activities
}
