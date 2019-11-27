package com.hias.apps.util;

import java.util.HashMap;
import java.util.Map;

public class MapMessage {

	private Map<String, String> mapMessage = new HashMap<>();
	
	public Map<String, String> setMapMessage(String msg) {
		mapMessage.put("message", msg);
		return mapMessage;
	}
	
}
