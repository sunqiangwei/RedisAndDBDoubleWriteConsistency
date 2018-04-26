package com.bixkjwfnh.eshop.cache.service;

import com.bixkjwfnh.eshop.cache.model.ProductInventory;

/**
 * 商品库存Service接口
 * 
 * @since 2018.04.26
 * @author SunQW
 *
 */
public interface ProductInventoryService {

	/**
	 * 根据商品id查询商品库存
	 * @param productId 商品id 
	 * @return 商品库存
	 */
	public ProductInventory findProductInventory(Integer productId);
	
	/**
	 * 更新商品库存
	 * @param productInventory 商品库存
	 */
	public void updateProductInventory(ProductInventory productInventory);
	
	/**
	 * 删除Redis中的商品库存的缓存
	 * @param productInventory 商品库存
	 */
	public void removeProductInventoryCache(Integer productId);
	
	/**
	 * 设置商品库存的缓存
	 * @param productInventory 商品库存
	 */
	public void setProductInventoryCache(ProductInventory productInventory);
	
	/**
	 * 获取商品库存的缓存
	 * @param productId
	 * @return
	 */
	ProductInventory getProductInventoryCache(Integer productId);
}
