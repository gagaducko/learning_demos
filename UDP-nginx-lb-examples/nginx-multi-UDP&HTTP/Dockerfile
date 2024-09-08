# 基于官方的 Nginx 镜像
FROM nginx:1.21-alpine

# 将本地的 nginx.conf 文件复制到容器中
COPY nginx.conf /etc/nginx/nginx.conf

# 暴露 8080 端口来接收 UDP 流量
EXPOSE 8080/udp
EXPOSE 8081/udp
EXPOSE 8080
