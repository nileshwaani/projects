package com.devmango.spring.restprototype.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmango.spring.restprototype.domain.Endpoint;
import com.devmango.spring.restprototype.domain.KeyObject;
import com.devmango.spring.restprototype.persistence.EndpointStorage;

@RestController
@RequestMapping(value = "/")
public class GenericController {

	/**
	 * Creates a new object for the given endpoint (service).
	 * For example, PUT to /employees/10 will create a new employee with ID 10.
	 * 
	 * @param endpointURL - The endpoint URL of the service to invoke.
	 * @param identifier - The ID of the new object.
	 * @param objectStructure - The JSON data of the new object.
	 * @return The JSON data of the new object.
	 */
	@PutMapping(path = "/{endpointURL}/{identifier}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> createObject(@PathVariable String endpointURL, @PathVariable String identifier,
			@RequestBody String objectStructure) {
		Endpoint endpoint = EndpointStorage.getUrlEndpointMap().get(endpointURL);
		if(endpoint == null) {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
			return new ResponseEntity<String>("The endpoint /" + endpointURL + " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
		}
		endpoint.getUrlObjectMap().put(identifier, new KeyObject(identifier, objectStructure));
		return new ResponseEntity<String>(objectStructure, HttpStatus.CREATED);
	}

	/**
	 * Gets all the objects for the given endpoint (service).
	 * For example, GET to /employees will get all the employees.
	 * 
	 * @param endpointURL - The endpoint URL of the service to invoke.
	 * @return List of objects for that service.
	 */
	@GetMapping(path = "/{endpointURL}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> getObjects(@PathVariable String endpointURL) {
		Endpoint endpoint = EndpointStorage.getUrlEndpointMap().get(endpointURL);
		if(endpoint == null) {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
			return new ResponseEntity<String>("The endpoint /" + endpointURL + " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
		}
		if(endpoint.getUrlObjectMap().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		String objects = endpoint.getUrlObjectMap().values().stream().map(val -> val.getObject()).collect(Collectors.toList()).toString();
		return new ResponseEntity<String>(objects, HttpStatus.OK);
	}

	/**
	 * Gets a specific object for the given endpoint (service).
	 * For example, GET to /employees/10 will get employee with ID = 10.
	 * 
	 * @param endpointURL - The endpoint URL of the service to invoke.
	 * @param identifier - The ID of the object.
	 * @return The JSON data for the object.
	 */
	@GetMapping(path = "/{endpointURL}/{identifier}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> getObject(@PathVariable String endpointURL, @PathVariable String identifier) {
		Endpoint endpoint = EndpointStorage.getUrlEndpointMap().get(endpointURL);
		if(endpoint == null) {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
			return new ResponseEntity<String>("The endpoint /" + endpointURL + " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
		}
		KeyObject keyObject = endpoint.getUrlObjectMap().get(identifier);
		if(keyObject == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<String>(keyObject.getObject(), HttpStatus.OK);
	}

	/**
	 * Deletes a specific object for the given endpoint (service).
	 * For example, DELETE to /employees/10 will delete employee with ID = 10.
	 * 
	 * @param endpointURL - The endpoint URL of the service to invoke.
	 * @param identifier - The ID of the object to be deleted.
	 * @return 
	 */
	@DeleteMapping(path = "/{endpointURL}/{identifier}")
	public ResponseEntity<String> deleteEndpoint(@PathVariable String endpointURL, @PathVariable String identifier) {
		Endpoint endpoint = EndpointStorage.getUrlEndpointMap().get(endpointURL);
		if(endpoint == null) {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
			return new ResponseEntity<String>("The endpoint /" + endpointURL + " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
		}
		endpoint.getUrlObjectMap().remove(identifier);
		return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
	}

	/**
	 * Deletes all objects for the given endpoint (service).
	 * For example, DELETE to /employees will delete all employees.
	 * 
	 * @param endpointURL - The endpoint URL of the service to invoke.
	 * @return 
	 */
	@DeleteMapping(path = "/{endpointURL}")
	public ResponseEntity<String> deleteEndpoint(@PathVariable String endpointURL) {
		Endpoint endpoint = EndpointStorage.getUrlEndpointMap().get(endpointURL);
		if(endpoint == null) {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
			return new ResponseEntity<String>("The endpoint /" + endpointURL + " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
		}
		endpoint.getUrlObjectMap().clear();
		return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
	}
}
