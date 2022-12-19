package com.example.mcpservice;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class McpService {

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/xml")
    @ResponseBody
    public String Xml(@RequestBody String body) {

        String url = "http://mms.smartnav.org:8088";
        String clientMRN = "urn:mrn:mcp:device:smart:kor:bluemap-test-client";
        String serviceMRN = "urn:mrn:mcp:service:smart:kor:instance:bluemap-mcp-service-sample";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "xml", Charset.forName("UTF-8")));
        headers.set("dstMrn", clientMRN);
        headers.set("srcMrn", serviceMRN);

        String result = body;
        HttpEntity<String> request = new HttpEntity<>(result, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return "OK";
    }
}
