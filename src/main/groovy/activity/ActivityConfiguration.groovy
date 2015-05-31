package activity

import java.util.List

import groovy.transform.Immutable
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import io.dropwizard.Configuration

import org.hibernate.validator.constraints.NotEmpty

@Immutable
@EqualsAndHashCode
@ToString
public class ActivityConfiguration extends Configuration {
    @NotEmpty
    String defaultState = "WA"

    @NotEmpty
    String defaultCity = "Seattle"

}
