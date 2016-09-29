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

  HashMap<String,String> sourceLookup;
  public PayloadTransformer () {
      this.sourceLookup = new HashMap<>();
      sourceLookup.put("102","Matthew Ross Laptop");
      sourceLookup.put("105","Rob Mee Laptop");
  }

  public String addOriginToPayload(Object payload) {
    JSONObject v = new JSONObject();


    System.out.println(payload);
    try {

      ObjectMapper mapper = new ObjectMapper();
      String str = (String) payload;

      Map<String, String> json = new HashMap<String, String>();

      json = mapper.readValue(str, new TypeReference<Map<String, String>>() {});
      String sourceId = (String) json.get("sourceId");
      if (sourceLookup.containsKey(sourceId)) {

        String origin = sourceLookup.get(sourceId);
        System.out.println("Map: " + json.get("name"));
        json.put("Origin", origin);
      } else {
        json.put("Origin", "ORIGIN N/A");
      }


      String mapAsJson = null;
      mapAsJson = new ObjectMapper().writeValueAsString(json);

      v = new JSONObject(mapAsJson);
    } catch (Exception e) {
      System.out.println(e);
    }



    System.out.println("returning this : " + v);
    return v.toString();
  }
}
