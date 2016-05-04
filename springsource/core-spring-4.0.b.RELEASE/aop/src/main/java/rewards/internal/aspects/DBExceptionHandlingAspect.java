package rewards.internal.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;

import rewards.internal.exception.RewardDataAccessException;


@Aspect	
public class DBExceptionHandlingAspect {
	
	private Logger logger = Logger.getLogger(getClass());


	//	TODO-09 (Optional): Configure this advice method to handle 
	//	all exceptions thrown by Repository class methods.
	//	Select the advice type that seems most appropriate.
	
	public void implExceptionHandling(RewardDataAccessException e) { 
		logger.info(" Sending an email to Mister Smith : " + e + "\n");
	}
	
	
	//	TODO-10 (Optional): Annotate this class as a Spring-managed bean.
	//	Note that we enabled component scanning in an earlier step.
	//	Save all work, run DBExceptionHandlingAspectTests. It should pass, and 
	//	expected logging output should appear in the console.
	
}
