package com.bixkjwfnh.eshop.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bixkjwfnh.eshop.cache.dao.RedisDao;
import com.bixkjwfnh.eshop.cache.mapper.ProductInventoryMapper;
import com.bixkjwfnh.eshop.cache.model.ProductInventory;
import com.bixkjwfnh.eshop.cache.service.ProductInventoryService;

/**
 * 商品库存Service实现类
 * @author SunQW
 *
 */
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

	@Autowired
	private ProductInventoryMapper productInventoryMapper;
	
	@Autowired
	private RedisDao redisDao;
	
	@Override
	public void updateProductInventory(ProductInventory productInventory) {
		productInventoryMapper.updateProductInventory(productInventory);
		System.out.println("===========日志===========: 已修改数据库中的库存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());
	}

	@Override
	public void removeProductInventoryCache(Integer productId) {
		String key = "product:inventory:"+String.valueOf(productId);
		redisDao.delete(key);
		System.out.println("===========日志===========: 已删除redis中的缓存，key=" + key); 
	}

	@Override
	public ProductInventory findProductInventory(Integer productId) {
		ProductInventory productInventory = productInventoryMapper.findProductInventory(productId);
		return productInventory;
	}

	@Override
	public void setProductInventoryCache(ProductInventory productInventory) {
		String key = "product:inventory:"+String.valueOf(productInventory.getProductId());
		
		redisDao.set(key, String.valueOf(productInventory.getInventoryCnt()));
		System.out.println("===========日志===========: 已更新商品库存的缓存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt() + ", key=" + key);  
	}

	/**
	 * 获取商品库存的缓存
	 * @param productId
	 * @return
	 */
	public ProductInventory getProductInventoryCache(Integer productId) {
		Long inventoryCnt = 0L;
		
		String key = "product:inventory:" + productId;
		String result = redisDao.get(key);
		
		if(result != null && !"".equals(result)) {
			try {
				inventoryCnt = Long.valueOf(result);
				return new ProductInventory(productId, inventoryCnt);
			} catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		
		return null;
	}
	
	

}
