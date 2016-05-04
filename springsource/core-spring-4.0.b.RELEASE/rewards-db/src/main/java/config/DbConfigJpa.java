package config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * JPA-Specific profile for DB-specific objects.  
 */
@Configuration
@Profile("jpa")
public class DbConfigJpa {

	@Autowired
	DataSource dataSource;
	
	/**
	 * Transaction Manager For JPA
	 */
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception {
		return new JpaTransactionManager();
	}
	
	
	/**
	 * EntityManagerFactory using Hibernate for the implementation.
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.format_sql", "true");
		
		LocalContainerEntityManagerFactoryBean emfb = 
			new LocalContainerEntityManagerFactoryBean();
		emfb.setPackagesToScan("rewards.internal");
		emfb.setJpaProperties(props);
		emfb.setJpaVendorAdapter(adapter);
		emfb.setDataSource(dataSource);
		
		return emfb;
	}
}
