package com.shoplaptop.dao;

import java.util.List;

public interface ShopLaptop365DAO <EntityType, KeyType> {
	
	public String insert(EntityType entity);
	public String update(EntityType entity);
	public String delete(KeyType id);
	public EntityType selectById(KeyType id);
	public List<EntityType> selectAll();
	public List<EntityType> selectBySQL(String sql, Object... args);
	
}
