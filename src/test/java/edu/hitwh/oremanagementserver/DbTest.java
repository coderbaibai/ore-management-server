package edu.hitwh.oremanagementserver;

import edu.hitwh.oremanagementserver.dao.UserMapper;
import edu.hitwh.oremanagementserver.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DbTest {

    @Autowired
    UserMapper userMapper;
    @Test
    void testUser() {
        List<User> res =  userMapper.selectList(null);
        System.out.println(res.get(0));
    }

}
