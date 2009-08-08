'''
Created on 2009/08/01

@author: takuji.shimokawa
'''

import cgi
from google.appengine.ext import webapp
from google.appengine.ext.webapp import template
import base_controller
import os
from ..models.user import User
from ..models.twitter_account import TwitterAccount
import logging

class UsersController(base_controller.BaseController):

  def index(self):
    user = User.get_current_user()
    if user == None:
      raise base_controller.LoginRequiredException(self.request.url)
    if self.request.get("oauth_token", None):
      twitter_account = TwitterAccount(oauth_token=self.request.get("oauth_token"))
      twitter_account.put()
      user.twitter_account = twitter_account
      user.put()
    self.redirect(user.path())

  def new(self):
    # ASSERT LOGGED IN
    self.response.headers['Content-Type'] = 'text/plain'
    self.response.out.write("UsersController NEW")

  def show(self, user_id):
    user = User.get_current_user()
    if user == None:
      raise base_controller.LoginRequiredException(self.request.url)
    editable = False
    if user.key().id() == long(user_id):
      editable = True
    template_values = {
      'user': user,
      'editable': editable
    }
    path = os.path.join(self.VIEWS_PATH, "users/show.%s" % self.response_format)
    self.response.out.write(template.render(path, template_values))

  def edit(self, user_id):
    # ASSERT LOGGED IN
    self.response.headers['Content-Type'] = 'text/plain'
    self.response.out.write("UsersController EDIT: %s" % user_id)

  def delete(self, user_id):
    # ASSERT LOGGED IN
    # ASSERT User is the owner of this message
    self.response.headers['Content-Type'] = 'text/plain'
    self.response.out.write("UsersController DELETE: %s" % user_id)

  def update(self, user_id):
    logging.info("ENTER: users_controller.update")
    user = User.get_current_user()
    if user == None:
      raise base_controller.LoginRequiredException(self.request.url)
    if user.key().id() != long(user_id):
      raise base_controller.Redirect(self.request.url)
    lat = self.request.get("lat", None)
    lon = self.request.get("lon", None)
    if lat != None and lon != None:
      # Update user location
      user.update_location(lat, lon)
      logging.info("User's location is updated!")
    
