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

package org.springframework.cloud.stream.app.transform.processor.rabbit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TransformProcessorRabbitApplicationTests {

	@Test
	public void testAddOriginToJson() {


		PayloadTransformer transformer = new PayloadTransformer();

		String expextedOutput = "{\"sourceId\":\"102\",\"Origin\":\"Matthew Ross Laptop\",\"name\":\"Truck Number 123\",\"id\":\"123\"}";
		String stringInput = "{\"name\":\"Truck Number 123\",\"id\":\"123\",\"sourceId\":\"102\"}";
		Object obj = (Object) stringInput;

		String transformedString = transformer.addOriginToPayload(obj);
		assertEquals(expextedOutput,transformedString);

		expextedOutput = "{\"sourceId\":\"105\",\"Origin\":\"Rob Mee Laptop\",\"name\":\"Truck Number 166\",\"id\":\"166\"}";
		stringInput = "{\"name\":\"Truck Number 166\",\"id\":\"166\",\"sourceId\":\"105\"}";
		obj = (Object) stringInput;

		transformedString = transformer.addOriginToPayload(obj);
		assertEquals(expextedOutput,transformedString);

		expextedOutput = "{\"Origin\":\"ORIGIN N/A\",\"name\":\"Truck Number 166\",\"id\":\"166\"}";
		stringInput = "{\"name\":\"Truck Number 166\",\"id\":\"166\"}";
		obj = (Object) stringInput;

		transformedString = transformer.addOriginToPayload(obj);
		assertEquals(expextedOutput,transformedString);



	}

}
