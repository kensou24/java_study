package com.les.db2.dao;

import com.les.db2.DbApplication;
import com.les.db2.entity.Address;
import com.les.db2.entity.Customer;
import com.les.db2.entity.EmailAddress;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * @ClassName: CustomerRepositoryTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/29 15:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DbApplication.class)
@Slf4j
public class CustomerRepositoryTest {
    @Autowired
    @Qualifier("customerRepository")
    CustomerRepository repository;

    @Test
    @Transactional
    public void savesCustomerCorrectly() {

        EmailAddress email = new EmailAddress("alicia@keys.com");

        Customer dave = new Customer("Alicia", "Keys");
        dave.setEmailAddress(email);
        dave.add(new Address("27 Broadway", "New York", "United States"));

        Customer result = repository.save(dave);
        assertThat(result.getId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void readsCustomerByEmail() {

        EmailAddress email = new EmailAddress("alicia@keys.com");
        Customer alicia = new Customer("Alicia", "Keys");
        alicia.setEmailAddress(email);

        repository.save(alicia);

        Customer result = repository.findByEmailAddress(email);
        assertThat(result, is(alicia));
    }

    @Test
    @Transactional
    public void preventsDuplicateEmail() {

        Customer dave = repository.findByEmailAddress(new EmailAddress("dave@dmband.com"));

        Customer anotherDave = new Customer("Dave", "Matthews");
        anotherDave.setEmailAddress(dave.getEmailAddress());

        repository.save(anotherDave);
    }
}