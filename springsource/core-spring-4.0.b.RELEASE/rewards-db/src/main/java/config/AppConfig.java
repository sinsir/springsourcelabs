package config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import rewards.internal.reward.JdbcRewardRepository;
import rewards.internal.reward.RewardRepository;

@Configuration
@Import({AppConfigJpa.class,AppConfigHibernate.class})
public class AppConfig {

	@Autowired
	DataSource dataSource;
		
	@Bean
	public RewardRepository rewardRepository(){
		return new JdbcRewardRepository(dataSource);
	}
	

}
