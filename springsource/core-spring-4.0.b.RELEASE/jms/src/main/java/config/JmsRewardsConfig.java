package config;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.AbstractMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import rewards.RewardNetwork;

@Configuration
public class JmsRewardsConfig {

	@Autowired ConnectionFactory connectionFactory;
	@Autowired RewardNetwork rewardNetwork;
	
	//	TODO-06: Modify these two bean methods below to create a listener
	//	and a listener container to process Dining messages.
	//	The listener should delegate to the only method on the rewardNetwork bean.
	//	The listener should place the method's return value on the confirmations queue defined earlier.
	//	The listener container should listen to the dining queue defined earlier.
	//	(Hint: Use the queue names you provided, not the IDs of the beans).
	
	@Bean
	public MessageListenerAdapter rewardListener () {
		return null;
    }	

	
	@Bean
	public AbstractMessageListenerContainer listenerContainer() {
		return null;
	}	
	
}
