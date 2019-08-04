package com.important.DesignPattern.flyweight;

import java.util.HashMap;
import java.util.Map;

public class WebSiteFactory {

	public static final Map<String, ConcreteWebSite> sharePool = new HashMap<>();

	public Website getWebSiteCategory(String type) {
		if (!sharePool.containsKey(type)) {
			sharePool.put(type, new ConcreteWebSite(type));
		}
		return sharePool.get(type);
	}

}
