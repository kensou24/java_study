package com.les.db2.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

/**
 * @ClassName: Address
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/29 14:31
 */
@Entity
@Data
public class Address extends  AbstractEntity{
    private String street;
    private String city;
    private String country;

    public Address(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }

    protected Address() {
    }

    /**
     * Returns a copy of the current {@link Address} instance which is a new entity in terms of persistence.
     *
     * @return
     */
    public Address getCopy() {
        return new Address(this.street, this.city, this.country);
    }

}
