require 'rubygems'
require 'sinatra'
require 'bumble'
require 'time'
include_class 'com.google.api.translate.Language';
include_class 'com.google.api.translate.Translate';


class Trans
	include Bumble
	ds :original, :jpToEn, :enToKo, :koToEn, :enToJp, :create_at
end

get '/' do
	@trans = Trans.all
	erb :index
end

post '/' do
	Translate.http_referrer = "http://code.google.com/p/google-api-translate-java/"
	jpToEn = Translate.translate(params[:trans][:original],Language::JAPANESE, Language::ENGLISH);
	enToKo = Translate.translate(jpToEn,Language::ENGLISH, Language::KOREAN);
	koToEn = Translate.translate(enToKo,Language::KOREAN, Language::ENGLISH);
	enToJp = Translate.translate(koToEn,Language::ENGLISH, Language::JAPANESE);
	params[:trans][:jpToEn]=jpToEn
	params[:trans][:enToKo]=enToKo
	params[:trans][:koToEn]=koToEn
	params[:trans][:enToJp]=enToJp
	params[:trans][:create_at]=Time.now
	Trans.create(params['trans'])
	redirect '/'
end
