package com.les.db2.dao;

import com.les.db2.entity.Customer;
import com.les.db2.entity.EmailAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;


/**
 * @ClassName: CustomerRepository
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/29 15:15
 */
public interface CustomerRepository extends Repository<Customer, Long> {
    /**
     * Returns the {@link Customer} with the given identifier.
     *
     * @param id the id to search for.
     * @return
     */
    Customer findCustomerById(Long id);

    /**
     * Saves the given {@link Customer}.
     *
     * @param customer the {@link Customer} to search for.
     * @return
     */
    Customer save(Customer customer);

    /**
     * Returns the customer with the given {@link EmailAddress}.
     *
     * @param emailAddress the {@link EmailAddress} to search for.
     * @return
     */
    Customer findByEmailAddress(EmailAddress emailAddress);
}
