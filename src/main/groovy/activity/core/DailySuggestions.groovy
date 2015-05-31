package activity.core

import groovy.transform.Canonical

@Canonical
final class DailySuggestions {
    String state
    String city
    List<ActivitySuggestion> activities
}
