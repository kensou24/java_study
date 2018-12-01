package com.les.db2.dao.order;

import com.les.db2.DbApplication;
import com.les.db2.dao.CustomerRepository;
import com.les.db2.dao.ProductRepository;
import com.les.db2.entity.Customer;
import com.les.db2.entity.EmailAddress;
import com.les.db2.entity.Product;
import com.les.db2.entity.order.LineItem;
import com.les.db2.entity.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.les.db2.dao.CoreMatchers.named;
import static com.les.db2.dao.CoreMatchers.with;
import static com.les.db2.dao.order.OrderMatchers.containsOrder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * @ClassName: OrderRepositoryTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/29 15:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DbApplication.class)
@Slf4j
public class OrderRepositoryTest {
    @Autowired
    OrderRepository repository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void createOrder() {

        Customer dave = customerRepository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
        Optional<Product> iPad = productRepository.findById(1L);

        Order order = new Order(dave, dave.getAddresses().iterator().next());
        order.add(new LineItem(iPad.get()));

        order = repository.save(order);
        assertThat(order.getId(), is(notNullValue()));
    }

    @Test
    public void readOrder() {

        Customer dave = customerRepository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
        List<Order> orders = repository.findByCustomer(dave);
        //Matcher<Iterable<? super Order>> hasOrderForiPad = containsOrder(with(LineItem(with(Product(named("iPad"))))));

        assertThat(orders, hasSize(1));
        //assertThat(orders, hasOrderForiPad);
    }

}