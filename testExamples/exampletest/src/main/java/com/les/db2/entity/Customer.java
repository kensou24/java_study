package com.les.db2.entity;


import lombok.Data;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: Customer
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/29 14:35
 */
@Entity
@Data
public class Customer extends AbstractEntity{
    private String firstname, lastname;

    @Column(unique = true)
    private EmailAddress emailAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private Set<Address> addresses = new HashSet<Address>();

    /**
     * Creates a new {@link Customer} from the given firstname and lastname.
     *
     * @param firstname must not be {@literal null} or empty.
     * @param lastname must not be {@literal null} or empty.
     */
    public Customer(String firstname, String lastname) {

        Assert.hasText(firstname);
        Assert.hasText(lastname);

        this.firstname = firstname;
        this.lastname = lastname;
    }

    protected Customer() {

    }

    /**
     * Adds the given {@link Address} to the {@link Customer}.
     *
     * @param address must not be {@literal null}.
     */
    public void add(Address address) {

        Assert.notNull(address);
        this.addresses.add(address);
    }
}
