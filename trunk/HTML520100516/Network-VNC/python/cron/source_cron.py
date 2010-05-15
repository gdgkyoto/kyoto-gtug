#!/usr/bin/env python2.6

# Server Sent Events 用 特定ディレクトリを監視してsource.py動作変更のフラグをたてる

import os
import re
import commands
import shutil
import time

dropdir = '/Users/likr/Music/dropbox'
distdir = '/Users/likr/Sites/musics'
tmppath = '/tmp/source.timestamp'
flagpath = '/tmp/source.flag'
p = re.compile('.+\.ogg')
while True:
    if 'source.flag' in os.listdir('/tmp'):
        try:
            os.remove(tmppath)
        except (IOError,OSError):
            pass
    files = [f for f in os.listdir(dropdir) if p.match(f)]
    try:
        filename = files[0]
        src = '%s/%s' % (dropdir, filename)
        dst = '%s/%s' % (distdir, filename)
        shutil.move(src, dst)
        f = open(tmppath, 'w')
        f.write(filename)
        f.close()
    except IndexError:
        pass
    time.sleep(2)

