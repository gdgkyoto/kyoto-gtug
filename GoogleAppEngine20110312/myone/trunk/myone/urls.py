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

view_groups = [
  ViewGroup(
    Rule('/', endpoint='index', view='myone.views.index'),
    Rule('/a/', endpoint='a', view='myone.views.a'),
 )
]

