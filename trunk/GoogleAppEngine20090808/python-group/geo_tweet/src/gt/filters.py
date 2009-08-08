# -*- coding: utf-8 -*-

from google.appengine.ext import webapp

def jsonescape(body):
    return body.replace("\n","\\n")

register = webapp.template.create_template_register()
register.filter(jsonescape)