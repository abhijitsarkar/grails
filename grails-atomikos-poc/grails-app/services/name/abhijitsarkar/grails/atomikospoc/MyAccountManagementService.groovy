package name.abhijitsarkar.grails.atomikospoc

import grails.transaction.Transactional

@Transactional
class MyAccountManagementService {
	static datasource = 'DEFAULT'

	double debit(final long accountNumber, final double amount) {
		MyAccount.findByAccountNumber(accountNumber).debit(amount)	
	}
}
