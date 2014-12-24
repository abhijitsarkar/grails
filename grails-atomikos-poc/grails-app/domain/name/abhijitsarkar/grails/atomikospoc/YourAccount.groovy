package name.abhijitsarkar.grails.atomikospoc

class YourAccount extends Account {
	static mapping = {
    	datasource 'other'
    	table 'account'
    }

    double credit(final double amount) {
    	this.balance += amount
	}

	double creditThrowException(final double amount) {
		credit(amount)

    	throw new RuntimeException()
	}

	double creditRollback(final double amount) {
    	withTransaction { status ->
			status.setRollbackOnly()
		}

		credit(amount)
	}
}
