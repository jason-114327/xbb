package cn.itcast.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.core.bean.TestTb;
import cn.itcast.core.dao.TestTbDao;

/**
 * 测试事务
 * @author lx
 *
 */
@Service("testTbService")
// 可以加载类上也可以加载方法上
@Transactional
public class TestTbServiceImpl implements TestTbService {

	
	@Autowired
	private TestTbDao testTbDao;
	
	@Override
	// @Transactional
	public void insertTestTb(TestTb testTb) {
		testTbDao.insertTestTb(testTb);
//		throw new RuntimeException();
	}
	
}
