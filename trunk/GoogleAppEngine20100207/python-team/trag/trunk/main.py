#!/usr/bin/env python

#import pdb, sys
#debugger = pdb.Pdb(stdin=sys.__stdin__,
#                   stdout=sys.__stdout__)
#debugger.set_trace(sys._getframe().f_back)

import os
os.environ['TRAC_ENV'] = './'

import trac.web.main
from google.appengine.ext.webapp import util

application = trac.web.main.dispatch_request
util.run_wsgi_app(application)