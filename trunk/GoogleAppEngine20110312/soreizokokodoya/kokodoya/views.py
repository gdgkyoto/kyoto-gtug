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
import simplejson

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
    render_params
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
    measured_result = MeasuredResult(carrier="test", speed=1.1)
    measured_result.put()
    return Response(simplejson.dumps({"result":"OK"}))

