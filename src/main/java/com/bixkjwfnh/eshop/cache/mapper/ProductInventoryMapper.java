package com.bixkjwfnh.eshop.cache.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bixkjwfnh.eshop.cache.model.ProductInventory;

/**
 * ProductInventoryMapper
 * 
 * @since 2018.04.26
 * @author SunQW
 *
 */
@Mapper
public interface ProductInventoryMapper {
	
	public ProductInventory findProductInventory(Integer productId);
	
	public void updateProductInventory(ProductInventory productInventory);
}
