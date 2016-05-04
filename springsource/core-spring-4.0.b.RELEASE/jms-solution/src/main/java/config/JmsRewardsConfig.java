package config;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.AbstractMessageListenerContainer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import rewards.RewardNetwork;

@Configuration
public class JmsRewardsConfig {

	@Autowired RewardNetwork rewardNetwork;
	
	/**
	 * Create a Listener adapter that converts JMS messages into calls
	 * to the rewardNetwork's rewardAccountFor method.  Returned values
	 * will be converted to messages and placed on the confirmation queue:
	 */
	@Bean
	public MessageListenerAdapter rewardListener () {
		MessageListenerAdapter adapter = 
			new MessageListenerAdapter(rewardNetwork);
		adapter.setDefaultListenerMethod("rewardAccountFor");
		adapter.setDefaultResponseQueueName("rewards.queue.confirmation");
		return adapter;
    }	

	
	/**
	 * Given a ConnectionFactory, create a ListenerContainer that listens
	 * to messages on the dining queue and sends them to the rewardListener:
	 */
	@Bean
	public AbstractMessageListenerContainer listenerContainer (
		ConnectionFactory connectionFactory ) {
		DefaultMessageListenerContainer container = 
			new DefaultMessageListenerContainer();
		container.setConnectionFactory( connectionFactory );
		container.setDestinationName("rewards.queue.dining");
		container.setMessageListener( rewardListener() );
		return container;
	}	
	
}
