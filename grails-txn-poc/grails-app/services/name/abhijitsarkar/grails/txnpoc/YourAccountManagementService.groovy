package name.abhijitsarkar.grails.txnpoc

import grails.transaction.Transactional

@Transactional
class YourAccountManagementService {
	static datasource = 'other'

	double credit(final long accountNumber, final double amount) {
		YourAccount.findByAccountNumber(accountNumber).credit(amount)	
	}

	double failCreditByThrowingException(final long accountNumber, final double amount) {
		YourAccount.findByAccountNumber(accountNumber).creditThrowException(amount)	
	}

	double failCreditByRollingBack(final long accountNumber, final double amount) {
		YourAccount.findByAccountNumber(accountNumber).creditRollback(amount)	
	}
}
