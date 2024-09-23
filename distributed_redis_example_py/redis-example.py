import redis
from redis.cluster import ClusterNode
from redis.exceptions import RedisError

# redis集群节点配置
# startup_nodes = [
#     {"host": "172.24.0.2", "port": 7001},
#     {"host": "172.24.0.3", "port": 7002},
#     {"host": "172.24.0.4", "port": 7003},
#     {"host": "172.24.0.5", "port": 7004},
#     {"host": "172.24.0.6", "port": 7005},
#     {"host": "172.24.0.7", "port": 7006}
# ]

startup_nodes = [
    ClusterNode("172.24.0.2", 7001),
    ClusterNode("172.24.0.3", 7002),
    ClusterNode("172.24.0.4", 7003),
    ClusterNode("172.24.0.5", 7004),
    ClusterNode("172.24.0.6", 7005),
    ClusterNode("172.24.0.7", 7006)
]

# 创建 Redis 集群连接
try:
    # 使用 startup_nodes 正确连接 Redis 集群
    redis_client = redis.RedisCluster(startup_nodes=startup_nodes, decode_responses=True)

    # 设置键值对
    redis_client.set("name", "Python Redis Cluster Example")

    # 获取键值
    value = redis_client.get("name")
    print(f"Stored value: {value}")

except RedisError as e:
    print(f"Error connecting to Redis Cluster: {e}")
except Exception as e:
    print(f"An unexpected error occurred: {e}")