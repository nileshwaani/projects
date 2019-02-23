package com.devmango.spring.restprototype.domain;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class KeyObject {
	@JsonRawValue
	private String key;

	@JsonIgnore
	private String object;

	public KeyObject() {
	}

	public KeyObject(String key, String object) {
		this.key = key;
		this.object = object;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@JsonRawValue
	@JsonProperty
	public String getObject() {
		return object;
	}

	@JsonProperty
	@JsonDeserialize(using = ObjectDeserializer.class)
	public void setObject(String object) {
		this.object = object;
	}

	private static class ObjectDeserializer extends JsonDeserializer<Object> {
		@Override
		public String deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			JsonNode node = p.getCodec().readTree(p);
			return node.toString();
		}
	}
}
