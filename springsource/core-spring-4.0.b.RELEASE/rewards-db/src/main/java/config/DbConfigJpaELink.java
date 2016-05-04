package config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@Profile("jpa-elink")
public class DbConfigJpaELink {

	@Autowired
	DataSource dataSource;
	
	@Bean
	public PlatformTransactionManager transactionManager(){
		return new JpaTransactionManager();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		JpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();

		Properties props = new Properties();
		props.setProperty("eclipselink.logging.level", "FINE");
		props.setProperty("eclipselink.logging.parameters", "true");
		props.setProperty("eclipselink.weaving", "false");		
		
		LocalContainerEntityManagerFactoryBean emfb = 
			new LocalContainerEntityManagerFactoryBean();
		emfb.setPackagesToScan("rewards.internal");
		emfb.setJpaProperties(props);
		emfb.setJpaVendorAdapter(adapter);
		emfb.setDataSource(dataSource);
		
		return emfb;
	}
}
