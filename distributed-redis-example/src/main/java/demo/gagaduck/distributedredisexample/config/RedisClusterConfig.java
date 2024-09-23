package demo.gagaduck.distributedredisexample.config;

import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.util.Arrays;

@Configuration
public class RedisClusterConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // 配置Redis集群节点
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(
                Arrays.asList("172.24.0.2:7001", "172.24.0.3:7002", "172.24.0.4:7003",
                        "172.24.0.5:7004", "172.24.0.6:7005", "172.24.0.7:7006"));

        // 配置拓扑刷新选项
        ClusterTopologyRefreshOptions refreshOptions = ClusterTopologyRefreshOptions.builder()
                .enablePeriodicRefresh(Duration.ofMinutes(10))  // 每10分钟刷新集群拓扑
                .enableAllAdaptiveRefreshTriggers()            // 启用自适应刷新
                .build();

        // 配置集群客户端选项
        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
                .topologyRefreshOptions(refreshOptions)
                .build();

        // 配置 Lettuce 客户端配置
        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
                .clientOptions(clusterClientOptions)
                .build();

        // 创建 Lettuce 连接工厂
        return new LettuceConnectionFactory(redisClusterConfiguration, clientConfiguration);
    }
}

