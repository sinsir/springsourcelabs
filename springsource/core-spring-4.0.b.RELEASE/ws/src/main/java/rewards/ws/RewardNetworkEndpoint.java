package rewards.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import rewards.RewardNetwork;

@Endpoint
public class RewardNetworkEndpoint {

	private static final String NAMESPACE_URI = "http://www.springsource.com/reward-network";

	private RewardNetwork rewardNetwork;

	@Autowired
	public RewardNetworkEndpoint(RewardNetwork rewardNetwork) {
		this.rewardNetwork = rewardNetwork;
	}
	
	// TODO 06: Create a new method to serve as the web service endpoint.
	//	Annotate it to respond to rewardAccountForDiningRequest payloads.
	//	Method should accept a RewardAccountForDiningRequest as the unmarshalled request payload.
	//	It should return a RewardAccountForDiningResponse as the marshalled response payload.
	//	Logic: Use the input to create a new Dining object (hint: Dining.createDining()).
	//	Call the rewardNetwork's rewardAccountFor method with this Dining object.
	//	Capture the confirmation and use it to create a RewardAccountForDiningResponse.
	//	Return this Response object. 
	
}