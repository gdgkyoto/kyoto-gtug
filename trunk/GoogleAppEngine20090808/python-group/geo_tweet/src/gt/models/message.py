from google.appengine.ext import db
from location import Location
from user import User
import logging
import datetime, time

class Message(db.Model):
  user = db.ReferenceProperty(User)
  title = db.StringProperty()
  text = db.TextProperty(required=True)
  created = db.DateTimeProperty(auto_now_add=True)
  updated = db.DateTimeProperty(auto_now_add=True)
  place = db.ReferenceProperty(Location)
  
  def path(self):
    return "/messages/%s" % str(self.key().id())

  @classmethod
  def find_by_location(cls, location):
    try:
      logging.info(location.key())
      query = Message.gql("WHERE place = :1", location)
      messages = query.fetch(1)
      for message in messages:
        logging.info(message.text)
      return messages[0]
    except Exception,e:
      logging.warn(str(e))
      return None

  def date(self):
    return long(time.mktime(self.created.timetuple()))