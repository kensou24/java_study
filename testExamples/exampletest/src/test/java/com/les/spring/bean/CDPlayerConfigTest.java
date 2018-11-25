package com.les.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @ProjectName: exampletest
 * @Package: com.les.spring.bean
 * @ClassName: CDPlayerConfigTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/17 11:12
 * @UpdateUser: king
 * @UpdateDate: 2018/11/17 11:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
@Slf4j
public class CDPlayerConfigTest {

    @Autowired
    private CompactDisc cd;

    @Test
    public void getCD()
    {
        assertNotNull(cd);
        cd.play();
    }
}