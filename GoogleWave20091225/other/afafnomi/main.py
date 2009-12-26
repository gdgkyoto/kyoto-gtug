#!/usr/bin/env python
# -*- coding: utf-8 -*-

from waveapi import events
from waveapi import model
from waveapi import robot
from waveapi import document

from google.appengine.api import urlfetch

import xml.etree.ElementTree as etree
import urllib
import logging


#ロボットが追加されたとき
def OnRobotAdded(properties, context):
    root_wavelet = context.GetRootWavelet()
    root_wavelet.CreateBlip().GetDocument().SetText('広告王に オレはなる!!')

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
            new_blip_doc = root_wavelet.CreateBlip().GetDocument()
            #Blipに広告を表示
            createAdBlip(new_blip_doc, items)

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
    logging.debug(enc_text)

    url_query = {'appid': app_id, 'sentence': text.encode('utf-8')}
    url = api_url + urllib.urlencode(url_query)
    logging.debug(url)

    result = urlfetch.fetch(url=url)
    
    logging.debug(result.status_code)
    keyphrase = []
    if result.status_code == 200:
        logging.debug(result.content)
        #APIからXMLを取得してパース
        dom = etree.fromstring(result.content)
        #XMLパスを検索キーフレーズを抽出
        for e in dom.findall('{%s}Result' % xmlns):
            keyphrase.append(e.find('{%s}Keyphrase' % xmlns).text.encode('utf-8'))

    return keyphrase


#楽天から商品を検索して取得
def getItemsByRakutenAPI(words):
    items = []

    # items[(商品名,商品AffiriateURL,サムネイルURL)....]
    return items


#広告Blipを生成
def createAdBlip(blip, items):
    #キーフレーズをblipに表示
#    root_wavelet.CreateBlip().GetDocument().SetText(keyphrases)
    #実験 - 画像をblipに表示
    cUrl1 = 'http://images.google.co.jp/intl/ja_ALL/images/logos/images_logo_lg.gif'  
    cImg1 = document.Image(cUrl1)
    cUrl2 = 'https://wave.google.com/wave/static/images/logo_preview.png'
    cImg2 = document.Image(url=cUrl2,caption='Google Wave')
    cUrl3 = 'http://calendar.google.com/googlecalendar/images/calendar_logo_sm_ja.gif'
    cImg3 = document.Image(url=cUrl3,caption='Google カレンダー')
    logging.debug(cImg1)
    logging.debug(cImg2)

    blip.AppendElement(cImg1)
    blip.AppendElement(cImg2)
    blip.AppendText('Google Waveのロゴ')
    blip.AppendElement(cImg3)
    blip.AppendText('Google カレンダーのロゴ')



if __name__ == '__main__':
    logging.getLogger().setLevel(logging.DEBUG)
    myRobot = robot.Robot('BTF_Smiles',
                          image_url='http://btf-test.appspot.com/assets/icon.png',
                          version='3',
                          profile_url='http://btf-test.appspot.com/')
    myRobot.RegisterHandler(events.BLIP_SUBMITTED, OnBlipSubmitted)
    myRobot.RegisterHandler(events.WAVELET_SELF_ADDED, OnRobotAdded)
    myRobot.Run()
