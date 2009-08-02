'''
Created on 2009/08/01

@author: takuji.shimokawa
'''

from google.appengine.ext import db

class TwitterAccount(db.Model):
  user = db.UserProperty(required=True)
  account = db.StringProperty(required=True)
  password = db.StringProperty(required=True)
