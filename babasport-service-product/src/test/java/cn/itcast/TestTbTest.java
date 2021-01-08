package cn.itcast;

import java.util.Date;
import java.util.List;

import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.service.product.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.core.bean.TestTb;
import cn.itcast.core.dao.TestTbDao;
import cn.itcast.core.service.TestTbService;

/**
 * 测试类  junit + Spring 
 * @author lx
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestTbTest {

	@Autowired
	private TestTbDao testTableDao;
	
	@Autowired
	private TestTbService testTbService;

	@Autowired
	private BrandService brandService;
	
	@Test
	public void testAdd() throws Exception {
		TestTb testTb = new TestTb();
		testTb.setName("范冰冰666");
		testTb.setBirthday(new Date());
		testTbService.insertTestTb(testTb);
//		testTableDao.insertTestTb(testTb);
		
		
	}

	@Test
	public void testDoubbo() throws Exception {
		List<Brand> brands = brandService.selectBrandListByQuery(1);
		System.out.println(brands);
	}
}
