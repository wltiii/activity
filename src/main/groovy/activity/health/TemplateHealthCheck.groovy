package activity.health

import groovy.transform.EqualsAndHashCode;
import groovy.transform.Immutable;
import groovy.transform.ToString;

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result

@EqualsAndHashCode
@ToString
class TemplateHealthCheck extends HealthCheck {
	final String template

	public TemplateHealthCheck(String template) {
		this.template = template
	}

	@Override
	protected Result check() throws Exception {
		final String saying = String.format(template, "TEST")
		if (!saying.contains("TEST")) {
			return Result.unhealthy("template doesn't include a name")
		}
		return Result.healthy()
	}
}
