'''
Created on 2009/08/01

@author: takuji
'''

#from google.appengine.api import users
from google.appengine.ext import webapp
from google.appengine.ext.webapp import template
from ..models.user import User

class LoginRequiredException(Exception):
  def __init__(self, redirect_url):
    self.redirect_url = redirect_url
    
class Redirect(Exception):
  def __init__(self, redirect_url):
    self.redirect_url = redirect_url


class BaseController(webapp.RequestHandler):
  def get(self, *args):
    self.decide_response_format()
    try:
      if len(args) == 0:
        return self.index()
      elif len(args) == 1:
        if args[0] == 'new':
            return self.new()
        else:
            return self.show(args[0])
      elif len(args) == 2:
        if args[1] == 'edit':
            return self.edit(args[0])
        elif args[1] == 'delete':
            return self.delete(args[0])
      else:
        self.error(404)
    except LoginRequiredException,e:
      self.redirect(User.login_url(e.redirect_url))
    except Redirect,e:
      self.redirect(e.redirect_url)
  
  def post(self, *args):
    self.decide_response_format()
    if len(args) == 0:
      return self.create()
    elif len(args) == 1:
      return self.update(args[0])
    elif len(args) == 2:
      if args[1] == 'delete':
        return self.delete(args[0])
    else:
      self.error(404)

  def assert_logged_in(self):
    user = User.get_current_user()
    if user == None:
      raise LoginRequiredException('/')

  def decide_response_format(self):
    path = self.request.path
    if path[-5:] == '.json':
      self.response_format = 'json'
      self.response.headers['Content-Type'] = 'application/json'
    else:
      self.response_format = 'html'
