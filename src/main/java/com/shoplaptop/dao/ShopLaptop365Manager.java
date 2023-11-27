package com.shoplaptop.dao;

import java.util.List;

public interface ShopLaptop365Manager <Entity>{
	public void fillTable(List<Entity> list);
	public Entity getModel();
	public void setModel(Entity entity);
	
}
