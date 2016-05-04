package accounts.web;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

import common.money.Percentage;

import rewards.internal.account.Account;
import rewards.internal.account.Beneficiary;
import accounts.AccountManager;

/**
 * A controller handling requests for CRUD operations on Accounts and their Beneficiaries.
 */
@Controller
public class AccountController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private AccountManager accountManager;
	
	/**
	 * Creates a new AccountController with a given account manager.
	 */
	@Autowired 
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	/**
	 * Provide a list of all accounts.
	 */
	//	TODO 02: Complete this method.  Add annotations to respond
	//	to GET /accounts and return a List<Account> to be converted.
	//	Save your work and restart the server.  You should get JSON results when accessing 
	//	http://localhost:8080/rest-ws/app/accounts
	public List<Account> accountSummary() {
		return accountManager.getAllAccounts();
	}
	
	/**
	 * Provide the details of an account with the given id.
	 */
	//	TODO 04: Complete this method.  Add annotations to respond
	//	to GET /accounts/{accountId} and return an Account to be converted.
	//	Save your work and restart the server.  You should get JSON results when accessing 
	//	http://localhost:8080/rest-ws/app/accounts/0
	public Account accountDetails(int id) {
		return retrieveAccount(id);
	}
	
	/**
	 * Creates a new Account, setting its URL as the Location header on the response.
	 */
	//	TODO 06: Complete this method.  Add annotations to respond to 
	//	POST /accounts requests containing a marshalled Account with a 201 Created status
	public void createAccount(Account newAccount, 
							  HttpServletRequest request, 
							  HttpServletResponse response) {
		Account account = accountManager.save(newAccount);
		//	TODO 07: Set the Location header on the Response to the location of the created account.
		//	Note the existing entityWithLocation method below.
	}

	
	private HttpEntity<String> entityWithLocation(StringBuffer url,
			Object resourceId) {
		// Configure and return an HttpEntity object - it will be used to build
		// the HttpServletResponse
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(getLocationForChildResource(url, resourceId));
		return new HttpEntity<String>(headers);
	}
	
	/**
	 * Returns the Beneficiary with the given name for the Account with the given id.   
	 */
	@RequestMapping(value="/accounts/{accountId}/beneficiaries/{beneficiaryName}", method=RequestMethod.GET)
	public @ResponseBody Beneficiary getBeneficiary(@PathVariable("accountId") int accountId, 
			                                        @PathVariable("beneficiaryName") String beneficiaryName) {
		return retrieveAccount(accountId).getBeneficiary(beneficiaryName);
	}

	/**
	 * Adds a Beneficiary with the given name to the Account with the given id,
	 * setting its URL as the Location header on the response. 
	 */
	// TODO 11:	Complete this method. Add annotations to respond to a
	//          POST /accounts/{accountId}/beneficiaries containing a beneficiary name
	//          with a 201 Created status
	public void addBeneficiary(long accountId, 
							   String beneficiaryName,
							   HttpServletRequest request, 
							   HttpServletResponse response) {
		accountManager.addBeneficiary(accountId, beneficiaryName);
		//	TODO 12: Set the Location header on the Response to the location of the created beneficiary.
		//	Note the existing entityWithLocation method below.
	}
	
	/**
	 * Removes the Beneficiary with the given name from the Account with the given id. 
	 */
	// TODO 13: Complete this method by adding the appropriate annotations to respond to a
	//          DELETE to /accounts/{accountId}/beneficiaries/{beneficiaryName}
	//          with a 204 No Content status
	public void removeBeneficiary(long accountId, 
			                      String beneficiaryName) {
		Account account = accountManager.getAccount(accountId);
		Beneficiary b = account.getBeneficiary(beneficiaryName);

		// We ought to reset the allocation percentages, but for now we won't
		// bother. If we are removing the only beneficiary or the beneficiary
		// has an allocation of zero we don't need to worry. Otherwise, throw an
		// exception.
		if (account.getBeneficiaries().size() != 1
				|| (!b.getAllocationPercentage().equals(Percentage.zero()))) {
			//	TODO 19: (BONUS) Removing a beneficiary will usually alter the total allocation % to be less than 100%.
			//	Add logic to re-allocate the %s of the other beneficiaries so the total = 100%.
			//	(Note: this logic would ordinarily be implemented within the AccountManager)
		}

		accountManager.removeBeneficiary(accountId, beneficiaryName,
				new HashMap<String, Percentage>());
	}
	
	/**
	 * Maps IllegalArgumentExceptions to a 404 Not Found HTTP status code.
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({IllegalArgumentException.class})
	public void handleNotFound(Exception ex) {
		logger.error("Exception is: ",ex);
		// just return empty 404
	}

	//	TODO 18 (BONUS): add a new exception-handling method that maps
	//	DataIntegrityViolationExceptions to a 409 Conflict status code.
	//	Use the handleNotFound method above for guidance.

	
	/**
	 * Finds the Account with the given id, throwing an IllegalArgumentException
	 * if there is no such Account. 
	 */
	private Account retrieveAccount(long accountId) throws IllegalArgumentException {
		Account account = accountManager.getAccount(accountId);
		if (account == null) {
			throw new IllegalArgumentException("No such account with id " + accountId);
		}
		return account;
	}

	/**
	 * determines URL of child resource based on the full URL of the given request,
	 * appending the path info with the given childIdentifier using a UriTemplate.
	 */ 
		
	private URI getLocationForChildResource(StringBuffer url,
			Object childIdentifier) {
		UriTemplate template = new UriTemplate(url.append("/{childId}")
				.toString());
		return template.expand(childIdentifier);
	}

}