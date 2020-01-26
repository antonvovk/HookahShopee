package com.wolf.hookahshopee.novaposhta;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/novaposhta")
public class NovaposhtaController {

    private final RestTemplate restTemplate;

    public NovaposhtaController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllCities(@RequestParam(name = "name", required = false) String name) {
        JSONObject methodProperties = new JSONObject();
        if (name != null) {
            methodProperties.put("FindByString", name);
        }

        JSONObject obj = new JSONObject();
        obj.put("modelName", "Address");
        obj.put("calledMethod", "getCities");
        obj.put("methodProperties", methodProperties);
        obj.put("apiKey", "bbc22586fca5307912fdf35f52fabbb6");

        HttpEntity<String> request = new HttpEntity<>(obj.toString());
        return restTemplate.postForObject("https://api.novaposhta.ua/v2.0/json/", request, String.class);
    }

    @GetMapping(value = "/warehouses", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findWarehouseByCity(@RequestParam(name = "name") String name) {
        JSONObject methodProperties = new JSONObject();
        methodProperties.put("CityName", name);
        JSONObject obj = new JSONObject();
        obj.put("modelName", "Address");
        obj.put("calledMethod", "getWarehouses");
        obj.put("methodProperties", methodProperties);
        obj.put("apiKey", "bbc22586fca5307912fdf35f52fabbb6");

        HttpEntity<String> request = new HttpEntity<>(obj.toString());
        return restTemplate.postForObject("https://api.novaposhta.ua/v2.0/json/", request, String.class);
    }
}
