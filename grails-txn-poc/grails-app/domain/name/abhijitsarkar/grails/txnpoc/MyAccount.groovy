package name.abhijitsarkar.grails.txnpoc

class MyAccount extends Account {
	static mapping = {
    	datasource 'DEFAULT'
    	table 'account'
    }

    double debit(final double amount) {
		this.balance -= amount		
	}
}
