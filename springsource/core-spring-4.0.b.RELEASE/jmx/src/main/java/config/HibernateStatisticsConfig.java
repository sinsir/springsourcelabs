package config;

import org.hibernate.SessionFactory;
import org.hibernate.jmx.StatisticsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("hibernate")
public class HibernateStatisticsConfig {

	@Bean
	public Object hibernateStatistics(SessionFactory sf) {
		StatisticsService svc = new StatisticsService();
		svc.setStatisticsEnabled(true);
		svc.setSessionFactory(sf);
		return svc;
	}
}
