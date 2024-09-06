import socket

# UDP接受者1
# 配置服务监听的IP和端口
UDP_IP = "192.168.186.1"
UDP_PORT = 8080

# 创建UDP Socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((UDP_IP, UDP_PORT))

print(f"Listening on {UDP_IP}:{UDP_PORT}")

while True:
    data, addr = sock.recvfrom(1024)  # 接收数据
    print(f"Received message: {data} from {addr}")
    sock.sendto(b"Message received", addr)  # 响应客户端
