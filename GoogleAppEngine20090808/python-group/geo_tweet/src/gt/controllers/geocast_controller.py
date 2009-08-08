'''
Created on 2009/08/08

@author: djmchl / Takanao ENDOH / MiCHiLU
'''

import logging
import base_controller

from gt.controllers.messages_controller import Location
from ..models.message import Message
from ..models.user import User
from ..models.user_location import UserLocation

class GEOCastController(base_controller.BaseController):
  
  def index(self):
    pass

  def new(self):
    pass

  def show(self, message_id):
    pass
    
  def create(self):
    logging.info("ENTER:GEOCastController.create")
    message_id = self.request.get("message_id", None)
    if message_id == None:
      logging.warn("invalid parameters")
      return
    message = Message.get_by_id(long(message_id))
    if message:
      radius = 100 * 1000 #[m]
      near_users = UserLocation.find_in_circle(message.place.location, radius)
      email_addrs = [User.get_by_id(loc.user_id).google_account.email() for loc in near_users]
      result = email_addrs
      logging.debug("EMAIL ADDR: %s", result)
      from google.appengine.api import mail
      body="""\
TEST
%s
"""
      email = mail.EmailMessage(sender="example.com support <support@example.com>",
                                  subject="New tweet in you around.")
      email.body = body % message.text
      logging.debug("EMAIL BODY: %s", message.text)
      for addr in email_addrs:
        email.to = addr
        email.send()
        logging.debug("SEND EMAIL: [%s] %s", addr, message)
    logging.info("GEO cast was successful")
    
  def edit(self, message_id):
    pass

  def delete(self, message_id):
    pass
