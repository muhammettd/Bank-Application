package com.projebank.projebanka;

import com.projebank.projebanka.dto.CreateCustomerRequest;
import com.projebank.projebanka.model.Account;
import com.projebank.projebanka.model.City;
import com.projebank.projebanka.model.Currency;
import com.projebank.projebanka.model.Customer;
import com.projebank.projebanka.repository.AccountRepository;
import com.projebank.projebanka.repository.CustomerRepository;
import com.projebank.projebanka.service.AccountService;
import com.projebank.projebanka.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ProjebankaApplication implements CommandLineRunner {

	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;

    public ProjebankaApplication(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(ProjebankaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer c1 = Customer.builder()
				.id("123468")
				.name("Muhammet")
				.city(City.ISTANBUL)
				.address("Ev")
				.dateOfBirth(2002)
				.build();

		Customer c2 = Customer.builder()
				.id("789456")
				.name("Selim")
				.city(City.ANKARA)
				.address("Ofis")
				.dateOfBirth(1988)
				.build();

		Customer c3 = Customer.builder()
				.id("456238")
				.name("Mert")
				.city(City.MANISA)
				.address("Ofis")
				.dateOfBirth(1997)
				.build();

		customerRepository.saveAll(Arrays.asList(c1,c2,c3));

		Account a1 = Account.builder()
				.id("100")
				.customerId("123468")
				.city(City.ISTANBUL)
				.balance(1520.0)
				.currency(Currency.EUR)
				.build();

		Account a2 = Account.builder()
				.id("101")
				.customerId("789456")
				.city(City.ANKARA)
				.balance(700.5)
				.currency(Currency.TRY)
				.build();

		Account a3 = Account.builder()
				.id("102")
				.customerId("456238")
				.city(City.MANISA)
				.balance(3500.0)
				.currency(Currency.USD)
				.build();

		accountRepository.saveAll(Arrays.asList(a1,a2,a3));

	}
}
