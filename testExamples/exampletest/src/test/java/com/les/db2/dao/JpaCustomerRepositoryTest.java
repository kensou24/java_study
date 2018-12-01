package com.les.db2.dao;

import com.les.db2.DbApplication;
import com.les.db2.entity.Customer;
import com.les.db2.entity.EmailAddress;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * @ClassName: JpaCustomerRepositoryTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/29 15:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DbApplication.class)
@Slf4j
public class JpaCustomerRepositoryTest {
    @Autowired
    @Qualifier("jpaCustomerRepository")
    CustomerRepository repository;

    @Test
    public void insertsNewCustomerCorrectly() {

        Customer customer = new Customer("Alicia", "Keys");
        customer = repository.save(customer);

        assertThat(customer.getId(), is(notNullValue()));
    }

    @Test
    public void updatesCustomerCorrectly() {

        Customer dave = repository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
        assertThat(dave, is(notNullValue()));

        dave.setLastname("Miller");
        dave = repository.save(dave);

        Customer reference = repository.findByEmailAddress(dave.getEmailAddress());
        assertThat(reference.getLastname(), is(dave.getLastname()));
    }
}