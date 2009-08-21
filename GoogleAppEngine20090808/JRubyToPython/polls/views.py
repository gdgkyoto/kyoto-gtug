# -*- coding: utf-8 -*-
from django.http import HttpResponse, HttpResponseRedirect
from django.utils.translation import ugettext as _
from ragendja.template import render_to_response
from polls.models import Poll
from django.template import loader
from django.template import Context
from django.utils import simplejson as json
import re
import urllib
import yaml
from yahoo import YahooJLP

class UrlOpener(urllib.FancyURLopener):
	version = "py-gtranslate/1.0"

base_uri = "http://ajax.googleapis.com/ajax/services/language/translate"
default_params = {'v': '1.0'}

def translate(src, to, phrase):
	args = default_params.copy()
	args.update({
		'langpair': '%s%%7C%s' % (src, to),
		'q': urllib.quote_plus(phrase),
	})
	argstring = '%s' % ('&'.join(['%s=%s' % (k,v) for (k,v) in args.iteritems()]))
	resp = json.load(UrlOpener().open('%s?%s' % (base_uri, argstring)))
	try:
		return resp['responseData']['translatedText']
	except:
		# should probably warn about failed translation
		return phrase

def index(request):
    jp = u'結局のところ、大企業に浸透するまでには時間がかかりそうだから、中小企業か個人をターゲットに数多くのユーザを獲得する以外なさそうかな、と私は感じています。コンシューマをターゲットに小売的な商売を行って、請負でいくらというより、使ってもらってナンボといった感じの方が合うような気がしています。'
    jpToEn = translate("ja","en",jp.encode('utf-8'));
    enToKo = translate("en","ko",jpToEn.encode('utf-8'));
    koToEn = translate("ko","en",enToKo.encode('utf-8'));
    enToJp = translate("en","ja",koToEn.encode('utf-8'));
    jlp = YahooJLP()
    orgWords = jlp.parse(jp)
    words = jlp.parse(enToJp)
    t = loader.get_template('polls/lang.html')
    c = Context({
            'jp': jp,
            'jpToEn': jpToEn,
            'enToKo': enToKo,
            'koToEn': koToEn,
            'enToJp': enToJp,
            'orgWords': orgWords,
            'words': words,
    })
    return HttpResponse(t.render(c))
