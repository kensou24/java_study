package com.les.db2.dao;

import com.les.db2.entity.Customer;
import com.les.db2.entity.EmailAddress;
import com.les.db2.entity.User;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @ClassName: JpaCustomerRepository
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/29 15:17
 */
@Repository
public class JpaCustomerRepository implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;
    /*
     * (non-Javadoc)
     * @see com.oreilly.springdata.jpa.core.CustomerRepository#findOne(java.lang.Long)
     */
    @Override
    public Customer findCustomerById(Long id) {
        return em.find(Customer.class, id);
    }

    /*
     * (non-Javadoc)
     * @see com.oreilly.springdata.jpa.core.CustomerRepository#save(com.oreilly.springdata.jpa.core.Customer)
     */
    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            em.persist(customer);
            return customer;
        } else {
            return em.merge(customer);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.oreilly.springdata.jpa.core.CustomerRepository#findByEmailAddress(com.oreilly.springdata.jpa.core.EmailAddress)
     */
    @Override
    public Customer findByEmailAddress(EmailAddress emailAddress) {

        TypedQuery<Customer> query = em.createQuery("select c from Customer c where c.emailAddress = :email",
                Customer.class);
        query.setParameter("email", emailAddress);

        return query.getSingleResult();
    }
}

