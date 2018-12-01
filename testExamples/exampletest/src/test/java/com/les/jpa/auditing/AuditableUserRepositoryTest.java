package com.les.jpa.auditing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * @ClassName: AuditableUserRepositoryTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/30 9:10
 */
@RunWith(SpringRunner.class)
//@Transactional
@SpringBootTest
public class AuditableUserRepositoryTest
{
    @Autowired
    AuditableUserRepository repository;
    @Autowired
    AuditorAwareImpl auditorAware;
    @Autowired
    AuditingEntityListener listener;

    @Test
    public void auditEntityCreation() throws Exception {

        assertThat(ReflectionTestUtils.getField(listener, "handler"), is(notNullValue()));

        AuditableUser user = new AuditableUser();
        user.setUsername("username");
        auditorAware.setAuditor(user);

        AuditableUser user2 = new AuditableUser();
        user2.setUsername("username2");

        repository.save(user);
        user2 = repository.save(user2);
        //user = repository.save(user);

        assertThat(user2.getCreatedBy(), is(user));
        assertThat(user2.getLastModifiedBy(), is(user));
    }
}