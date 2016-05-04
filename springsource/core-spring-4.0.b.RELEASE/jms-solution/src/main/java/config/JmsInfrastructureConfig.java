package config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsInfrastructureConfig {

	/**
	 * Create a ConnectionFactory using ActiveMQ:
	 */
	@Bean
	public ConnectionFactory connectionFactory(){
		return new ActiveMQConnectionFactory("vm://embedded?broker.persistent=false");
	}
	
	
	/**
	 *	Create a Queue for Dining objects using ActiveMQ: 
	 */
	@Bean
	public Destination diningQueue() {
		return new ActiveMQQueue("rewards.queue.dining");
	}
	
	
	/**
	 *	Create a Queue for Confirmation objects using ActiveMQ: 
	 */
	@Bean
	public Destination confirmationQueue() {
		return new ActiveMQQueue("rewards.queue.confirmation");
	}
		
}
