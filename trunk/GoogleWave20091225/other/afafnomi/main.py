#!/usr/bin/env python
# -*- coding: utf-8 -*-

from waveapi import events
from waveapi import model
from waveapi import robot
from waveapi import document
from waveapi import ops

from google.appengine.api import urlfetch

import xml.etree.ElementTree as etree
import urllib
import json
import logging


#ロボットが追加されたとき
def OnRobotAdded(properties, context):
    root_wavelet = context.GetRootWavelet()
    blip_doc = root_wavelet.CreateBlip().GetDocument()
    blip_doc.AppendElement(document.Image('http://btf-test.appspot.com/assets/icon.png'))
    blip_doc.AppendText('広告王に オレはなる!!')

#Blipが送信されたとき
def OnBlipSubmitted(properties, context):
    blip = context.GetBlipById(properties['blipId'])
    #Blipからコンテンツを取得
    contents = blip.GetDocument().GetText()
    #キーフレーズを取得
    keyphrases = getKeyPhrase(contents)
    if len(keyphrases) > 0:
        #キーフレーズに該当する商品を検索
        items = getItemsByRakutenAPI(keyphrases)

        if len(items) > 0:
            #新しいBlipを生成
            root_wavelet = context.GetRootWavelet()
            new_blip = root_wavelet.CreateBlip()
            #Blipに広告を表示
            createAdBlip(context, new_blip, items, keyphrases)

#キーフレーズを取得
def getKeyPhrase(text):
    #Yahoo API ID
    app_id = 'WauWtp2xg66CltkGFEQ4xWeGOl9N0SecUJCl2Xs9yZSgQreyRAS5pWcATmRMl2tg9WcOVSiGjUXb'
    #キーフレーズAPI
    api_url = 'http://jlp.yahooapis.jp/KeyphraseService/V1/extract?'
    #xml namespace
    xmlns = 'urn:yahoo:jp:jlp:KeyphraseService'

    logging.debug(text)

    #URL Fetch
    enc_text = urllib.quote(text.encode('utf-8'))
#    logging.debug(enc_text)

    url_query = {'appid': app_id, 'sentence': text.encode('utf-8')}
    url = api_url + urllib.urlencode(url_query)
#    logging.debug(url)

    result = urlfetch.fetch(url=url)

#    logging.debug(result.status_code)
    keyphrase = []
    if result.status_code == 200:
#        logging.debug(result.content)
        #APIからXMLを取得してパース
        dom = etree.fromstring(result.content)
        #XMLパスを検索キーフレーズを抽出
        for e in dom.findall('{%s}Result' % xmlns):
            keyphrase.append(e.find('{%s}Keyphrase' % xmlns).text.encode('utf-8'))

    logging.debug(keyphrase)
    return keyphrase


#楽天から商品を検索して取得
def getItemsByRakutenAPI(words):
    # items[(商品名,商品AffiriateURL,サムネイルURL)....]
    items = []
    #楽天dev_id
    dev_id = 'c3dd0b79379197c559cba23be9b9be22'
    #楽天アフィリエイトID
    aff_id = '0b60c903.224a3a10.0b60c904.ee197b06'

    #キーワードをクオート
    keyword = ' '.join(words)
    quoted_keyword = urllib.quote(keyword)

    logging.debug('Before Quote: ' + keyword)
    logging.debug('After Quoete: ' + quoted_keyword)

    #APIを構成
    url='http://api.rakuten.co.jp/rws/2.0/json?developerId=' + dev_id + '&affiliateId=' + aff_id + '&operation=ItemSearch&version=2009-04-15&keyword=' + quoted_keyword + '&sort=%2BitemPrice&orFlag=1&hits=5'

    #JSONを取得
    result = urlfetch.fetch(url=url)

#    logging.debug(result.status_code)
#    logging.debug(result.content)

    if result.status_code == 200:
        j = json.read(result.content)
        body = j['Body']
        ItemSearch = body['ItemSearch']
        Items = ItemSearch['Items']
        Item = Items['Item']
        if len(Item) > 0:
            for x in Item:
                items.append( (x['itemName'],x['affiliateUrl'],x['mediumImageUrl']) )

    logging.debug(items)

    return items

#Amazonから商品を検索して取得
def getItemsByAmazonAPI(words):
    items = []

    return items

#広告Blipを生成
def createAdBlip(context, blip, items, keyphrases):

    if len(items) > 0:
        logging.debug('OK!')
        #メッセージ用のキーワードを抽出
        words = []
        for y in keyphrases:
            words.append(y)
        #メッセージをBlipに表示
        blip.GetDocument().AppendText('　'.join(words) + ' に関連する広告はこちら\n\n')
        #広告を表示
        for x in items:
            #blipに表示
            blip.GetDocument().AppendElement(document.Image(x[2]))
            blip.GetDocument().AppendText('\n')
            #アンカーを構成
            aElem = '<a href=\"%s\">%s</a>' % (x[1], x[0])
            context.builder.DocumentAppendMarkup(blip.GetWaveId(), blip.GetWaveletId(), blip.GetId(), aElem)
            blip.GetDocument().AppendText('\n[' + get_short_url(x[1], None) + ']\n\n')


#goo.gl short URL Hack
def _c(vals):
    l = 0
    for val in vals:
        l += val & 4294967295
    return l
def _d(l):
    if l <=  0:
        l += 4294967296
    m = str(l) 
    o = 0
    n = False
    for char in m[::-1]:
        q = int(char)
        if n:
            q *= 2
            o += q / 10 + q % 10  #
        else:
            o += q
        n = not(n)
    m = o % 10
    o = 0
    if m != 0:
        o = 10 - m
        if len(str(l)) % 2 == 1:
            if o % 2 == 1:
                o += 9
            o /= 2
    return str(o) + str(l)
def _e(uri):
    m = 5381
    for char in uri:
        # m = _c([m << 5, m, struct.unpack("B", char)[0]])
        m = _c([m << 5, m, ord(char)])
    return m
def _f(uri):
    m = 0
    for char in uri:
        # m = _c([struct.unpack("B", char)[0], m << 6, m << 16, -1 * m])
        m = _c([ord(char), m << 6, m << 16, -1 * m])
    return m
def _make_auth_token(uri):
    i = _e(uri)
    i = i >> 2 & 1073741823
    i = i >> 4 & 67108800 | i & 63
    i = i >> 4 & 4193280 | i & 1023
    i = i >> 4 & 245760 | i & 16383
    h = _f(uri)
    k = (i >> 2 & 15) << 4 | h & 15
    k |= (i >> 6 & 15) << 12 | (h >> 8 & 15) << 8
    k |= (i >> 10 & 15) << 20 | (h >> 16 & 15) << 16
    k |= (i >> 14 & 15) << 28 | (h >> 24 & 15) << 24
    j = "7" + _d(k)
    return j
def get_short_url(uri, user):
    if user is None:
        user = 'toolbar@google.com'
    token = _make_auth_token(uri)
    opt = 'user='+user+'&'+urllib.urlencode({'url':uri})+'&auth_token='+token
    # print opt
    ggl_url = 'http://goo.gl/api/url'
    res = urllib.urlopen(ggl_url, opt)
    # print res.read()
    short_url =  json.read(res.read())['short_url']
    return short_url



if __name__ == '__main__':
    logging.getLogger().setLevel(logging.DEBUG)
    myRobot = robot.Robot('afafnomi',
                          image_url='http://btf-test.appspot.com/assets/icon.png',
                          version='4',
                          profile_url='http://btf-test.appspot.com/')
    myRobot.RegisterHandler(events.BLIP_SUBMITTED, OnBlipSubmitted)
    myRobot.RegisterHandler(events.WAVELET_SELF_ADDED, OnRobotAdded)
    myRobot.Run()
