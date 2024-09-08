import socket
import threading
from http.server import BaseHTTPRequestHandler, HTTPServer

# 实例编号
INSTANCE_NUM = 2

# UDP 服务器1
UDP_IP = "192.168.186.1"
HTTP_PORT = 8083
UDP_PORT_1 = 8083
UDP_PORT_2 = 8086

# 创建UDP Socket
def udp_server(udp_ip, udp_port):
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.bind((udp_ip, udp_port))
    print(f"UDP server listening on {udp_ip}:{udp_port}")
    
    while True:
        data, addr = sock.recvfrom(1024)  # 接收数据
        print(f"Received message: {data} from {addr} on port {udp_port}")
        sock.sendto(b"Message received", addr)  # 响应客户端

# HTTP 服务器
class RequestHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()
        str = f"Hello, this is the HTTP server! Instance number: {INSTANCE_NUM}"
        self.wfile.write(str.encode())

def run_http_server(server_ip, server_port):
    httpd = HTTPServer((server_ip, server_port), RequestHandler)
    print(f"HTTP server listening on {server_ip}:{server_port}")
    httpd.serve_forever()

if __name__ == "__main__":
    # 启动UDP服务器1和UDP服务器2
    udp_thread_1 = threading.Thread(target=udp_server, args=(UDP_IP, UDP_PORT_1))
    udp_thread_2 = threading.Thread(target=udp_server, args=(UDP_IP, UDP_PORT_2))

    udp_thread_1.start()
    udp_thread_2.start()

    # 启动HTTP服务器
    http_thread = threading.Thread(target=run_http_server, args=(UDP_IP, HTTP_PORT))
    http_thread.start()

    udp_thread_1.join()
    udp_thread_2.join()
    http_thread.join()
