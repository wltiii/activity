package activity

import activity.health.TemplateHealthCheck
import activity.resources.ActivityResource
import spock.lang.Specification
import io.dropwizard.jersey.setup.JerseyEnvironment
import io.dropwizard.setup.Environment
import com.codahale.metrics.health.HealthCheckRegistry

class ActivitiesApplicationSpec extends Specification {

	def "validate run registers the resource"() {
		
		given: "environment has mock jersey and health check implementations"
		def mockJerseyEnvironment = Mock(JerseyEnvironment)
		def mockHealthCheckRegistry = Mock(HealthCheckRegistry)
		def mockEnvironment = Mock(Environment)
		mockEnvironment.healthChecks() >> mockHealthCheckRegistry
		mockEnvironment.jersey() >> mockJerseyEnvironment
		
		and: "a configuration for portland WA"
		ActivityConfiguration configuration = new ActivityConfiguration(defaultState: "WA", defaultCity: "Portland")
		
		and: "a resource expected to be registered"
		ActivityResource expectedResource = new ActivityResource("WA", "Portland")
		
		and: "a health check expected to be registered"
		TemplateHealthCheck expectedHealthCheck = new TemplateHealthCheck("WA")
		
		when: "run is called"
		new ActivitiesApplication().run(configuration, mockEnvironment)
		
		then: "expect resource and health check to be registered"
		1 * mockJerseyEnvironment.register(expectedResource)
		1 * mockHealthCheckRegistry.register("template", expectedHealthCheck)
	}

}
