# -*- coding: utf-8 -*-
"""
kokodoya.views
"""

"""
import logging

from google.appengine.api import users
from google.appengine.api import memcache
from werkzeug import (
  unescape, redirect, Response,
)
from werkzeug.exceptions import (
  NotFound, MethodNotAllowed, BadRequest
)

from kay.utils import (
  render_to_response, reverse,
  get_by_key_name_or_404, get_by_id_or_404,
  to_utc, to_local_timezone, url_for, raise_on_dev
)
from kay.i18n import gettext as _
from kay.auth.decorators import login_required

"""

from kay.utils import render_to_response, url_for
from kokodoya.models import MeasuredResult
from kokodoya.forms import MeasuredResultForm
from werkzeug import Response, redirect
import simplejson as json
import datetime

# Create your views here.

def index(request):
    """indexページ.

    Args:
        request:
            HTTPリクエストオブジェクト

    Return:
        レンダリングされたテンプレートデータ
    """
    return render_to_response('kokodoya/index.html')

def result(request):
    """速度の測定値結果を表示するページ

    Args:
        request:
            リクエストオブジェクト

    Return:
        レンダリングされたテンプレートデータ

    まだ仕様未決の為適当実装
    """

    form = MeasuredResultForm()
    if request.method == "POST":
        if form.validate(request.form):
            # 仕様未決の為実装は適当
            result = form.save()
            return redirect(url_for('kokodoya/index'))

    render_params = {}
    render_params['form'] = form.as_widget()

    current = datetime.datetime.now()
    render_params['measured_time'] = current.strftime("%Y/%m/%d %H:%M")
    # todo : ちゃんとした奴に直す
    import random
    render_params['speed'] = random.randrange(5, 50) / 10.0
    render_params['address'] = u"福井県鯖江市新横江"

    return render_to_response('kokodoya/result.html', render_params)

def measure(request):
    """速度測定中に表示するページ

    Args:
        request:
            リクエストオブジェクト

    Return:
        レンダリングされたテンプレートデータ

    """
    return render_to_response('kokodoya/measure.html')


def progressing(request):
    """速度測定中に表示するページ

    Args:
        request:
            リクエストオブジェクト

    Return:
        レンダリングされたテンプレートデータ

    """
    return render_to_response('kokodoya/progressing.html')


def map(request):
    """電波状態のマップ表示するページ

    Args:
        request:
            リクエストオブジェクト

    Return:
        レンダリングされたテンプレートデータ
    """
    return render_to_response('kokodoya/map.html')

def add(request):
    """測定結果を追加するページ(Ajax?)

    Args:
        request:
            リクエストオブジェクト

    Return:
        追加処理実行結果(JSONフォーマット)
    """
    post_data = request.args
    measured_result = MeasuredResult(post_data)
    measured_result.put()
    return Response(json.dumps({"result":"OK"}))


def get_data(request):
    """測定結果のデータを返す

    Args:
        request:
            リクエストオブジェクト

    Return;
        測定結果データ(JSON)

    """
    response_msg = ""
    url_params = request.args

    north_bound = url_params.get('n')
    south_bound = url_params.get('s')
    east_bound  = url_params.get('e')
    west_bound  = url_params.get('w')

    query = MeasuredResult.all()
    query.filter("latitude <= ",  north_bound)
    query.filter("latitude >= ",  south_bound)
    query.filter("longitude <= ", west_bound)
    query.filter("longitude >= ", east_bound)
    resuts = query.fetch(20)

    json = json.dumps([dbutils.to_dict(result) for result in results])
    callback_func_name = url_params.get("callback")
    jsonp = "%s(%s)" % (callback_func_name, json)
    return Response(jsonp, content_type="application/json")
