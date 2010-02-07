require 'sinatra'
require 'appengine-apis/users'
require 'tiny_ds'
require 'util'
	##モデル追加
require 'model'

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
	ido1   = param[:ido1]
	keido1 = param[:keido1]
	ido2   = param[:ido2]
	keido2 = param[:keido2]

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


require 'dev_util'
