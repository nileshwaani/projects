package com.devmango.spring.restprototype.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devmango.spring.restprototype.domain.Endpoint;
import com.devmango.spring.restprototype.service.EndpointService;

@RestController
@RequestMapping(value = "/endpoints")
public class EndpointController {
	
	private EndpointService endpointService;
	
	public EndpointController(EndpointService endpointService) {
		this.endpointService = endpointService;
	}

	/**
	 * Creates multiple endpoints along with their objects.
	 * 
	 * @param endpoints - Endpoint data containing URLs and objects.
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createEndpoints(@RequestBody List<Endpoint> endpoints) {
		endpointService.createEndpoints(endpoints);
	}

	/**
	 * Creates an endpoint.
	 * 
	 * @param endpointURL - The URL of the endpoint to be created.
	 * @return - The created endpoint information.
	 */
	@PutMapping(path = "/{endpointURL}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Endpoint createEndpoint(@PathVariable String endpointURL) {
		return endpointService.createEndpoint(endpointURL);
	}

	/**
	 * Gets all the endpoints.
	 * 
	 * @return The list of endpoints along with their objects.
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Endpoint>> getAllEndpoints() {
		if(endpointService.getAllEndpoints().isEmpty()) {
			return new ResponseEntity<List<Endpoint>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Endpoint>>(endpointService.getAllEndpoints(), HttpStatus.OK);
	}

	/**
	 * Gets detail about specific endpoint along with its objects.
	 * 
	 * @param endpointURL - The endpoint URL for which we need to fetch data.
	 * @return - Endpoint data containing its objects.
	 */
	@GetMapping(path = "/{endpointURL}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Endpoint> getEndpoint(@PathVariable String endpointURL) {
		Endpoint endpoint = endpointService.getEndpoint(endpointURL);
		if(endpoint == null) {
			return new ResponseEntity<Endpoint>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Endpoint>(endpoint, HttpStatus.OK);
	}

	/**
	 * Deletes a specific endpoint.
	 * 
	 * @param endpointURL - The endpoint URL that needs to be deleted.
	 */
	@DeleteMapping(path = "/{endpointURL}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEndpoint(@PathVariable String endpointURL) {
		endpointService.deleteEndpoint(endpointURL);
	}

	/**
	 * Deletes all endpoints.
	 */
	@DeleteMapping()
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllEndpoints() {
		endpointService.deleteAllEndpoints();
	}
}