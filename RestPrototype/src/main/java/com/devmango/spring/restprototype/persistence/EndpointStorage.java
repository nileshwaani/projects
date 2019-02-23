package com.devmango.spring.restprototype.persistence;

import java.util.HashMap;
import java.util.Map;

import com.devmango.spring.restprototype.domain.Endpoint;

public class EndpointStorage {
	private static Map<String, Endpoint> urlEndpointMap = new HashMap<>();

	public static Map<String, Endpoint> getUrlEndpointMap() {
		return urlEndpointMap;
	}
}
