package accounts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import rewards.internal.account.Account;
import rewards.internal.account.Beneficiary;

import accounts.AccountManager;

/**
 * A Spring MVC @Controller controller handling requests to view and modify
 * Account information.
 * <p>
 * Note that all the Account application classes are imported from the
 * <tt>rewards-db</tt> project:
 * <ul>
 * <li>Domain objects: {@link Account} and {@link Beneficiary}</li>
 * <li>Service layer: {@link AccountManager} interface and its implementations</li>
 * <li>No repository layer is being used - the account-manager does everything</li>
 */
@Controller
public class AccountController {

	private AccountManager accountManager;

	/**
	 * Creates a new AccountController with a given account manager.
	 */
	@Autowired
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	
	/**
	 * <p>
	 * Provide a model with a list of all accounts for the account List page.
	 * </p>
	 * 
	 * @param model
	 *            the "implicit" model created by Spring MVC
	 */
	@RequestMapping("/accountList")
	public String accountList(Model model) {
		model.addAttribute("accounts", accountManager.getAllAccounts());
		return "/WEB-INF/views/accountList.jsp";
	}
	

	// TODO-02: Implement the /accountDetails request handling method.  
	//	Use a method parameter to obtain the request parameter needed to retrieve an account.
	//	Use another method parameter to gain access to the Model.
	//	Use the accountManager to obtain an account.  Place this on the model.
	//	The attribute key should correspond to the key used in accountDetails.jsp.
	//	Finally tell Spring to forward to accountDetails.jsp.
	//	Save all work, restart the server.  You should be able 
	//	to reach the account detail page.


}
