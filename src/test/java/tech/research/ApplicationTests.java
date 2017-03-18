package tech.research;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import tech.research.repository.UserRepository;
import tech.research.data.Data;
import tech.research.data.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {


    @Autowired
    private RedisTemplate<String, Data> redisTemplate;

    @Test
    public void testRedis() throws Exception {

        // 保存对象
        Data user = new Data("超人", 20);
        redisTemplate.opsForValue().set(user.getKey(), user);

        user = new Data("蝙蝠侠", 30);
        redisTemplate.opsForValue().set(user.getKey(), user);

        user = new Data("蜘蛛侠", 40);
        redisTemplate.opsForValue().set(user.getKey(), user);

        Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getValue().longValue());
        Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getValue().longValue());
        Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getValue().longValue());

    }


    @Autowired
    private UserRepository userRepository;

    @Test
    public void testJPA() throws Exception {

        // 创建10条记录
        userRepository.save(User.builder().username("AAA").age(10).build());
        userRepository.save(User.builder().username("BBB").age(20).build());
        userRepository.save(User.builder().username("CCC").age(30).build());
        userRepository.save(User.builder().username("DDD").age(40).build());
        userRepository.save(User.builder().username("EEE").age(50).build());
        userRepository.save(User.builder().username("FFF").age(60).build());
        userRepository.save(User.builder().username("GGG").age(70).build());
        userRepository.save(User.builder().username("HHH").age(80).build());
        userRepository.save(User.builder().username("III").age(90).build());
        userRepository.save(User.builder().username("JJJ").age(100).build());

        // 测试findAll, 查询所有记录
        Assert.assertEquals(10, userRepository.findAll().size());

        // 测试findByName, 查询姓名为FFF的User
        Assert.assertEquals(60, userRepository.findByUsername("FFF").getAge());

        // 测试findUser, 查询姓名为FFF的User
        Assert.assertEquals(60, userRepository.findByUsername("FFF").getAge());

        // 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
        Assert.assertEquals("FFF", userRepository.findByUsername("FFF").getUsername());

        // 测试删除姓名为AAA的User
        userRepository.delete(userRepository.findByUsername("AAA"));

        // 测试findAll, 查询所有记录, 验证上面的删除是否成功
        Assert.assertEquals(9, userRepository.findAll().size());

    }

    @Test
    public void testPasswordEncoder() throws Exception{
        int i = 0;
        while (i < 10) {
            String password = "123456";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            passwordEncoder = new BCryptPasswordEncoder();
            Assert.assertTrue(passwordEncoder.matches(password,hashedPassword));
            i++;
        }

    }
}