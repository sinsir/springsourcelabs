package rewards.jms.client;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.ClientConfig;
import config.JmsInfrastructureConfig;
import config.JmsRewardsConfig;
import config.RootConfig;

import rewards.Dining;

/**
 * Tests the Dining batch processor
 */
@ActiveProfiles("jpa")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	// See the inner class below
public class DiningBatchProcessorTests {

	/**
	 *	Configuration for the entire system: 
	 */
	@Configuration
	@Import({
		RootConfig.class, 
		JmsInfrastructureConfig.class, 
		JmsRewardsConfig.class,
		ClientConfig.class})
	public static class Config{}	
	
	@Autowired
	private DiningBatchProcessor diningBatchProcessor;

	@Autowired
	private RewardConfirmationLogger confirmationLogger;

	@Test
	public void testBatch() throws Exception {
		Dining dining1 = Dining.createDining("80.93", "1234123412341234", "1234567890");
		Dining dining2 = Dining.createDining("56.12", "1234123412341234", "1234567890");
		Dining dining3 = Dining.createDining("32.64", "1234123412341234", "1234567890");
		Dining dining4 = Dining.createDining("77.05", "1234123412341234", "1234567890");
		Dining dining5 = Dining.createDining("94.50", "1234123412341234", "1234567890");

		List<Dining> batch = new ArrayList<Dining>();
		batch.add(dining1);
		batch.add(dining2);
		batch.add(dining3);
		batch.add(dining4);
		batch.add(dining5);

		diningBatchProcessor.processBatch(batch);
		waitForBatch(batch.size(), 1000);

		assertEquals(batch.size(), confirmationLogger.getConfirmations().size());
	}

	private void waitForBatch(int batchSize, int timeout) throws InterruptedException {
		int sleepTime = 100;
		while (confirmationLogger.getConfirmations().size() < batchSize && timeout > 0) {
			Thread.sleep(sleepTime);
			timeout -= sleepTime;
		}
	}
}
