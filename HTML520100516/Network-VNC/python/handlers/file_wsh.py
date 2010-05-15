# -*- coding:utf-8 -*-
# pywebsocketのハンドラ

from mod_pywebsocket import msgutil
import time

def web_socket_do_extra_handshake(request):
    pass

def web_socket_transfer_data(request):
    while True:
        message = msgutil.receive_message(request)
        f = open('/tmp/%s' % str(int(time.time())), 'wb')
        try:
            message = ''.join(chr(ord(c)) for c in message)
            f.write(bytes(message))
        except Exception, e:
            print e
        f.close()

