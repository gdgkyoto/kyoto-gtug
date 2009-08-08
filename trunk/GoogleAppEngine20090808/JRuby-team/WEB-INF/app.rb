require 'rubygems'
require 'sinatra'
require 'bumble'
require 'time'
require 'urlfetch'
require 'rexml/document'
include_class 'com.google.api.translate.Language';
include_class 'com.google.api.translate.Translate';


class Trans
	include Bumble
	ds :IconURL, :UserName, :Contents, :CreatedAt, :Memo
end

get '/' do
   @contents = 'Hello'
   erb :index
end

post '/' do
   yahoo_api = 'http://jlp.yahooapis.jp/MAService/V1/parse?appid=FGoDDduxg64oBMu1pSndqhogq1KN5ASODXX20.pcreQDnGJAhTU7QFw8p.8-&results=ma,uniq&uniq_filter=9|10&sentence=%E5%BA%AD%E3%81%AB%E3%81%AF%E4%BA%8C%E7%BE%BD%E3%83%8B%E3%83%AF%E3%83%88%E3%83%AA%E3%81%8C%E3%81%84%E3%82%8B%E3%80%82'
   res = URLFetch.get(yahoo_api)
   doc = REXML::Document.new res.content
   ret = []
#   doc.elements.each('/'){|elem|
#     ret.push elem.text
#   }
#   @xml = ret.join(',')

   @xml = REXML::XPath.first(doc, '//surface')

   erb :result
end
