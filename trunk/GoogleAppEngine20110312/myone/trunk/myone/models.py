# -*- coding: utf-8 -*-
# myone.models

from google.appengine.ext import db

# Create your models here.
from kay.auth.models import GoogleUser

class MyUser(GoogleUser):
  pass
