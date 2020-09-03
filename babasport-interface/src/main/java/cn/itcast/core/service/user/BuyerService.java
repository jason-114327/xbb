package cn.itcast.core.service.user;

import cn.itcast.core.bean.user.Buyer;

public interface BuyerService {
	
	//通过用户名查询用户对象
	public Buyer selectBuyerByUsername(String username);

}
