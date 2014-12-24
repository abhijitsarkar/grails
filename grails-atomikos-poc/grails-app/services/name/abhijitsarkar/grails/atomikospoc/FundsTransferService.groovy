package name.abhijitsarkar.grails.atomikospoc

import grails.transaction.Transactional

@Transactional
class FundsTransferService {
	/* If not specified, uses default datasource and associated txm mgr. Can only use one. 
	 * http://grails.org/doc/latest/guide/conf.html (see Services section)
	*/
	// static datasource = 'DEFAULT'

	MyAccountManagementService myAccountManagementService
	YourAccountManagementService yourAccountManagementService

    double transferFunds(final long myAccountNumber, final long YourAccountNumber, 
    		final double amount, final boolean failCreditByThrowingException = false, 
    		final boolean failCreditByRollingBack = false) {
    	myAccountManagementService.debit(myAccountNumber, amount)

    	if (failCreditByThrowingException) {
    		yourAccountManagementService.failCreditByThrowingException(YourAccountNumber, amount)
    	} else if (failCreditByRollingBack) {
    		yourAccountManagementService.failCreditByRollingBack(YourAccountNumber, amount)
		} else {
			yourAccountManagementService.credit(YourAccountNumber, amount)
		}    	

    	amount
    }
}
