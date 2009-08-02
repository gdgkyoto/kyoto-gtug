'''
Created on 2009/08/01

@author: takuji.shimokawa
'''

import cgi
from google.appengine.ext import webapp
from google.appengine.ext.webapp import template
import base_controller


class UsersController(base_controller.BaseController):

  def index(self):
    self.response.headers['Content-Type'] = 'text/plain'
    self.response.out.write("UsersController INDEX")

  def new(self):
    # ASSERT LOGGED IN
    self.response.headers['Content-Type'] = 'text/plain'
    self.response.out.write("UsersController NEW")

  def show(self, user_id):
    self.response.headers['Content-Type'] = 'text/plain'
    self.response.out.write("UsersController SHOW: %s" % user_id)

  def edit(self, user_id):
    # ASSERT LOGGED IN
    self.response.headers['Content-Type'] = 'text/plain'
    self.response.out.write("UsersController EDIT: %s" % user_id)

  def delete(self, user_id):
    # ASSERT LOGGED IN
    # ASSERT User is the owner of this message
    self.response.headers['Content-Type'] = 'text/plain'
    self.response.out.write("UsersController DELETE: %s" % user_id)
