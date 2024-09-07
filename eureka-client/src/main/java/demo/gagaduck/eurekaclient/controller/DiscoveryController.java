package demo.gagaduck.eurekaclient.controller;

import demo.gagaduck.eurekaclient.service.DiscoveryService;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/discovery")
public class DiscoveryController {
    @Resource
    private DiscoveryService discoveryService;

    @GetMapping("/service/{serviceName}")
    public List<ServiceInstance> getServiceInstances(@PathVariable String serviceName) {
        return discoveryService.getServiceInstances(serviceName);
    }

    // 获取所有服务的所有服务实例
    @GetMapping("/serviceInstances")
    public ResponseEntity<Map<String, List<ServiceInstance>>> getAllServiceInstances() {
        Map<String, List<ServiceInstance>> allServiceInstances = discoveryService.getAllServiceInstances();
        return ResponseEntity.ok(allServiceInstances);
    }

    // 获取所有服务ID
    @GetMapping("/services")
    public ResponseEntity<List<String>> getAllServices() {
        List<String> serviceIds = discoveryService.getAllServices();
        return ResponseEntity.ok(serviceIds);
    }

}
