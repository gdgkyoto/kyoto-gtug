from google.appengine.ext import db
from google.appengine.api import users
from twitter_account import TwitterAccount

class User(db.Model):
  google_account = db.UserProperty(required=True)
  twitter_account = db.ReferenceProperty(TwitterAccount)
  
  def path(self):
    return "/users/%s" % str(self.key().id())

  @classmethod
  def get_current_user(cls):
    user = users.get_current_user()
    if user:
      query = User.gql("WHERE google_account = :1", user)
      result = query.fetch(1)
      if len(result) > 0:
        return result[0]
      new_user = User(google_account=user)
      new_user.put()
      return new_user 
    return None
  
  @classmethod
  def login_url(cls, dest_url):
    return users.create_login_url(dest_url)

  @classmethod
  def logout_url(cls, dest_url):
    return users.create_logout_url(dest_url)