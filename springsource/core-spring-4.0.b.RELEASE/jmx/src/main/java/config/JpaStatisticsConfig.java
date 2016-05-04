package config;


import javax.persistence.EntityManagerFactory;

import org.hibernate.jmx.StatisticsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("jpa")
public class JpaStatisticsConfig {

	@Bean
	public Object jpaStatistics(EntityManagerFactory emf) {
		StatisticsService svc = new StatisticsService();
		svc.setStatisticsEnabled(true);
		return svc;
	}
}
