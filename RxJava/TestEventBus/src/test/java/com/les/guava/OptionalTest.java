package com.les.guava;

import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * @ProjectName: TestEventBus
 * @Package: com.les.guava
 * @ClassName: OptionalTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/15 8:06
 * @UpdateUser: king
 * @UpdateDate: 2018/11/15 8:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
public class OptionalTest {

    @Test (expected = NullPointerException.class)
    public void testOptional()
    {
        Integer testInterger = null;
        com.google.common.base.Optional<Integer> integerOptional = com.google.common.base.Optional.of(testInterger);
        Integer getIntger = integerOptional.get();
    }

    @Test
    public void testOptional2()
    {
        Integer testInterger = new Integer(2);
        com.google.common.base.Optional<Integer> integerOptional = com.google.common.base.Optional.of(testInterger);
        if(integerOptional.isPresent())
        {
            assertEquals(new Integer(2), integerOptional.get());
        }
    }

}