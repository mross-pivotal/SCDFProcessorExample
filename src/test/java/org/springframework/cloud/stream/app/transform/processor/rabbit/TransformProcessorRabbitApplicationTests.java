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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TransformProcessorRabbitApplication.class)
public class TransformProcessorRabbitApplicationTests {

	@Test
	public void testAddOriginToJson() {


		PayloadTransformer transformer = new PayloadTransformer();

		String expextedOutput = "{\"Origin\":\"Matthew Ross Laptop\",\"name\":\"Special Agent Jack Ryan\",\"id\":\"123\"}";
		String stringInput = "{\"name\":\"Special Agent Jack Ryan\",\"id\":\"123\"}";
		Object obj = (Object) stringInput;

		String transformedString = transformer.addOriginToPayload(obj,"Matthew Ross Laptop");
		assertEquals(expextedOutput,transformedString);



	}

}
