package com.les.db2.dao;

import com.les.db2.DbApplication;
import com.les.db2.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static com.les.db2.dao.CoreMatchers.named;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

/**
 * @ClassName: ProductRepositoryTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/29 15:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DbApplication.class)
@Slf4j
public class ProductRepositoryTest {
    @Autowired
    ProductRepository repository;

    @Test
    public void createProduct() {

        Product product = new Product("Camera bag", new BigDecimal(49.99));
        product = repository.save(product);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void lookupProductsByDescription() {

        Pageable pageable = new PageRequest(0, 1, Sort.Direction.DESC, "name");
        Page<Product> page = repository.findByDescriptionContaining("Apple", pageable);

        assertThat(page.getContent(), hasSize(1));
        assertThat(page, Matchers.<Product> hasItems(named("iPad")));
        assertThat(page.getTotalElements(), is(2L));
        assertThat(page.isFirst(), is(true));
        assertThat(page.isLast(), is(false));
        assertThat(page.hasNext(), is(true));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void findsProductsByAttributes() {

        List<Product> products = repository.findByAttributeAndValue("connector", "plug");

        assertThat(products, Matchers.<Product> hasItems(named("Dock")));
    }
}