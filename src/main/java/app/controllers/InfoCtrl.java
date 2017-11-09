package app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/info")
public class InfoCtrl {

    @Value("${api-version}")
    private String API_VERSION;

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String version() {
        return API_VERSION;
    }
}