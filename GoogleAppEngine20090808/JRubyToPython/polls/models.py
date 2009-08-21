# -*- coding: utf-8 -*-
from django.utils.translation import ugettext_lazy as _
from google.appengine.ext import db
import datetime
class Poll(db.Model):
    question = db.StringProperty(verbose_name='question', required=True)
    pub_date = db.DateTimeProperty(verbose_name='date published',
                                    required=True)
    def __unicode__(self):
        return self.question

    def was_published_today(self):
        return self.pub_date.date() == datetime.date.today()
    was_published_today.short_description = 'Published today?'

class Choice(db.Model):
        poll = db.ReferenceProperty(Poll)
        choice = db.StringProperty(verbose_name='choice', required=True)
        votes = db.IntegerProperty(verbose_name='votes', required=True,
                                    default=0)
        
        def __unicode__(self):
            return self.choice




