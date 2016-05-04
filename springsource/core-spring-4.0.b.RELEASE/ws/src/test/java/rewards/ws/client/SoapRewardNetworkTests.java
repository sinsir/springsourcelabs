package rewards.ws.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

//	TODO 09: Run this test.  It should pass.

@ContextConfiguration(locations = {"classpath:rewards/ws/client/client-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SoapRewardNetworkTests {
	
	private static final String NAMESPACE_URI = "http://www.springsource.com/reward-network";

	@Autowired
	private WebServiceTemplate webServiceTemplate;
	
	@Test
	public void testWebServiceWithXml() throws Exception {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element requestElement = document.createElementNS(NAMESPACE_URI, "rewardAccountForDiningRequest");
		requestElement.setAttribute("amount", "100.00");
		requestElement.setAttribute("creditCardNumber", "1234123412341234");
		requestElement.setAttribute("merchantNumber", "1234567890");
		DOMSource source = new DOMSource(requestElement);
		DOMResult result = new DOMResult();
		webServiceTemplate.sendSourceAndReceiveToResult(source, result);
		Element responseElement = (Element) result.getNode().getFirstChild();
		
		// assert the expected reward confirmation results
		assertNotNull(responseElement);
		
		// the account number should be '123456789'
		assertEquals("123456789", responseElement.getAttribute("accountNumber"));
		
		// the total contribution amount should be 8.00 (8% of 100.00)
		assertEquals("8.00", responseElement.getAttribute("amount"));
	}

	
	@Test
	public void testWebServiceWithJAXB() throws Exception {
		//	TODO 11: Implement this method by using your generated JAXB2 classes.
		//	Use the template's marshalSendAndReceive() to send a 
		//	RewardAccountForDiningRequest and receive a RewardAccountForDiningResponse.
		//	Use data for input and assertions from the testWebServiceWithXml() method above.		
		
	}
	
	
	//	TODO 10: Run the TCP/IP monitor.  
	//	Open the TCP/IP view (type Ctrl-3, enter "TCP" and enter to open the view).
	//	Add a new monitor (click the small down arrow and choose "properties". Then choose "Add...".)
	//	Enter local monitor port = 8081, host = "localhost", port = 8080.  Press OK and start.
	//	Open client-config.xml and change port number in the request URL from 8080 to 8081.
	//	Rerun this test and examine the request and response in the monitor view.
	
}
