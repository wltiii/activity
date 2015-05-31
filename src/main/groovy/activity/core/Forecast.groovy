package activity.core

import groovy.transform.Canonical

@Canonical
final class Forecast {
    String state
    String city
    List<DailyConditions> conditions

}
