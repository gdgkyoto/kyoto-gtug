'''
Created on 2009/08/01

@author: takuji.shimokawa
'''

import cgi
#from google.appengine.api import users
from google.appengine.ext import webapp
from google.appengine.api.labs import taskqueue
from google.appengine.ext import db
import os
from google.appengine.ext.webapp import template
from google.appengine.api import images
import logging
import base_controller
from ..models.message import Message
from ..models.location import Location
from ..models.user import User
from ..models.user_location import UserLocation
from ..models.user_location import UserLocation
from twitter_oauth_handler import OAuthClient

class MessagesController(base_controller.BaseController):
  
  def index(self):
    logging.info("ENTER:MessagesController.index")
    user_id = self.request.get("user_id", None)

    offset = 0 
    if self.request.get("offset", None):
      offset = int(self.request.get("offset", None))

    if self.request.get("lat1", None):
      p1 = (float(self.request.get("lat1")), float(self.request.get("lon1")))
      p2 = (float(self.request.get("lat2")), float(self.request.get("lon2")))
      area = (max(p1[0], p2[0]), max(p1[1], p2[1]), min(p1[0], p2[0]), min(p1[1], p2[1]))
      logging.info("AREA:%s", str(area))
      locations = Location.find_in_area(area, 20, user_id=user_id)
      logging.info("LOCATIONS:%d", len(locations))
      messages = [Message.find_by_location(location) for location in locations]
      logging.info("MESSAGES:%d", len(messages))
    elif self.request.get("lat", None):
      center = db.GeoPt(float(self.request.get("lat")), float(self.request.get("lon")))
      radius = float(self.request.get("rad"))
      locations = Location.find_in_circle(center, radius, 20)
      messages = [Message.find_by_location(location) for location in locations]
    else:
      query = Message.all().order("-created")
      messages = query.fetch(20, offset)
    template_values = {
      'messages': messages
    }
    path = os.path.join(os.path.dirname(__file__), "../../views/messages/index.%s" % self.response_format)
    self.response.out.write(template.render(path, template_values))

  def new(self):
    # ASSERT LOGGED IN
    user = User.get_current_user()
    if user == None:
      raise base_controller.LoginRequiredException(self.request.uri)
    template_values = {
      'logout_url': User.logout_url(self.request.uri)
    }
    path = os.path.join(os.path.dirname(__file__), '../../views/messages/new.html')
    self.response.out.write(template.render(path, template_values))

  def show(self, message_id):
    user = User.get_current_user()
    message = Message.get_by_id(long(message_id))
    editable = False
    if message == None:
      raise base_controller.Redirect('/')
    if user != None and user.google_account == message.user:
      editable = True
    template_values = {
      'editable': editable,
      'message': message
    }
    path = os.path.join(os.path.dirname(__file__), "../../views/messages/show.%s" % self.response_format)
    self.response.out.write(template.render(path, template_values))
    
  def create(self):
    # ASSERT LOGGED IN
    logging.info("ENTER:MessagesController.create")
    user = User.get_current_user()
    if user == None:
      raise base_controller.LoginRequiredException('/')
    text = self.request.get("text")
    longitude = self.request.get("longitude")
    latitude = self.request.get("latitude")
    logging.info("PLACE(%s,%s)", longitude, latitude)
    place = None
    if longitude != None and latitude != None and len(longitude) > 0 and len(latitude) > 0:
      lat = float(latitude)
      lon = float(longitude)
      try:
        place = Location(location=db.GeoPt(lat, lon), user_id=user.key().id())
        place.update_location()
        place.put()
        logging.info("Message's locaiton=%s", str(place))
      except Exception, e:
        logging.warn(e)
        place = None
    message = Message(user=user, text=text)
    if place:
      message.place = place
    message.put()

#    tweet_post = OAuthClient('twitter',self)
#    print tweet_post.post('http')

    if user.twitter_account:
      logging.debug("Posting a new tweet!")
      taskqueue.add(url='/tweets',
                    params={'oauth_token': user.twitter_account.oauth_token,
                            'tweet': text,
                            'user_id': user.key().id(),
                            'message_id': message.key().id()})
      logging.debug("Posted a new tweet!")
    
    #if user.geocast:
    if True:
      logging.debug("GEO casting.")
      logging.info("GEO casting.")
      taskqueue.add(url='/geocast',
                    params={
                            'message_id': message.key().id(),
                    })
      logging.debug("GEO casted.")
      logging.info("GEO casted.")
    if False:
      radius = 100 * 1000 #[m]
      near_users = UserLocation.find_in_circle(message.place.location, radius)
      email_addrs = [User.get_by_id(loc.user_id).google_account.email() for loc in near_users]
      result = email_addrs
      logging.debug("EMAIL ADDR: %s", result)
      from google.appengine.api import mail
      body = """\
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


    if self.response_format == 'json':
      path = os.path.join(os.path.dirname(__file__), "../../views/messages/show.json")
      self.response.out.write(template.render(path, {'message': message}))
    else:
      self.redirect("/messages?user_id=%d" % user.key().id())
                       

  def edit(self, message_id):
    # ASSERT LOGGED IN
    user = User.get_current_user()
    if user == None:
      raise LoginRequiredException(self.request.uri)
    self.response.headers['Content-Type'] = 'text/plain'
    self.response.out.write("MessagesController EDIT: %s NOT IMPLEMENTED" % message_id)

  def delete(self, message_id):
    # ASSERT LOGGED IN
    # ASSERT User is the owner of this message
    user = User.get_current_user()
    if user == None:
      raise LoginRequiredException(self.request.uri)
    message = Message.get_by_id(long(message_id))
    if user != message.user:
      raise "No permission"
    self.response.headers['Content-Type'] = 'text/plain'
    self.response.out.write("MessagesController DELETE: %s NOT IMPLEMENTED" % message_id)
