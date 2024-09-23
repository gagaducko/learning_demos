# 采用python3镜像
FROM python:3.9-slim

# 将工作目录切换到 /app
WORKDIR /app

# 将udp_server.py 拷贝到 /app 中
COPY redis-example.py /app/redis-example.py

# 安装依赖库
RUN pip install --no-cache-dir redis

# 运行 Python UDP 服务
CMD ["python", "redis-example.py"]
