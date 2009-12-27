#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# Service class for Amazon web service api
#
# Copyright (c) 2009 Shinya Ohyanagi, All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions
# are met:
#
#   * Redistributions of source code must retain the above copyright
#     notice, this list of conditions and the following disclaimer.
#
#   * Redistributions in binary form must reproduce the above copyright
#     notice, this list of conditions and the following disclaimer in
#     the documentation and/or other materials provided with the
#     distribution.
#
#   * Neither the name of Shinya Ohyanagi nor the names of his
#     contributors may be used to endorse or promote products derived
#     from this software without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
# "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
# LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
# FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
# COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
# INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
# BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
# LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
# CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
# LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
# ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
# POSSIBILITY OF SUCH DAMAGE.
#
import sys, os
sys.path.append(os.pardir)
from lib.pyzon.Pyzon import Pyzon
import xml.etree.ElementTree as etree

class Amazon():
    affiliate_url = 'http://www.amazon.co.jp/gp/product/%s/ie=UTF6&tag=%s/linkCode=as2&camp=247&creative=1211'
    xmlns = r'http://webservices.amazon.com/AWSECommerceService/2009-03-31'

    image_size = 'SmallImage'

    userinfo = {
        'api_key': 'API_KEY',
        'secret_key': 'SECRET_KEY',
        'affiliate': 'ASSOSIATE_TAG'
    }

    def __init__(self, info):
        self.userinfo = info
        self.amazon = Pyzon(info['api_key'], info['secret_key'], info['affiliate'])
        self.image_size('SmallImage');

    def image_size(self, size):
        self.imagesize = size
        return self

    def search(self, category, keywords):
        return self.amazon.ItemSearch(search_index=category, Keywords=keywords, ResponseGroup='Large,ItemAttributes,Images')

    def parse(self, xml):
        dom = etree.fromstring(xml)
        items = dom.findall('.//{%s}Item' % self.xmlns)
        response = []
        for e in items:
            asin = e.find('{%s}ASIN' % self.xmlns).text
            url = e.find('{%s}DetailPageURL' % self.xmlns).text
            try:
                img = e.find('{%s}SmallImage/{%s}URL' % (self.xmlns, self.xmlns)).text
            except:
                img = ''
            title = e.find('{%s}ItemAttributes/{%s}Title' % (self.xmlns, self.xmlns))

            response.append((
                title.text.encode('utf-8'),
                self.affiliate_url % (asin, self.userinfo['affiliate']),
                img
            ))

        return response
