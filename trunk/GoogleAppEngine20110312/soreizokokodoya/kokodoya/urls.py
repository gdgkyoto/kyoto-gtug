# -*- coding: utf-8 -*-
# kokodoya.urls
# 

# Following few lines is an example urlmapping with an older interface.
"""
from werkzeug.routing import EndpointPrefix, Rule

def make_rules():
  return [
    EndpointPrefix('kokodoya/', [
      Rule('/', endpoint='index'),
    ]),
  ]

all_views = {
  'kokodoya/index': 'kokodoya.views.index',
}
"""

from kay.routing import (
  ViewGroup, Rule
)

view_groups = [
  ViewGroup(
    Rule('/', endpoint='index', view='kokodoya.views.index'),
    Rule('/result', endpoint='result', view='kokodoya.views.result'),
    Rule('/add', endpoint='add', view='kokodoya.views.add'),
    Rule('/map', endpoint='map', view='kokodoya.views.map'),
    Rule('/measure', endpoint='measure', view='kokodoya.views.measure'),
  )
]

