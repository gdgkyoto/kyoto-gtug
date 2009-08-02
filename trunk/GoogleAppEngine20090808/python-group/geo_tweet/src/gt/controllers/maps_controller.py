'''
Created on 2009/08/01

@author: takuji.shimokawa
'''

import os
from google.appengine.ext import webapp
from google.appengine.ext.webapp import template
import logging
from ..models.message import Message
from ..models.user import User
import base_controller

class MapsController(base_controller.BaseController):
      
  def index(self):
    logging.info("ENTER:MapsController.index")
    auth_url = None
    auth_url_string = None
    user = User.get_current_user()
    if user != None:
      auth_url = User.logout_url(self.request.uri)
      auth_url_string = 'logout'
    else:
      auth_url = User.login_url(self.request.uri)
      auth_url_string = 'login'
    query = Message.all().order("-created")
    messages = query.fetch(20)
    template_values = {
      'messages': messages,
      'auth_url': auth_url,
      'auth_url_string': auth_url_string
    }
    path = os.path.join(os.path.dirname(__file__), "../../views/maps/index.html")
    self.response.out.write(template.render(path, template_values))

  def show(self, user_id):
    logging.info("ENTER:MapsController.show")
    user = User.get_by_id(long(user_id))
    if user == None:
      raise base_controller.Redirect('/')
    query = Message.all().order("-created").filter("user =", user)
    messages = query.fetch(20)
    template_values = {
      'messages': messages
    }
    path = os.path.join(os.path.dirname(__file__), '../../views/maps/show.html')
    self.response.out.write(template.render(path, template_values))

