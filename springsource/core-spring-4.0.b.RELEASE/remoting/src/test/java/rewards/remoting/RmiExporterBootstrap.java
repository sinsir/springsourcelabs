package rewards.remoting;

import org.springframework.context.support.ClassPathXmlApplicationContext;


//	TODO-02: Run this class as a Java application (not as a JUnit test) 
//	to start the server side of this system.  Note that the application will run
//	in the background waiting for RMI requests to be made.

public class RmiExporterBootstrap {

	public static final String[] BEAN_FILES = {
		"rewards/remoting/rmi-server-config.xml",
		"rewards/system-test-config.xml" };
	
	public static void main(String[] args) throws Exception {
		
		// Load the application context, using the jpa profile.
		// Set profile to "hibernate" if preferred.
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		context.getEnvironment().setActiveProfiles("jpa");
		context.setConfigLocations(BEAN_FILES);
		context.refresh();   // Make it load the files

		System.out.println("RMI reward network server started.");
		System.in.read();
	}
}
