# 采用python3镜像
FROM python:3.9-slim

# 将工作目录切换到 /app
WORKDIR /app

# 将udp_server.py 拷贝到 /app 中
COPY jenkins-test.py /app/jenkins-test.py

# 暴露 8080 端口
EXPOSE 8080

# 运行 Python UDP 服务
CMD ["python", "jenkins-test.py"]
