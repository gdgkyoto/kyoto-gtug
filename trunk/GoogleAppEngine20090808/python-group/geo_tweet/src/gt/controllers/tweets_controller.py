'''
Created on 2009/08/01

@author: takuji.shimokawa
'''

import logging
import base_controller
from ..models.user import User


class TweetsController(base_controller.BaseController):
  
  def index(self):
    logging.info("ENTER:MessagesController.index")
    raise base_controller.Redirect('/')

  def new(self):
    # ASSERT LOGGED IN
    raise base_controller.Redirect('/')

  def show(self, message_id):
    user = User.get_current_user()
    raise base_controller.Redirect('/')
    
  def create(self):
    # ASSERT LOGGED IN
    logging.info("ENTER:TweetsController.create")
    oauth_token = self.request.get("oauth_token", None)
    tweet = self.request.get("tweet", None)
    if oauth_token == None or tweet == None:
      logging.warn("invalid parameters")
      return
    logging.info("tweet=%s, oauth_token=%s", tweet, oauth_token)
    logging.info("Tweet was successfully posted")
    
  def edit(self, message_id):
    # ASSERT LOGGED IN
    user = User.get_current_user()
    if user == None:
      raise LoginRequiredException(self.request.uri)
    raise base_controller.Redirect('/')

  def delete(self, message_id):
    # ASSERT LOGGED IN
    # ASSERT User is the owner of this message
    user = User.get_current_user()
    if user == None:
      raise LoginRequiredException(self.request.uri)
    raise base_controller.Redirect('/')
