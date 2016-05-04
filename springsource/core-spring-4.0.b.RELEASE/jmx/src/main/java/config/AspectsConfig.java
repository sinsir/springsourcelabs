package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import rewards.internal.aspects.RepositoryPerformanceMonitor;
import rewards.internal.monitor.jamon.JamonMonitorFactory;

@Configuration
@EnableAspectJAutoProxy

//	TODO-02: Activate annotation driven JMX. 
//	Deploy the project to the web server and start it. 
//	View the statistic using JConsole.

public class AspectsConfig {

	@Bean
	public JamonMonitorFactory monitorFactory() {
		return new JamonMonitorFactory();
	}
	
	@Bean
	public RepositoryPerformanceMonitor repositoryPerformanceMonitor() {
		return new RepositoryPerformanceMonitor(monitorFactory());
	}
	

}
