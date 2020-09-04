package cn.itcast.core.bean;

import java.io.Serializable;

import cn.itcast.core.bean.product.Sku;

/**
 * 购物项
 * @author lx
 *
 */
public class BuyerItem implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//1:SKUID   Sku对象里面有个自己的ID
	private Sku sku;
	
	//2：Boolean 是否有货;
	private Boolean isHave = true;

	//3:数量
	private Integer amount = 1;

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public Boolean getIsHave() {
		return isHave;
	}

	public void setIsHave(Boolean isHave) {
		this.isHave = isHave;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BuyerItem [sku=" + sku + ", isHave=" + isHave + ", amount=" + amount + "]";
	}
	
	
}
