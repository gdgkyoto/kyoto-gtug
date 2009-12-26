#-*- coding: utf-8 -*-

import json
import urllib2

#dev_id
dev_id = 'c3dd0b79379197c559cba23be9b9be22'
#アフィリエイトID
aff_id = '0b60c903.224a3a10.0b60c904.ee197b06'
#チョコレート
choco_str = '%E3%83%81%E3%83%A7%E3%82%B3%E3%83%AC%E3%83%BC%E3%83%88'
#json data
api_uri='http://api.rakuten.co.jp/rws/2.0/json?developerId=' + dev_id + '&affiliateId=' + aff_id + '&operation=ItemSearch&version=2009-04-15&keyword=' + choco_str + '&sort=%2BitemPrice'

f = urllib2.urlopen(api_uri)

j = json.read(f.read())

body = j['Body']
ItemSearch = body['ItemSearch']
Items = ItemSearch['Items']
Item = Items['Item']

sItemName =  Item[0]['itemName']
sUrl = Item[0]['affiliateUrl']
sImage = Item[0]['smallImageUrl']

print "%s\n%s\n%s" % (sItemName,sUrl,sImage)
