package rewards.internal.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import rewards.internal.monitor.Monitor;
import rewards.internal.monitor.MonitorFactory;

	// 	TODO-01: Indicate this class is an aspect.  
	//	Also mark it as a component.  
	//	Place an @Autowired annotation on the constructor.

public class LoggingAspect {

	private Logger logger = Logger.getLogger(getClass());
	private MonitorFactory monitorFactory;

	
	public LoggingAspect(MonitorFactory monitorFactory) {
		super();
		this.monitorFactory = monitorFactory;
	}

	
	//	TODO-02: Mark this method with an advice type annotation.
	//	Decide which advice type is most appropriate.  Write a 
	//	pointcut expression that selects only find* methods.

	public void implLogging(JoinPoint joinPoint) {
		logger.info("Logging: Class - "+ joinPoint.getTarget().getClass()+"; Executing before " + joinPoint.getSignature().getName() + "() method");
	}
	
	
	//	TODO-06: Mark this method as around advice.  Write a pointcut 
	//	expression to match on all update* methods on Repository classes.  

	public Object monitor(ProceedingJoinPoint repositoryMethod) throws Throwable {
		String name = createJoinPointTraceName(repositoryMethod);
		Monitor monitor = monitorFactory.start(name);
		try {

			//	TODO-07: Add the logic to proceed with the target method invocation.
			//	Be sure to return the target method's return value to the caller.
			
			return new String("Comment this line after completing TODO-07");
			
		} finally {
			monitor.stop();
			logger.info(monitor);
		}
	}
	
	//	TODO-08: After completing the monitor method above, 
	//	save all work, run the RewardNetworkTests.  It should pass,
	//	and you should see console output from the monitor method.

	private String createJoinPointTraceName(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		StringBuilder sb = new StringBuilder();
		sb.append(signature.getDeclaringType().getSimpleName());
		sb.append('.').append(signature.getName());
		return sb.toString();
	} 
}