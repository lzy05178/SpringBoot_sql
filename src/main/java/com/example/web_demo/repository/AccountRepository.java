package com.example.web_demo.repository;

import com.example.web_demo.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account,String> {
    @Query(value = "select * from account", nativeQuery = true)
    public  Iterable<Account> getAllAcounts();
}
