# -*- coding: utf-8 -*-
"""
myone.views
"""

"""
import logging

from google.appengine.api import users
from google.appengine.api import memcache
from werkzeug import (
  unescape, redirect, Response,
)
from werkzeug.exceptions import (
  NotFound, MethodNotAllowed, BadRequest
)

from kay.utils import (
  render_to_response, reverse,
  get_by_key_name_or_404, get_by_id_or_404,
  to_utc, to_local_timezone, url_for, raise_on_dev
)
from kay.i18n import gettext as _
from kay.auth.decorators import login_required

"""

from kay.utils import render_to_response
from myone.forms import RecordForm
from myone.models import Competition

# Create your views here.

def index(request):
  query = Competition.all()
  competitions = query.fetch(10)
  return render_to_response('myone/index.html', {'message': 'Hello'})


def test(request):
  form = RecordForm()
  return render_to_response('myone/test.html'	, {'form': form.as_widget()
												, 'scrolls': [u'牛乳早飲み 挑戦者挑む - ハッカソン - ', u'タイヤ早交換 挑戦者挑む - ハッカソン - ', u'カップ麺早食い 挑戦者挑む - ハッカソン - ']
												, 'challenges': [u'パン早食い123', u'パン早食い456', u'パン早食い789']
												, 'top_runkings': [{ 'movie_id': 'XzUILaTGA5k' , 'title': u'パン早食い' , 'rank': 5 , 'update_time': u'1週間前' , 'play_count': 200000}
																	,{ 'movie_id': 'XzUILaTGA5k' , 'title': u'早食いパン222' , 'rank': 3 , 'update_time': u'3日前', 'play_count': 100000}]
												, 'respect_runkings': [{ 'movie_id': 'XzUILaTGA5k' , 'title': u'パン早食い11' , 'rank': 5 , 'update_time': u'1日前', 'play_count': 100000}
																	,{ 'movie_id': 'XzUILaTGA5k' , 'title': u'早食いパン222' , 'rank': 3, 'update_time': u'3日前', 'play_count': 400000}
																	,{ 'movie_id': 'XzUILaTGA5k' , 'title': u'早食いパン222' , 'rank': 1, 'update_time': u'5日前', 'play_count': 600000}]})

def a(request):
  return render_to_response('myone/a.html', {'scrolls': u'牛乳早飲み 挑戦者挑む - ハッカソン - '})
  
def challenge_list(request):
  return render_to_response('myone/challenge_list.html', {'challeng_lists': [{ 'movie_id': 'XzUILaTGA5k' , 'title': u'パン早食い' , 'rank': 5 , 'update_time': u'1週間前' , 'play_count': 200000}
																	,{ 'movie_id': 'XzUILaTGA5k' , 'title': u'早食いパン222' , 'rank': 3 , 'update_time': u'3日前' , 'play_count': 100000}]})
