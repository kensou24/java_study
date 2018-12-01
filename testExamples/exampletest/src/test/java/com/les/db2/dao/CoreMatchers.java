package com.les.db2.dao;

import com.les.db2.entity.Product;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;


/**
 * @ClassName: CoreMatchers
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/29 15:23
 */
public class CoreMatchers {
    /**
     * Syntactic sugar to make Matchers more readable.
     *
     * @param matcher must not be {@literal null}.
     * @return
     */
    public static <T> Matcher<T> with(Matcher<T> matcher) {
        return matcher;
    }

    /**
     * Matches if the {@link Product} has the given name.
     *
     * @param name must not be {@literal null}.
     * @return
     */
    public static Matcher<Product> named(String name) {
        return hasProperty("name", is(name));
    }
}
