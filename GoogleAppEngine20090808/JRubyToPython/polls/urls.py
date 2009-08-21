# -*- coding: utf-8 -*-
from django.conf.urls.defaults import *
urlpatterns = patterns(
        'polls.views',
        url(r'^$', 'index', name='polls_index'),
        )
