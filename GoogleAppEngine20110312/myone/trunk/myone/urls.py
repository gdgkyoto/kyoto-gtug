# -*- coding: utf-8 -*-
# myone.urls
# 

# Following few lines is an example urlmapping with an older interface.
"""
from werkzeug.routing import EndpointPrefix, Rule

def make_rules():
  return [
    EndpointPrefix('myone/', [
      Rule('/', endpoint='index'),
    ]),
  ]

all_views = {
  'myone/index': 'myone.views.index',
}
"""

from kay.routing import (
  ViewGroup, Rule
)

all_views = {
  'myone/test': 'myone.views.test',
}
view_groups = [
  ViewGroup(
    Rule('/', endpoint='index', view='myone.views.index'),
    Rule('/challenge_list/', endpoint='challenge_list', view='myone.views.challenge_list'),
    Rule('/test', endpoint='test', view='myone.views.test'),
 )
]

