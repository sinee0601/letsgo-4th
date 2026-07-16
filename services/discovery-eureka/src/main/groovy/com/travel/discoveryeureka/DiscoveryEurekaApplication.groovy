package com.travel.discoveryeureka

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class DiscoveryEurekaApplication {

    static void main(String[] args) {
        SpringApplication.run(DiscoveryEurekaApplication, args)
    }

}
