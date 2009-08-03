'''
Created on 2009/08/01

@author: takuji.shimokawa
'''

import logging
import base_controller

class TweetsController(base_controller.BaseController):
  
  def index(self):
    pass

  def new(self):
    pass

  def show(self, message_id):
    pass
    
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
    pass

  def delete(self, message_id):
    pass
