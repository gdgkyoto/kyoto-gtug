#!/usr/bin/env python

#import pdb, sys
#debugger = pdb.Pdb(stdin=sys.__stdin__,
#                   stdout=sys.__stdout__)
#debugger.set_trace(sys._getframe().f_back)

import os
os.environ['TRAC_ENV'] = '/Developer/IDEs/project/hackathon/workspace/trag2/data/sample'

import trac.web.main
application = trac.web.main.dispatch_request
