package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import rewards.internal.account.AccountRepository;
import rewards.internal.account.JpaAccountRepository;
import rewards.internal.restaurant.JpaRestaurantRepository;
import rewards.internal.restaurant.RestaurantRepository;
import accounts.AccountManager;
import accounts.internal.JpaAccountManager;

@Configuration
@Profile({"jpa","jpa-elink"})
public class AppConfigJpa {

	
	@Bean
	public RestaurantRepository restaurantRepository(){
		return new JpaRestaurantRepository();
	}
	
	@Bean
	public AccountRepository accountRepository(){
		return new JpaAccountRepository();
	}
	
	@Bean
	public AccountManager accountManager(){
		return new JpaAccountManager();
	}

}
