import socket
import time
 
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

if __name__ == "__main__":
    # 需要发送的消息
    message = "Hello, this is a test message"
    
    # 目标服务器IP和端口号
    server_ip = "192.168.186.1"  # 本地
    server_port = 8080       # 目标端口
    i = 1
    while True:
        # 调用UDP客户端函数发送消息
        message_new = message + str(i)
        i = i + 1
        udp_client(message_new, server_ip, server_port)
        # 每秒发送一次
        time.sleep(1)
