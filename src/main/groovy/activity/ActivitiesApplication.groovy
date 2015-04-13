package activity

import activity.resources.ActivityResource
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import activity.health.TemplateHealthCheck

public class ActivitiesApplication extends Application<ActivityConfiguration> {
    public static void main(String[] args) throws Exception {
        new ActivitiesApplication().run(args)
    }

    @Override
    public String getName() {
        return "activities"
    }

    @Override
    public void initialize(Bootstrap<ActivityConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(ActivityConfiguration configuration,
                    Environment environment) {
	    final ActivityResource resource = new ActivityResource(
	        configuration.getDefaultState(),
	        configuration.getDefaultCity()
	    )
		
		
	    final TemplateHealthCheck healthCheck =
	        new TemplateHealthCheck(configuration.getDefaultState())
			
	    environment.healthChecks().register("template", healthCheck)
	    environment.jersey().register(resource)
    }

}