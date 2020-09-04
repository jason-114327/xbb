package cn.itcast.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 * @author lx
 *
 */
public class BuyerCart implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//1：商品结果集 List<BuyerItem> 
	private List<BuyerItem> items = new ArrayList<>();
	
	//添加购物项到购物车中
	public void addItem(BuyerItem item){
		items.add(item);
	}

	public List<BuyerItem> getItems() {
		return items;
	}

	public void setItems(List<BuyerItem> items) {
		this.items = items;
	}
	
	//2:小计     （商品数据  、商品金额  、运费  、 总计）
	
}
