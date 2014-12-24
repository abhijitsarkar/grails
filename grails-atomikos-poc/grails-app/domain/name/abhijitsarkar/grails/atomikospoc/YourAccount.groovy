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
    	throw new IllegalArgumentException()
	}

	double creditRollback(final double amount) {
    	withTransaction { status ->
			status.setRollbackOnly()
		}

		0.0d
	}
}
