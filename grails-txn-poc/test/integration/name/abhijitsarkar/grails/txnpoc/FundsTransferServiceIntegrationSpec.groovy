package name.abhijitsarkar.grails.txnpoc

import grails.test.spock.IntegrationSpec

class FundsTransferServiceIntegrationSpec extends IntegrationSpec {
	FundsTransferService fundsTransferService
    Account myAccount
    Account yourAccount

    def setup() {
        myAccount = new MyAccount(accountNumber: 1L, balance: 100.0, 
                accountOwnerName: 'Abhijit Sarkar').save(failOnError: true)

        yourAccount = new YourAccount(accountNumber: 2L, balance: 100.0, 
                accountOwnerName: 'John Doe').save(failOnError: true)
    }

    def cleanup() {
    }

    void 'test operations across two datasources when all is good'() {
    	when:
    	fundsTransferService.transferFunds(myAccount.accountNumber, yourAccount.accountNumber, 
                10.0)

    	then:
    	myAccount.balance == 90.0
    	yourAccount.balance == 110.0
    }

    void 'test operations across two datasources when credit throws exception'() {
        when:
        try {
            fundsTransferService.transferFunds(myAccount.accountNumber, yourAccount.accountNumber, 
                10.0, true, false)
        } catch (RuntimeException e) {

        }

        then:
        myAccount.balance == 90.0
        yourAccount.balance == 100.0
    }

    void 'test operations across two datasources when credit rolls back'() {
        when:
        fundsTransferService.transferFunds(myAccount.accountNumber, yourAccount.accountNumber, 
                10.0, false, true)

        then:
        myAccount.balance == 90.0
        yourAccount.balance == 100.0
    }
}
