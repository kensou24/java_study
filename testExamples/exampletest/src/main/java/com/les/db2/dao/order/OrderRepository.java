package com.les.db2.dao.order;

import com.les.db2.entity.Customer;
import com.les.db2.entity.order.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @ClassName: OrderRepository
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/29 15:29
 */
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    /**
     * Returns all {@link Order}s of the given {@link Customer}.
     *
     * @param customer
     * @return
     */
    List<Order> findByCustomer(Customer customer);
}