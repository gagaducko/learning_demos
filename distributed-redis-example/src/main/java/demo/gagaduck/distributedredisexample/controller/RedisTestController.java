package demo.gagaduck.distributedredisexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Properties;

@RestController
public class RedisTestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    // 设置一个键值对
    @GetMapping("/setKey")
    public String setKey() {
        stringRedisTemplate.opsForValue().set("key1", "value1");
        return "Key 'key1' set to 'value1'";
    }

    // 获取键的值
    @GetMapping("/getKey")
    public String getKey() {
        String value = stringRedisTemplate.opsForValue().get("key1");
        return "Value for 'key1': " + value;
    }

    // 获取当前节点信息
    @GetMapping("/nodeInfo")
    public String getNodeInfo() {
        RedisClusterConnection clusterConnection = (RedisClusterConnection) redisConnectionFactory.getClusterConnection();

        // 获取当前节点的具体信息
        Properties info = clusterConnection.info("server");
        StringBuilder infoBuilder = new StringBuilder();
        info.forEach((key, value) -> infoBuilder.append(key).append(": ").append(value).append("\n"));

        return "Current Node Info:\n" + infoBuilder.toString();
    }

    // 获取Redis集群拓扑信息
    @GetMapping("/clusterInfo")
    public String getClusterInfo() {
        RedisClusterConnection clusterConnection = (RedisClusterConnection) redisConnectionFactory.getClusterConnection();

        // 获取集群节点信息
        Iterable<RedisClusterNode> clusterNodes = clusterConnection.clusterGetNodes();
        StringBuilder clusterInfo = new StringBuilder();

        for (RedisClusterNode node : clusterNodes) {
            clusterInfo.append("Node ID: ").append(node.getId()).append("\n");
            clusterInfo.append("Address: ").append(Objects.requireNonNull(node.getHost())).append("\n");
            clusterInfo.append("Master: ").append(node.isMaster()).append("\n");
            clusterInfo.append("Slots: ").append(node.getSlotRange()).append("\n");
            clusterInfo.append("-----------------------\n");
        }

        return "Cluster Info:\n" + clusterInfo.toString();
    }
}
