package cn.itcast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestRedis {

    @Autowired
    private Jedis jedis;

    // 从spring容器获取
    @Test
    public void testSpringJedis(){
        jedis.set("ooo","aaa");
        jedis.close();
    }

    // 直接连接
    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("192.168.200.128",6379);
//        Long pno = jedis.incr("pno");
        String pno = jedis.get("pno");
        System.out.println(pno);
        jedis.close();
    }
}
