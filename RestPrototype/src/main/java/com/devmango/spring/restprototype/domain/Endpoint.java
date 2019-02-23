package com.devmango.spring.restprototype.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Endpoint {
	private String url;

	@JsonIgnore
	private Map<String, KeyObject> urlObjectMap = new HashMap<>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, KeyObject> getUrlObjectMap() {
		return urlObjectMap;
	}

	public void setUrlObjectMap(Map<String, KeyObject> urlObjectMap) {
		this.urlObjectMap = urlObjectMap;
	}

	@JsonProperty("objects")
	public List<KeyObject> getObjects() {
		return urlObjectMap.values().stream().collect(Collectors.toList());
	}

	@JsonProperty("objects")
	public void setObjects(List<KeyObject> keyObjects) {
		keyObjects.forEach(obj -> urlObjectMap.put(obj.getKey(), obj));
	}
}
