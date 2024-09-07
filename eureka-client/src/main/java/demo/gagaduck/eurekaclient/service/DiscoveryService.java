package demo.gagaduck.eurekaclient.service;

import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DiscoveryService {

    @Resource
    private DiscoveryClient discoveryClient;

    // 获得对应服务的实例
    public List<ServiceInstance> getServiceInstances(String serviceName) {
        return discoveryClient.getInstances(serviceName);
    }

    // 获取所有服务ID
    public List<String> getAllServices() {
        return discoveryClient.getServices();
    }

    // 获取所有服务的所有服务实例
    public Map<String, List<ServiceInstance>> getAllServiceInstances() {
        return getAllServices().stream()
                .collect(Collectors.toMap(
                        serviceId -> serviceId,
                        this::getServiceInstances
                ));
    }
}
