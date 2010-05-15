#!/usr/bin/env python2.6

# Server Sent Events 用

print 'Content-Type: text/event-stream'
print
print 'retry: 2000'
print
import os

tmpname = 'source.timestamp'
tmppath = '/tmp/' + tmpname
flagname = 'source.flag'
flagpath = '/tmp/' + flagname
try:
    name = open(tmppath).readline()
except IOError:
    try:
        os.remove(flagpath)
    except OSError:
        pass
    exit()
f = open(flagpath, 'w')
f.write('dummy')
f.close()
print 'data: %s' % name

