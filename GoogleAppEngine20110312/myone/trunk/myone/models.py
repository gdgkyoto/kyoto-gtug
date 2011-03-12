# -*- coding: utf-8 -*-
# myone.models

from google.appengine.ext import db

# Create your models here.
from kay.auth.models import GoogleUser
import kay.db

class MyUser(GoogleUser):
  pass

class Competition(db.Model):
  name = db.TextProperty(required=True)
  detail = db.TextProperty(required=True)

class Record(db.Model):
  user = kay.db.OwnerProperty()
  competition = db.ReferenceProperty(Competition,collection_name='competitions')
  time = db.IntegerProperty(required=True)
  youtubeurl = db.TextProperty(required=True)
  respect = db.IntegerProperty(required=True)
  comment = db.TextProperty(required=True)
  created = db.DateTimeProperty(auto_now_add=True)

