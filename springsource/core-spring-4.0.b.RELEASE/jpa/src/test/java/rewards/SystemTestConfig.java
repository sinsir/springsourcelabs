package rewards;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import config.RewardsConfig;


@Configuration
@Import(RewardsConfig.class)
public class SystemTestConfig {

	
	/**
	 * Creates an in-memory "rewards" database populated 
	 * with test data for fast testing
	 */
	@Bean
	public DataSource dataSource(){
		return
			(new EmbeddedDatabaseBuilder())
			.addScript("classpath:rewards/testdb/schema.sql")
			.addScript("classpath:rewards/testdb/test-data.sql")
			.build();
	}	
	
	
	//	TODO-10: Define a LocalContainerEntityManagerFactoryBean with the name entityManagerFactory 
	//	Be sure to set the dataSource and jpaVendorAdaptor properties appropriately. 


	//	TODO-11: Define a JpaTransactionManager bean with the name transactionManager. 
	//	The @Bean method should accept a parameter of type EntityManagerFactory.
	//	Use this parameter when instantiating the JpaTransactionManager.
	//	Run the RewardNetworkTests, it should pass. 


}
