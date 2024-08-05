package com.projebank.projebanka.service;

import com.projebank.projebanka.dto.AccountDto;
import com.projebank.projebanka.dto.AccountDtoConverter;
import com.projebank.projebanka.dto.CreateAccountRequest;
import com.projebank.projebanka.model.Account;
import com.projebank.projebanka.model.City;
import com.projebank.projebanka.model.Currency;
import com.projebank.projebanka.model.Customer;
import com.projebank.projebanka.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AccountServiceTest {

    private AccountService accountService;

    private AccountRepository accountRepository;
    private CustomerService customerService;
    private AccountDtoConverter accountDtoConverter;

    @org.junit.Before
    public void setUp() throws Exception {
        accountRepository = Mockito.mock(AccountRepository.class);
        customerService = Mockito.mock(CustomerService.class);
        accountDtoConverter = Mockito.mock(AccountDtoConverter.class);

        accountService = new AccountService(accountRepository, customerService, accountDtoConverter);
    }

    @Test
    public void whenCreateAccountCalledWithValidRequest_itShouldReturnValidAccountDto(){
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234");
        createAccountRequest.setCustomerId("12345");
        createAccountRequest.setBalance(100.0);
        createAccountRequest.setCity(City.ISTANBUL);
        createAccountRequest.setCurrency(Currency.TRY);

        Customer customer = Customer.builder()
                .id("12345")
                .address("Adres")
                .city(City.ISTANBUL)
                .dateOfBirth(1999)
                .name("Mehmet Can")
                .build();

        Account account = Account.builder()
                .id(createAccountRequest.getId())
                .balance(createAccountRequest.getBalance())
                .currency(createAccountRequest.getCurrency())
                .customerId(createAccountRequest.getCustomerId())
                .city(createAccountRequest.getCity())
                .build();

        AccountDto accountDto = AccountDto.builder()
                .id("1234")
                .customerId("12345")
                .currency(Currency.TRY)
                .balance(100.0)
                .build();



        Mockito.when(customerService.getCustomerById("12345")).thenReturn(customer);
        Mockito.when(accountRepository.save(account)).thenReturn(account);
        Mockito.when(accountDtoConverter.convert(account)).thenReturn(accountDto);

        AccountDto result = accountService.createAccount(createAccountRequest);

        Assert.assertEquals(result, accountDto);

        Mockito.verify(customerService).getCustomerById("12345");
        Mockito.verify(accountRepository).save(account);
        Mockito.verify(accountDtoConverter).convert(account);

    }

    @Test(expected = RuntimeException.class)
    public void whenCreateAccountCalledWithNonExistCustomer_itShouldReturnEmptyAccountDto(){
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234");
        createAccountRequest.setCustomerId("12345");
        createAccountRequest.setBalance(100.0);
        createAccountRequest.setCity(City.ISTANBUL);
        createAccountRequest.setCurrency(Currency.TRY);

        Mockito.when(customerService.getCustomerById("12345")).thenReturn(Customer.builder().build());

        AccountDto exceptedAccountDto = AccountDto.builder().build();

        AccountDto result = accountService.createAccount(createAccountRequest);

        Assert.assertEquals(result, exceptedAccountDto);
        Mockito.verifyNoInteractions(accountRepository);
        Mockito.verifyNoInteractions(accountDtoConverter);

    }

    @Test(expected = RuntimeException.class)
    public void whenCreateAccountCalledWithCustomerWithOutId_itShouldReturnEmptyAccountDto(){
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234");
        createAccountRequest.setCustomerId("12345");
        createAccountRequest.setBalance(100.0);
        createAccountRequest.setCity(City.ISTANBUL);
        createAccountRequest.setCurrency(Currency.TRY);

        Mockito.when(customerService.getCustomerById("12345")).thenReturn(Customer.builder()
                .id(" ")
                .build());

        AccountDto exceptedAccountDto = AccountDto.builder().build();

        AccountDto result = accountService.createAccount(createAccountRequest);

        Assert.assertEquals(result, exceptedAccountDto);
        Mockito.verifyNoInteractions(accountRepository);
        Mockito.verifyNoInteractions(accountDtoConverter);

    }

    @Test(expected = RuntimeException.class)
    public void whenCreateAccountCalledRepositoryThrewException_itShouldThrowException(){
    CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234");
        createAccountRequest.setCustomerId("12345");
        createAccountRequest.setBalance(100.0);
        createAccountRequest.setCity(City.ISTANBUL);
        createAccountRequest.setCurrency(Currency.TRY);

    Customer customer = Customer.builder()
            .id("12345")
            .address("Adres")
            .city(City.ISTANBUL)
            .dateOfBirth(1999)
            .name("Mehmet Can")
            .build();

    Account account = Account.builder()
            .id(createAccountRequest.getId())
            .balance(createAccountRequest.getBalance())
            .currency(createAccountRequest.getCurrency())
            .customerId(createAccountRequest.getCustomerId())
            .city(createAccountRequest.getCity())
            .build();

        Mockito.when(customerService.getCustomerById("12345")).thenReturn(customer);
        Mockito.when(accountRepository.save(account)).thenThrow(new RuntimeException("bla bla"));

        accountService.createAccount(createAccountRequest);

        Mockito.verify(customerService).getCustomerById("12345");
        Mockito.verify(accountRepository).save(account);
        Mockito.verifyNoInteractions(accountDtoConverter);

}



}