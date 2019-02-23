package com.devmango.spring.restprototype.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devmango.spring.restprototype.domain.Endpoint;
import com.devmango.spring.restprototype.persistence.EndpointStorage;

@Service
public class EndpointService {

	public Endpoint createEndpoint(String endpointURL) {
		Endpoint endpoint = new Endpoint();
		endpoint.setUrl(endpointURL);
		EndpointStorage.getUrlEndpointMap().put(endpointURL, endpoint);
		return endpoint;
	}

	public List<Endpoint> getAllEndpoints() {
		return EndpointStorage.getUrlEndpointMap().values().stream().collect(Collectors.toList());
	}

	public Endpoint getEndpoint(String endpointURL) {
		return EndpointStorage.getUrlEndpointMap().get(endpointURL);
	}

	public void deleteEndpoint(String endpointURL) {
		EndpointStorage.getUrlEndpointMap().remove(endpointURL);
	}

	public void createEndpoints(List<Endpoint> endpoints) {
		endpoints.forEach(endpoint -> {
			EndpointStorage.getUrlEndpointMap().put(endpoint.getUrl(), endpoint);
		});
	}

	public void deleteAllEndpoints() {
		EndpointStorage.getUrlEndpointMap().clear();
	}
}
