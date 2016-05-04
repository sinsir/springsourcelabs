package config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import rewards.internal.account.AccountRepository;
import rewards.internal.account.HibernateAccountRepository;
import rewards.internal.restaurant.HibernateRestaurantRepository;
import rewards.internal.restaurant.RestaurantRepository;
import accounts.AccountManager;
import accounts.internal.HibernateAccountManager;

@Configuration
@Profile({"hibernate"})
public class AppConfigHibernate {

	@Autowired
	SessionFactory sessionFactory;
	
	@Bean
	public RestaurantRepository restaurantRepository(){
		return new HibernateRestaurantRepository(sessionFactory);
	}
	
	@Bean
	public AccountRepository accountRepository(){
		return new HibernateAccountRepository(sessionFactory);
	}
	
	@Bean
	public AccountManager accountManager(){
		return new HibernateAccountManager(sessionFactory);
	}
	
}
