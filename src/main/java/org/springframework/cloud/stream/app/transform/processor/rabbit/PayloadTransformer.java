package org.springframework.cloud.stream.app.transform.processor.rabbit;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mross on 9/21/16.
 */
public class PayloadTransformer {

  public PayloadTransformer () {

  }

  public String addOriginToPayload(Object payload, String origin) {
    JSONObject v = new JSONObject();
    try {

      ObjectMapper mapper = new ObjectMapper();
      String str = (String) payload;

      Map<String, Object> json = new HashMap<String, Object>();

      json = mapper.readValue(str, new TypeReference<Map<String, String>>() {});

      System.out.println("Map: " + json);
      json.put("Origin", origin);

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
