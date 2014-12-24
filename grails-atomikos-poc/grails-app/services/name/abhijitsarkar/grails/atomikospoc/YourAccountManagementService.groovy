package name.abhijitsarkar.grails.atomikospoc

import grails.transaction.Transactional

@Transactional
class YourAccountManagementService {
	static datasource = 'other'

	double credit(final long accountNumber, final double amount) {
		YourAccount yourAccount = YourAccount.findByAccountNumber(accountNumber)

		final double amountTransferred = yourAccount.credit(amount)
		yourAccount.save(flush: true)

		amountTransferred	
	}

	double failCreditByThrowingException(final long accountNumber, final double amount) {
		YourAccount yourAccount = YourAccount.findByAccountNumber(accountNumber)

		final double amountTransferred = yourAccount.creditThrowException(amount)	
		yourAccount.save(flush: true)

		amountTransferred
	}

	double failCreditByRollingBack(final long accountNumber, final double amount) {
		YourAccount yourAccount = YourAccount.findByAccountNumber(accountNumber)

		final double amountTransferred = yourAccount.creditRollback(amount)	
		yourAccount.save(flush: true)

		amountTransferred
	}
}
