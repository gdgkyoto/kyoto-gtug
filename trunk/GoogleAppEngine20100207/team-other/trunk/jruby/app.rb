##外部ライブラリ
require 'sinatra'
require 'appengine-apis/users'	#認証用
require 'tiny_ds'								#モデル用
require 'lib/json'							#外部ライブラリ(JSON)
require 'WEB-INF/lib/MyTwitter.jar'			#TwitterAPI

##内部の今回作成ライブラリ
require 'util'									#ユーティリティ
require 'model'									#つながったー独自モデル

include MySinatraUtil
include Rack::Utils
enable :sessions 

helpers do
  include Rack::Utils
  alias_method :h, :escape_html
end

def notify_message( message )
	session[:notify] ||= []
	session[:notify] << message
end

before do
	##とりあえず、すべての要求で text/html utf-8を前提としました。
	##もし、css , xml などなどを返却するときには、再定義お願いします。
	content_type 'text/html', :charset => 'utf-8'	
end

def is_development?
	@env['java.servlet_context'].getServerInfo() =~ /Google App Engine Development/ 
end

get '/' do
	## index.erb は レイアウトなしにして、レイアウトをレンダリング
	## しないようにしました。
	## index.erb ですべての結果が生成できるように、お願いします。
	if is_development?
		#開発用キー
		@google_map_api_key = 'ABQIAAAAgapTRXAOQ1ujOCRTo31osBS9gEXLlk-bbmjsmtM6rjseNKPRwRQbXymit5BVR2Nr5wu33PIQRBz7EA'
	else
		#本番用キー
		@google_map_api_key = 'ABQIAAAAiiJ8no53FLslswsn0wxdVhQ6nRY5Sd7N2P-dpdFFnQhAKbdkbRTSLmNNZFL_0LDokISeuETFi1-rOg'
	end

	erb	:index,:layout => false		# ./views/index.erb のみです。
end

get '/status/accountcheck/json/' do
  user_name = AppEngine::Users.current_user
  h = nil
  if user_name 
    url = AppEngine::Users.create_logout_url('/')
    h = {:status=>'login',:url=>url}
  else
    url = AppEngine::Users.create_login_url('/')
    h = {:status=>'not_login',:url=>url}
  end
  content_type 'text/javascript', :charset => 'utf-8'
  WebAPI::JsonBuilder.new.build(h)
end

##どうするか悩み中
get '/status/notifymessage/json/' do
	h= if session[:notify] && session[:notify].size > 0 
		{:notify=>true,:message=>session[:notify].map(){|s| escape_html s}.join('<br>'),:timeout=>3000,:speed=>"normal"}
	else
		{:notify=>false,:message=>'',:timeout=>3000,:speed=>"normal"}
	end
	session.clear
  content_type 'text/javascript', :charset => 'utf-8'
  WebAPI::JsonBuilder.new.build(h)
end

get '/group/' do
	@groups = Group.query.all
	erb :group_index			# ./views/index.erb をレンダリングします。(layout.erbも含めて)
end

get '/group/create/' do
	erb :group_create
end

# Time.now.strftime("%Y%m%d%H%M%S")+"-"+java.util.UUID.randomUUID().to_s

post '/group/create/' do
	is_ok = request_is_ok?({:groupname=>"グループ名なし",:list=>"LISTなし"})
	if is_ok
		Group.create( params.pairs_at(:groupname,:url,:list,:tag) )
	else
		##エラー
	end
#	erb %{ #{h @r.inspect} }
	redirect '/group/'
end

get '/group/edit/:key' do
	@item = Group.get(params[:key])
	if @item
		erb :group_edit
	else
		redirect '/group/'
	end
end

post '/group/edit/:key' do
	@item = Group.get(params[:key])
	if @item
		is_ok = request_is_ok?({:groupname=>"グループ名なし",:list=>"LISTなし"})
		if is_ok
			@item.attributes = params.pairs_at(:groupname,:url,:list,:tag)
			@item.save
		else
			## エラー
		end
	else
		## エラー
	end
	redirect '/group/'
end


get '/group/view/:key' do
	@item = Group.get(params[:key])
	if @item
		erb :group_view
	else
		redirect '/group/'
	end
end

get '/group/delete/:key' do
	@item = Group.get(params[:key])
	if @item
		Group.destroy(params[:key])
	else
	end
	redirect '/group/'
end

get '/group/information/json/:key' do
	group = Group.get(params[:key])
	hash = nil
	if group
		hash = {:name=>group.groupname,
							:site_url=>group.site_url,
							:logo_url=>group.logo_url,
							:tags=>group.tags,
							:description=>group.description}
	else
		hash = {:name=>'',
							:site_url=>'',
							:logo_url=>'',
							:tags=>'',
							:description=>''}
	end
  content_type 'text/javascript', :charset => 'utf-8'	
	WebAPI::JsonBuilder.new.build(hash)
end

get '/group/position/json/' do
	pos1 = nil
	pos2 = nil
	if params[:ido1] && params[:ido2] && params[:keido1] && params[:keido2] 
		##TODO 数字の妥当性チェック
	  pos1 = [params[:ido1].to_f, params[:keido1].to_f]
	  pos2 = [params[:ido2].to_f, params[:keido2].to_f]
	else
		##TODO 削除		とりあえず、福井周辺をいれてます
		pos1 = [35.95133,136.018982]
		pos2 = [36.248703,136.724854]
		##TODO エラー処理
	end

  ret_group_list = []
	cache_of_group = {}
	group_names = GroupPostion.get_groupname(pos1,pos2)
  group_names.each do |name|
    group = Group.query.filter(:groupname, "==", name).all[0]
		cache_of_group[group.groupname] = group.key.to_s
    ret_group_list << Hash['id' => group.key.to_s, :name => group.groupname,
                :lat => group.location.split(',')[0], :lng => group.location.split(',')[1]]
  end
  
  asso = []
  group_names.each_index do |i|
    group_names.each_index do |j|
      next if j <= i # G1とG2の計算が終わったらG2とG1を計算しないための制御
      ass = Association.query.filter(:group1group2, "==", [group_names[i], group_names[j]].sort.join(':')).all[0]
      asso << {:group1_id=>cache_of_group[group_names[i]],:group2_id=>cache_of_group[group_names[j]],:tunagari=>ass.value} if ass
    end
  end

#  ary2 = ary.map{|item| item.each{|k,v| k+':'+v.inspect}}

  @result = [ret_group_list, asso]
#  { id: "id3", name: "LONG NAME", lat: 35.350000, lng: 137.224976 }
  content_type 'text/javascript', :charset => 'utf-8'	

#  @result.to_json
	WebAPI::JsonBuilder.new.build(@result)
end

#######################
##  管理者用ツール画面

require 'digest/sha1'
def authed_user?( email )
	auted_users_hash = {'f39a542f465fead3a33b4c24db35639afbe56fde'=>'k1',
											'c589bd64c2088ea3e3797f7332360c51dd0c65b6'=>'u1',
											'bedeb49f34e2934fba21fe31412b30afa0347d9b'=>'ka',
											'bffaf4e7e3af16b3ec2825c629db1482641bbe61'=>'ts',
											'cdc318c81a6c2ec47863625fd48f12d2f0ab6c64'=>'gi'
											}
	key1 = 'Aoihdoieur8ihjdoia&Atgeliu3ge'
	key2 = '%Ajew78iUWO(RY;ojfwpeo9)SRp3o'
	h1 = Digest::SHA1.hexdigest( key1 + email)
	hash = Digest::SHA1.hexdigest( key2 + h1)
	auted_users_hash[hash] != nil
end

post '/admintool/save/' do
  user_name = AppEngine::Users.current_user
  if user_name && authed_user?( user_name.email )
		setting = AppSetting.query.all[0]
		unless setting
			AppSetting.create
			setting = AppSetting.query.all[0]
		end

		AppSetting.property_definitions.each do |k,v|
			setting.set_property(k,params[k.to_sym])
		end
		setting.save
	end
	redirect '/admintool/'
end
get '/admintool/' do
  user_name = AppEngine::Users.current_user
  if user_name 
		##ログイン済み
    @logout_url = AppEngine::Users.create_logout_url('/admintool/')
		if authed_user?( user_name.email )
			@vals = {}
			setting = AppSetting.query.all[0]
			unless setting
				AppSetting.create
				setting = AppSetting.query.all[0]
			end
			AppSetting.property_definitions.each do |k,v|
				@vals[k] = setting.get_property(k)
			end
			erb :admintool_logined_authed
		else
	    @logout_url = AppEngine::Users.create_login_url('/admintool/')
			erb :admintool_logined_not_authed
		end
		#erbで結果が返るのでここでは処理をかかないこと
  else
		##ログインではない
    @url = AppEngine::Users.create_login_url('/admintool/')
		erb :admintool_nologin
  end
	#erbで結果が返るのでここでは処理をかかないこと
end
