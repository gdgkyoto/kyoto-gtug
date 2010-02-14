require 'pp'
#require 'json'


get '/devutil/' do
	erb	:dev_util
end

get '/devutil/create_initialdata' do
	Group.destroy_all		#データ全消し
	#GroupUserList.destroy_all		#データ全消し
	GroupPostion.destroy_all
	Association.destroy_all

	associations = [
			[['FITEA','HokuEng'],				8],
			[['FITEA','Sakai'],					1],
			[['FITEA','TeamOther'],			2],
			[['HokuEng','TeamOther'],		2],
	]

	list = [	
			{
				:groupname => 'FITEA',
				:site_url=>'http://www.fitea.org/',
				:logo_url=>'',
				:listnames=>['@tomohiro555/fitea'],
				:tags=>['IT','FUKUI'],
				:location=>'36.067278,136.219311',
				:description=>'福井情報技術者協会'
			}, 
			{
				:groupname => 'HokuEng',
				:site_url=>'https://www.google.com/accounts/ServiceLogin?passive=true&service=groups2&continue=http%3A%2F%2Fgroups.google.co.jp%2Fgroup%2Fhokuriku_engineer&cd=JP&hl=ja',
				:logo_url=>'',
				:listnames=>['@tomohiro555/fitea'],
				:location=>'36.573078,136.657562',
				:tags=>['IT','FUKUI','ISHIKAWA','TOYAMA'],
				:description=>'北陸エンジニアグループ '
			},
			{
				:groupname => 'Sakai',
				:site_url=>'https://www.google.com/accounts/ServiceLogin?passive=true&service=groups2&continue=http%3A%2F%2Fgroups.google.co.jp%2Fgroup%2Fhokuriku_engineer&cd=JP&hl=ja',
				:logo_url=>'',
				:listnames=>['@tomohiro555/fitea'],
				:location=>'36.172803,136.231842',
				:tags=>['IT','FUKUI'],
				:description=>'さかいのグループ'
			},
			{
				:groupname => 'TeamOther',
				:site_url=>'https://www.google.com/accounts/ServiceLogin?passive=true&service=groups2&continue=http%3A%2F%2Fgroups.google.co.jp%2Fgroup%2Fhokuriku_engineer&cd=JP&hl=ja',
				:logo_url=>'',
				:listnames=>['@tomohiro555/fitea'],
				:location=>'35.943044,136.198797',
				:tags=>['IT','FUKUI'],
				:description=>'つながったー開発グループ'
			}
	]

	###自動作成
	list.each do |item|
		Group.create( item )
		pos = create_pos_index(*( item[:location].split(/,/).map{|val| val.to_f}))
		GroupPostion.create({
				:groupname => item[:groupname],
				:posindex  => pos})
	end
	associations.each do |item|
		Association.create({
												:group1group2=>item[0].sort.join(':'),
												:value => item[1]		# 1～10
												})
	end

	erb	'ok <a href="./">戻る</a>'
end

get '/devutil/create_groupdata' do
	Group.destroy_all		#データ全消し
	Group.create( {	:groupname => 'FITEA',
									:site_url=>'http://www.fitea.org/',
									:logo_url=>'',
									:listnames=>['@tomohiro555/fitea'],
									:tags=>['IT','FUKUI'],
									:location=>'36.067278,136.219311',
									:description=>'福井情報技術者協会'
								} )
	Group.create( {	:groupname => 'HokuEng',
									:site_url=>'https://www.google.com/accounts/ServiceLogin?passive=true&service=groups2&continue=http%3A%2F%2Fgroups.google.co.jp%2Fgroup%2Fhokuriku_engineer&cd=JP&hl=ja',
									:logo_url=>'',
									:listnames=>['@tomohiro555/fitea'],
									:location=>'36.573078,136.657562',
									:tags=>['IT','FUKUI','ISHIKAWA','TOYAMA'],
									:description=>'北陸エンジニアグループ '
								} )
	Group.create( {	:groupname => 'Sakai',
									:site_url=>'https://www.google.com/accounts/ServiceLogin?passive=true&service=groups2&continue=http%3A%2F%2Fgroups.google.co.jp%2Fgroup%2Fhokuriku_engineer&cd=JP&hl=ja',
									:logo_url=>'',
									:listnames=>['@tomohiro555/fitea'],
									:location=>'36.172803,136.231842',
									:tags=>['IT','FUKUI'],
									:description=>'さかいのグループ'
								} )
	Group.create( {	:groupname => 'TeamOther',
									:site_url=>'https://www.google.com/accounts/ServiceLogin?passive=true&service=groups2&continue=http%3A%2F%2Fgroups.google.co.jp%2Fgroup%2Fhokuriku_engineer&cd=JP&hl=ja',
									:logo_url=>'',
									:listnames=>['@tomohiro555/fitea'],
									:location=>'35.943044,136.198797',
									:tags=>['IT','FUKUI'],
									:description=>'つながったー開発グループ'
								} )
	erb	'ok <a href="./">戻る</a>'
end

get '/devutil/create_GroupUserList' do
	#XXXX.destroy_all
	#XXXX.create ...
	GroupUserList.destroy_all		#データ全消し
	
	begin
	GroupUserList.create({
	  :groupname => 'FETEA',
	  :users => ['kabakiyo', 'hashimoto', 'okamoto', 'kobayashi'],
	  :count => 4
	})
	GroupUserList.create({
	  :groupname => '北エン',
	  :users => ['kabakiyo', 'hashimoto', 'okamoto', 'kobayashi'],
	  :count => 4
	})
    erb	'ok <a href="./">戻る</a>'
  rescue
    erb	'ok <a href="./">Error</a>'
  end
end

get '/devutil/create_UserDetailList' do
	#XXXX.destroy_all
	#XXXX.create ...
	erb	'ok <a href="./">戻る</a>'
end


def create_pos_index( ido , keido )
		ido_min = (ido*60).to_i
		keido_min = (keido*60).to_i
		sprintf("%+05d,%+05d", ido_min/10,keido_min/10)
end

get '/devutil/create_GroupPostion' do
	#XXXX.destroy_all
	#XXXX.create ...
	GroupPostion.destroy_all
	GroupPostion.create({
												:groupname => 'FITEA',
												:posindex  => create_pos_index(36.067278,136.219311)
											})
	GroupPostion.create({
												:groupname => 'HokuEng',
												:posindex  => create_pos_index(36.573078,136.657562)
											})
	GroupPostion.create({
												:groupname => 'Sakai',
												:posindex  => create_pos_index(36.172803,136.231842)
											})
	GroupPostion.create({
												:groupname => 'TeamOther',
												:posindex  => create_pos_index(35.943044,136.198797)
											})

	erb	'ok <a href="./">戻る</a>'
end

get '/devutil/create_TwitterAccount' do
	#XXXX.destroy_all
	#XXXX.create ...
	erb	'ok <a href="./">戻る</a>'
end


get '/devutil/create_Association' do
	Association.destroy_all
	Association.create({
											:group1group2=>['FITEA','HokuEng'].sort.join(':'),
											:value => 8		# 1～10
											})
	Association.create({
											:group1group2=>['FITEA','Sakai'].sort.join(':'),
											:value => 1		# 1～10
											})
	Association.create({
											:group1group2=>['FITEA','TeamOther'].sort.join(':'),
											:value => 2		# 1～10
											})
	Association.create({
											:group1group2=>['HokuEng','TeamOther'].sort.join(':'),
											:value => 2		# 1～10
											})
#	Association.create({
#											:group1group2=>['FITEA','HokuEng'].sort.join(':'),
#											:value => 8		# 1～10
#											})
	erb	'ok <a href="./">戻る</a>'
end

get '/devutil/test_GroupPostion' do

	pos1 = [35.95133,136.018982]
	pos2 = [36.248703,136.724854]
	@r = GroupPostion.get_groupname(pos1,pos2)

  @arr = []
  @hash = {}
	@group_names = GroupPostion.get_groupname(pos1,pos2)
  @group_names.each do |name|
    group = Group.query.filter(:groupname, "==", name).all[0]
    @hash[group.groupname] = group.key
    @arr << Hash[:id => group.key, :name => group.groupname,
                :lat => group.location.split(',')[0], :lng => group.location.split(',')[1]]
  end

  @asso = []
  @group_names.each_index do |i|[0]
    @group_names.each_index do |j|
      next if j <= i # G1とG2の計算が終わったらG2とG1を計算しないための制御
      @asso << [@hash[@group_names[i]], @hash[@group_names[j]], Association.query.filter(:group1group2, "==", [@group_names[i], @group_names[j]].sort.join(':')).all[0].value]
    end
  end
#  ary2 = @arr.map{|item| '{'+item.map{|k,v| k.to_s+':'+v.inspect}.join(' ')+'}'}.join(',')
#  @ary2 = [ary2]
  @result = [@arr, @asso]

	erb "<pre>#{h(@result)}</pre>"
end


get '/devutil/json' do
#text/javascript; charset=utf-8
	content_type 'text/javascript', :charset => 'utf-8'	
	'[1,2,3]'
end

get '/devutil/jsontest' do
	erb %|
		<script type="text/javascript">
		<!--
			$(document).ready(function(){
				$.getJSON("/group/position/",{},
function(json){
  alert(json);
});
			});
		//-->
		</script>
	
	<div id="content-main">
		<div id="map" class="test">
			地図
		</div>
		<div id="group_details" class="test">
			タイムライン
		</div>
	</div>
	|
end

get '/devutil/printenv/' do
	erb %{
<pre>
	<%=h @env['java.servlet_context'].getServerInfo() %>
	<% @env.each do |k,v| %>
		[<%= h k %>]<%= h v.inspect %>
	<% end %>
</pre>
}
end

#Twitterテスト
get '/devutil/twitter_get_list_timeline/:screen_name/:list_name/:page/' do
	include_class('com.ts0604.twitterapi.MyTwitter')
  content_type 'text/javascript', :charset => 'utf-8'

	r = [{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"},
{"screen_name"=>"xxxxx","text"=>"php 5.2.12がubuntuに入らん。5.3.1ならちゃんと動いたのに。ふぁっきゅー"}
]
#	WebAPI::JsonBuilder.new.build(r)
	if MyTwitter.basicAuth('twitter_name','password') then	#BASIC認証
		MyTwitter.getListTimeLine(params[:screen_name],params[:list_name], (params[:page]).to_i);	#リストのタイムライン取得
	end
end

get '/devutil/twitter_get_list/:screen_name/:list_name/' do
	include_class('com.ts0604.twitterapi.MyTwitter')
  content_type 'text/javascript', :charset => 'utf-8'
  if MyTwitter.basicAuth('twitter_name','password') then	#BASIC認証
		MyTwitter.getList(params[:screen_name], params[:list_name]);	#リスト取得
	end
end

get '/devutil/sample_of_application_setting/' do
	@setting = AppSetting.applicationsetting
	@setting.twitter_id	#twitter id
	@setting.twitter_pass	#twitter password
	if @setting.twitter_id 
		#twiiter id 設定済み処理
	else
		#twiiter id 未設定の処理
	end

	erb %{
		<h1>アプリケーション設定</h1>
		<div>
		@setting.twitter_id: "<%= h @setting.twitter_id %>"<br/>
		@setting.twitter_pass: "<%= h @setting.twitter_pass %>"<br/>
		</div>

		<a href="/admintool/">管理者画面へ</a>
	}
end

