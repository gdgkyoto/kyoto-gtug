require 'sinatra'
require 'appengine-apis/users'
require 'tiny_ds'
require 'util'
	##モデル追加
require 'model'
require 'json'

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
	erb	:index		# ./views/index.erb をレンダリングします。(layout.erbも含めて)
end


get '/mapinfo/group/' do
	ido1   = params[:ido1]
	keido1 = params[:keido1]
	ido2   = params[:ido2]
	keido2 = params[:keido2]

	ido1_num = ido1.to_i
	ido2_num = ido2.to_i
	keido1_num = keido1.to_i
	keido2_num = keido2.to_i

	

end


get '/group/' do
	@groups = Group.query.all
	erb :group_index			# ./views/index.erb をレンダリングします。(layout.erbも含めて)
end

get '/group/create/' do
	erb :group_create
end

# Time.now.strftime("%Y%m%d%H%M%S")+"-"+java.util.UUID.randomUUID().to_s

class Group < TinyDS::Base
  property :groupname,   		:string
  property :url, 	  		:string
  property :list,       :string	
  property :tag,       :string	
end

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


post '/group/position/' do
#  pos1 = [params[:ido1].to_i, params[:keido1].to_i]
#  pos2 = [params[:ido2].to_i, params[:keido2].to_i]
	pos1 = [35.95133,136.018982]
	pos2 = [36.248703,136.724854]

  arr = []
	@group_names = GroupPostion.get_groupname(pos1,pos2)
  @group_names.each do |name|
    group = Group.query.filter(:groupname, "==", name).all[0]
    arr << Hash[:id => group.key, :name => group.groupname,
                :lat => group.location.split(',')[0], :lng => group.location.split(',')[1]]
  end
  
  asso = []
  @group_names.each_index do |i|
    @group_names.each_index do |j|
      next if j <= i # G1とG2の計算が終わったらG2とG1を計算しないための制御
      asso << Association.query.filter(:group1group2, "==", [@group_names[i], @group_names[j]].sort.join(':')).all[0]
    end
  end

#  ary2 = ary.map{|item| item.each{|k,v| k+':'+v.inspect}}

  @result = [arr2, asso]
#  { id: "id3", name: "LONG NAME", lat: 35.350000, lng: 137.224976 }
  content_type 'text/javascript', :charset => 'utf-8'	

#  @result.to_json
end


require 'dev_util'
