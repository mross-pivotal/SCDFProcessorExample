/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.stream.app.transform.processor;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A Processor app that transforms messages using a SpEL expression.
 *
 * @author Eric Bottard
 * @author Marius Bogoevici
 * @author Gary Russell
 */
@EnableBinding(Processor.class)
@EnableConfigurationProperties(TransformProcessorProperties.class)
public class TransformProcessorConfiguration {

	@Autowired
	private TransformProcessorProperties properties;

	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public String transform(Object payload) {

		System.out.println("!!!!" + payload.getClass());
		//String d = payload.toString();
		JSONObject v = new JSONObject();
		try {

			ObjectMapper mapper = new ObjectMapper();
			String str = (String) payload;

			Map<String, Object> json = new HashMap<String, Object>();

			// convert JSON string to Map
			json = mapper.readValue(str, new TypeReference<Map<String, String>>() {
			});

			System.out.println("Map: " + json);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = Calendar.getInstance().getTime();
			String sDate = dateFormat.format(date);

			///System.out.println.info("Date = {}", sDate);

			json.put("Date", "Hello gemsss");
			json.put("Origin", "Matthew Ross Laptop");
			json.put("lte", "Hello gemsss");

			//			json.put("origin", "Matthew Ross's Laptop");

			String mapAsJson = null;

			mapAsJson = new ObjectMapper().writeValueAsString(json);

			v = new JSONObject(mapAsJson);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

		System.out.println("returning this : " + v);
		return v.toString();

	}
	}