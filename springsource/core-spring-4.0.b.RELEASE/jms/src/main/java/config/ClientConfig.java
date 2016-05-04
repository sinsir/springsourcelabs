package config;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.AbstractMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import rewards.jms.client.DiningBatchProcessor;
import rewards.jms.client.JmsDiningBatchProcessor;
import rewards.jms.client.RewardConfirmationLogger;

@Configuration
public class ClientConfig {

	@Autowired ConnectionFactory connectionFactory;
	
	@Bean
	public DiningBatchProcessor diningBatchProcessor(JmsTemplate jmsTemplate) {
		JmsDiningBatchProcessor processor = new JmsDiningBatchProcessor();
		return processor;
	}

	//	TODO-05: Define a JmsTemplate.
	//	Provide it with a reference to the ConnectionFactory and the dining destination. 
	//	Inject it into the batch processor bean (above). 
	

	
	/**
	 *	Create an object that knows how to log dining confirmations: 
	 */
	@Bean
	public RewardConfirmationLogger logger() {
		return new RewardConfirmationLogger();
	}
	

	//	TODO-07: Modify the two bean methods below to create a second
	//	listener and listener container to process Confirmation messages.
	//	The listener should delegate to the only method on the RewardConfirmationLogger bean.
	//	The listener container should listen to the confirmation queue defined earlier.  
	//	(Hint: Indicate the queue name, not the bean ID.)
	//	Delegate to the 'log' method of the confirmationLogger. -->	

	@Bean
	public MessageListenerAdapter loggerListener () {
       return null;
    }	

	
	@Bean
	public AbstractMessageListenerContainer clientListenerContainer () {
		return null;
	}	
}
