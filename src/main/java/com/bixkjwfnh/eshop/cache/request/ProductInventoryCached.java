package com.bixkjwfnh.eshop.cache.request;

import java.util.BitSet;


/**
 * 用来保存某个商品的库存是否已经在缓存中了
 * 
 * @since 2018.04.26
 * @author SunQW
 *
 */
public class ProductInventoryCached {

	private static final ProductInventoryCached instance = new ProductInventoryCached();
	
	/**
	 * 此处使用BitSet来代替Map的原因，请参考下面的地址：
	 * https://blog.csdn.net/sun_qiangwei/article/details/80083535
	 */
	private  BitSet cached=new BitSet();  
	
	private ProductInventoryCached() {
	}

	public static ProductInventoryCached getInstance() {
		return instance;
	}
	
	public void set(Integer index,Boolean value) {
		cached.set(index,value);
	}
	
	public Boolean get(Integer index) {
		return cached.get(index);
	}
}
