package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({AppConfig.class,DbConfig.class})

//	TODO-03: Import the bean configuration file (resource) containing the security configuration.
//	Save all work and restart the server.  You should be able to access the homepage,
//	but you should see a 404 when clicking on "View account list".-->

@EnableTransactionManagement
public class RootConfig {

}
