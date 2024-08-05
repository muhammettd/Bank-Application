package com.projebank.projebanka.repository;

import com.projebank.projebanka.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
