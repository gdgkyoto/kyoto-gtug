#!/usr/bin/env python
#
# Copyright 2007 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


from google.appengine.ext import webapp
from google.appengine.ext.webapp import util
from google.appengine.api import urlfetch
import xml.etree.ElementTree as etree
import urllib2

#Yahoo API ID
app_id = 'WauWtp2xg66CltkGFEQ4xWeGOl9N0SecUJCl2Xs9yZSgQreyRAS5pWcATmRMl2tg9WcOVSiGjUXb'
#API URL
api_url = 'http://jlp.yahooapis.jp/MAService/V1/parse?appid='
#API Params
api_params = '&results=ma,uniq&uniq_filter=9&sentence='
#namespase
namespase = 'urn:yahoo:jp:jlp'

class MainHandler(webapp.RequestHandler):

  def get(self):
    source = '私はピンクの鞄がほしいわ'
    sen = urllib2.unquote(source)
    #APIを構成
    url = api_url + app_id + api_params + sen
    #URL Fetch
#    xml = urlfetch.fetch(url)
#    dom = etree.fromstring(xml.content)
    dom = etree.parse(urllib2.urlopen(url))
    #XMLパスを検索
    uniq_result = dom.find('/{%s}uniq_result' % namespase)
    word_list   = uniq_result.find('{%s}word_list' % namespase)
    #テキストを抽出
    meishi = []
    for e in word_list.findall('{%s}word' % namespase):
      meishi.append(e.find('{%s}surface' % namespase).text.encode('utf-8'))

    strout = ''
    for x in meishi:
      strout += x + '<br/>'

    self.response.out.write(strout)


def main():
  application = webapp.WSGIApplication([('/', MainHandler)],
                                       debug=True)
  util.run_wsgi_app(application)

if __name__ == '__main__':
  main()
