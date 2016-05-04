package rewards.internal.account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * Finds account objects using the JPA API.
 */
@Repository
public class JpaAccountRepository implements AccountRepository {

	private EntityManager entityManager;

	/**
	 * Set the entity manager. Assumes automatic dependency injection via the
	 * JPA @PersistenceContext annotation. However this method may still be
	 * called manually in a unit-test.
	 * 
	 * @param entityManager
	 */
	@PersistenceContext
	void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * {@inheritDoc}
	 */
	public Account findByCreditCard(String creditCardNumber) {
		//	TODO-06: Use the entityManager to search for 
		//	an Account with the given credit card number.
		//	Remove the throws statement when complete.  
		//	Run the JpaAccountRepositoryTests.  It should pass.
		
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	//	TODO-12: (BONUS) Rewrite the query above 
	//	to use the Criteria Query API instead.	
}
