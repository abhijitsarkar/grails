package name.abhijitsarkar.grails.atomikospoc

import grails.test.spock.IntegrationSpec

class FundsTransferServiceIntegrationSpec extends IntegrationSpec {
	FundsTransferService fundsTransferService

    def setup() {
        new MyAccount(accountNumber: 1L, balance: 100.0, 
                accountOwnerName: 'Abhijit Sarkar').save(failOnError: true)

        new YourAccount(accountNumber: 2L, balance: 100.0, 
                accountOwnerName: 'John Doe').save(failOnError: true)
    }

    def cleanup() {
    }

    void 'test operations across two datasources when all is good'() {
        when:       
        fundsTransferService.transferFunds(1L, 2L, 10.0)

        then:
        MyAccount.findByAccountNumber(1L).balance == 90.0
        YourAccount.findByAccountNumber(2L).balance == 110.0
    }

    void 'test operations across two datasources when credit throws exception'() {
        when:
        fundsTransferService.transferFunds(1L, 2L, 10.0, true, false)

        then:
        thrown(RuntimeException)

        MyAccount.findByAccountNumber(1L).balance == 90.0
        YourAccount.findByAccountNumber(2L).balance == 110.0
    }

    void 'test operations across two datasources when credit is supposed to roll back'() {
        when:
        fundsTransferService.transferFunds(1L, 2L, 10.0, false, true)

        then:
        MyAccount.findByAccountNumber(1L).balance == 90.0
        YourAccount.findByAccountNumber(2L).balance == 110.0
    }
}
