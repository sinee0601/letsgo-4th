package com.travel.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayRestController {
    @GetMapping("/health")
    public String gateway(){
        return "ok";
    }
}
