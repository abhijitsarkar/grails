package name.abhijitsarkar.grails.atomikospoc

import grails.transaction.Transactional

@Transactional
class MyAccountManagementService {
	static datasource = 'DEFAULT'

	double debit(final long accountNumber, final double amount) {
		MyAccount myAccount = MyAccount.findByAccountNumber(accountNumber)

		final double amountTransferred = myAccount.debit(amount)
		myAccount.save(flush: true)	

		amountTransferred
	}
}
