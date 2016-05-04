package config;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;


/**
 * Configuration class for Persistence-specific objects, including
 * profile choices for Hibernate, JPA via Hibernate, and JPA via EclipseLink
 */
@Configuration
@Import({DbConfigJpa.class, DbConfigJpaELink.class, DbConfigHibernate.class})
public class DbConfig {

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
	
	/**
	 * Translates ORM exceptions to Spring Data Access Exceptions
	 */
//	@Bean
//	public BeanPostProcessor exceptionTranslator(){
//		return new PersistenceExceptionTranslationPostProcessor();
//	}
}
