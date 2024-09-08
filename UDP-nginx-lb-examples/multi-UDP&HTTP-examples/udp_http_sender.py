import socket
import time
import requests
 
# UDP发送方 
# Udp client发送消息
def udp_client(message, server_ip, server_port):
    # 创建一个UDP套接字
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    
    try:
        # 将消息编码为字节并发送到指定的服务器和端口
        print(f"Sending message to {server_ip}:{server_port}")
        sock.sendto(message.encode(), (server_ip, server_port))

        # 接收响应数据
        data, server = sock.recvfrom(1024)  # 2048字节的缓冲区大小
        print(f"Received response from server: {data.decode()}")

    finally:
        # 关闭套接字
        sock.close()
        
# HTTP发送方
def http_client(server_ip, server_port):
    url = f"http://{server_ip}:{server_port}"
    try:
        # 发送HTTP GET请求
        print(f"Sending HTTP request to {url}")
        response = requests.get(url)
        print(f"Received HTTP response: {response.status_code} - {response.text}")
    except requests.RequestException as e:
        print(f"HTTP request failed: {e}")

if __name__ == "__main__":
    # 需要发送的消息
    message1 = "Hello, this is a test message for UDP 8080: "
    message2 = "Hello, this is a test message for UDP 8081: "
    
    # 目标服务器IP和端口号
    server_ip = "192.168.186.1"  # 本地
    server_port1 = 8080       # 目标端口
    server_port2 = 8081       # 目标端口
    http_port = 8080   # HTTP端口
    i = 1
    while True:
        # 调用UDP客户端函数发送消息
        message_new1 = message1 + str(i)
        message_new2 = message2 + str(i)
        i = i + 1
        udp_client(message_new1, server_ip, server_port1)
        udp_client(message_new2, server_ip, server_port2)
        http_client(server_ip, http_port)
        # 每秒发送一次
        time.sleep(1)
