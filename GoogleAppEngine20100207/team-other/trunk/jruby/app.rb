require 'sinatra'
require 'appengine-apis/users'
require 'tiny_ds'
require 'util'
	##モデル追加
require 'model'
#require 'json'
require 'lib/json'

include MySinatraUtil

helpers do
  include Rack::Utils
  alias_method :h, :escape_html
end

before do
	##とりあえず、すべての要求で text/html utf-8を前提としました。
	##もし、css , xml などなどを返却するときには、再定義お願いします。
	content_type 'text/html', :charset => 'utf-8'	
end

get '/' do
	## TODO index.erb は レイアウトなしのほうがいいかもしれない。
	erb	:index		# ./views/index.erb をレンダリングします。(layout.erbも含めて)
end


get '/group/' do
	@groups = Group.query.all
	erb :group_index			# ./views/index.erb をレンダリングします。(layout.erbも含めて)
end

get '/group/create/' do
	erb :group_create
end

# Time.now.strftime("%Y%m%d%H%M%S")+"-"+java.util.UUID.randomUUID().to_s

#class Group < TinyDS::Base
#  property :groupname,   		:string
#  property :url, 	  		:string
#  property :list,       :string	
#  property :tag,       :string	
#end

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
	WebAPI::JsonBuilder.new.build(hash)
end

get '/group/position/' do
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


require 'dev_util'
