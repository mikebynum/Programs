package com.example.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContainerBean<T extends BusinessBean> extends BusinessBean{
	
private List<T> items = new ArrayList<T>();
	
	public ContainerBean() {}
	
	public void add(T obj) {
		items.add(obj);
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		if (items == null) {
			this.items.clear();
		} else {
			this.items = items;
		}
	}

	public int getSize() {
		if (items != null)
			return items.size();
		return 0;
	}
	
	public BusinessBean get(String id) {
		if (items != null) {
			Iterator<T> i = items.iterator();
			while (i.hasNext()) {
				T bean = i.next();
				if (bean.getId().equals(id)) {
					return bean;
				}
			}
		}
		
		return null;
	}

}
