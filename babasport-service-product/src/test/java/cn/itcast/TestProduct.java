package cn.itcast;

import cn.itcast.core.bean.product.Product;
import cn.itcast.core.dao.product.ProductDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类  junit + Spring 
 * @author lx
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestProduct {

	@Autowired
	private ProductDao productDao;

	@Test
	public void testAdd() throws Exception {
		Product product = productDao.selectByPrimaryKey(441L);
		System.out.println(product);
	}
}
