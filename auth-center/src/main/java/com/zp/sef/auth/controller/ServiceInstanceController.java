package com.zp.sef.auth.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ServiceInstanceController
 *
 * @author ZP
 */
@RestController
public class ServiceInstanceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/services")
    public List<String> getServices() {
        return discoveryClient.getServices().stream()
                .map(serviceId -> discoveryClient.getInstances(serviceId))
                .flatMap(instances -> instances.stream())
                .map(instance -> instance.getHost() + ":" + instance.getPort())
                .collect(Collectors.toList());
    }

}
