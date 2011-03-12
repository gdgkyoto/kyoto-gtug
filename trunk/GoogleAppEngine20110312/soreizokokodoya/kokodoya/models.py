# -*- coding: utf-8 -*-
# kokodoya.models

from google.appengine.ext import db

# Create your models here.

class MeasuredResult(db.Model):
    """速度測定結果を表すモデル.

    carrier:
        計測に使った回線のキャリア。
    speed:
        回線速度。
    out_of_service:
        圏外かどうか。Trueなら圏外とし、速度は常に0。
    measured_time:
        計測した時間。デフォルト値はアクセスした日時。
    latitude:
        計測した場所の緯度。
    longitude:
        計測した場所の経度。
    address:
        計測した場所の住所。番地等の詳細な場所までは入れないが、目印となる建物は入れる。
    commnet:
        ユーザーからのコメント。
    """
    carrier = db.StringProperty()
    speed = db.FloatProperty()
    out_of_service = db.BooleanProperty()
    measured_time = db.DateTimeProperty(auto_now_add =  True)
    latitude = db.FloatProperty()
    longitude = db.FloatProperty()
    address = db.StringProperty()
    comment = db.StringProperty()

