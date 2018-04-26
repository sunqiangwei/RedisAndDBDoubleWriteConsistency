package com.bixkjwfnh.eshop.cache.request;

import com.bixkjwfnh.eshop.cache.model.ProductInventory;
import com.bixkjwfnh.eshop.cache.service.ProductInventoryService;

/**
 * 重新加载商品库存的缓存
 * 
 * @since 2018.04.26
 * @author SunQW
 *
 */
public class ProductInventoryCacheRefreshRequest implements Request {

	/**
	 * 商品id
	 */
	private Integer productId;

	/**
	 * 商品库存Service
	 */
	private ProductInventoryService productInventoryService;

	/**
	 * 是否强制刷新缓存
	 */
	private Boolean forceRefresh;

	public ProductInventoryCacheRefreshRequest(Integer productId, ProductInventoryService productInventoryService,
			Boolean forceRefresh) {
		this.productId = productId;
		this.productInventoryService = productInventoryService;
		this.forceRefresh = forceRefresh;
	}

	@Override
	public void process() {
		// 从数据库中查询最新的商品库存数量
		ProductInventory productInventory = productInventoryService.findProductInventory(productId);
		System.out.println("===========日志===========: 已查询到商品最新的库存数量，商品id=" + productId + ", 商品库存数量=" + productInventory.getInventoryCnt());  
		// 将最新的商品库存数量，刷新到redis缓存中去
		productInventoryService.setProductInventoryCache(productInventory);

	}

	public Integer getUniqueId() {
		return productId;
	}

	public Boolean isForceRefresh() {
		return forceRefresh;
	}

}
