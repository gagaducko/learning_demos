from http.server import BaseHTTPRequestHandler, HTTPServer

class SimpleHTTPRequestHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/plain')
        self.end_headers()
        self.wfile.write(b"hello! Version here is 1.0, and version is 1.0 now!")

def run(server_class=HTTPServer, handler_class=SimpleHTTPRequestHandler):
    server_address = ('', 8080)
    httpd = server_class(server_address, handler_class)
    print("hello! Version here is 1.0, and version is 1.0 now!")
    httpd.serve_forever()

if __name__ == '__main__':
    run()
