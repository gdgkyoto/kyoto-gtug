'''
Created on 2009/08/01

@author: takuji.shimokawa
'''

from google.appengine.ext import db

class TwitterAccount(db.Model):
  oauth_token = db.StringProperty(required=True)
