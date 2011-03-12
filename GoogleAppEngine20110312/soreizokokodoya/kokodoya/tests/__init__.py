#-*- coding: utf-8 -*-

from google.appengine.ext import db

from kay.ext.testutils.gae_test_base import GAETestBase

from kokodoya.models import MeasuredResult


class ModelTest(GAETestBase):

    # テスト終了後使用したスタブを削除
    CLEANUP_USED_KIND = True

    def tearDown(self):
        """テスト実行の前処理"""
        db.delete(MeasuredResult.all())

    def test_model(self):
        mr = MeasuredResult(speed=100.0)
        mr.put()
        results = MeasuredResult.all().fetch(100)
        self.assertEquals(len(results), 1)
        # 浮動小数点の比較はassertAlmostEqualsを使う
        self.assertAlmostEquals(results[0].speed, 100.0)

    def test_filter(self):
        add_data1 = {
            "carrier":"docomo",
            "speed":100.0,
            "out_of_service":False,
            "latitude":100.0,
            "longitude":100.0,
            "address":u"福井県鯖江市",
            "comment":u"テスト1データ"
        }
        add_data2 = {
            "carrier":"docomo",
            "speed":100.0,
            "out_of_service":False,
            "latitude":200.0,
            "longitude":200.0,
            "address":u"福井県鯖江市",
            "comment":u"テスト2データ"
        }
        mr = MeasuredResult(**add_data1)
        mr.put()
        mr = MeasuredResult(**add_data2)
        mr.put()
        results = MeasuredResult.all().filter("latitude >= ", 50.0).filter("latitude <= ", 150.0).fetch(100)
        self.assertEquals(len(results), 1)
        self.assertEquals(results[0].carrier, "docomo")
        self.assertEquals(results[0].comment, u"テスト1データ")

