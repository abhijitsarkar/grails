package name.abhijitsarkar.grails.atomikospoc

class MyAccount extends Account {
	static mapping = {
    	datasource 'DEFAULT'
    	table 'account'
    }

    double debit(final double amount) {
		this.balance -= amount		
	}
}
