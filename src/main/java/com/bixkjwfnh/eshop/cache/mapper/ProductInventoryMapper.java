package com.bixkjwfnh.eshop.cache.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bixkjwfnh.eshop.cache.model.ProductInventory;

@Mapper
public interface ProductInventoryMapper {
	
	public ProductInventory findProductInventory(Integer productId);
	
	public void updateProductInventory(ProductInventory productInventory);
}
