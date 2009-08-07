# coding:utf-8

class RevGeo(object):
  
  URL = "http://refits.cgk.affrc.go.jp/tsrv/jp/rgeocode.php?v=1&lat=%f&lon=%f"
  
  @classmethod
  def search(cls, lat, lon):
    url = URL % [lat, lon]
    return ['京都府', '京都市下京区', '中堂寺南町']