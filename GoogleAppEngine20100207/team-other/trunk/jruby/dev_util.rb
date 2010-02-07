get '/devutil/' do

	erb	:dev_util
end

get '/devutil/create_groupdata' do
	Group.destroy_all		#データ全消し
	Group.create( {	:groupname => 'FITEA',
									:site_url=>'http://www.fitea.org/',
									:logo_url=>'',
									:listnames=>['@tomohiro555/fitea'],
									:tags=>['IT','FUKUI'],
									:description=>'福井情報技術者協会'
								} )
	Group.create( {	:groupname => 'HokuEng',
									:site_url=>'https://www.google.com/accounts/ServiceLogin?passive=true&service=groups2&continue=http%3A%2F%2Fgroups.google.co.jp%2Fgroup%2Fhokuriku_engineer&cd=JP&hl=ja',
									:logo_url=>'',
									:listnames=>['@tomohiro555/fitea'],
									:tags=>['IT','FUKUI','ISHIKAWA','TOYAMA'],
									:description=>'北陸エンジニアグループ '
								} )
	Group.create( {	:groupname => 'Sakai',
									:site_url=>'https://www.google.com/accounts/ServiceLogin?passive=true&service=groups2&continue=http%3A%2F%2Fgroups.google.co.jp%2Fgroup%2Fhokuriku_engineer&cd=JP&hl=ja',
									:logo_url=>'',
									:listnames=>['@tomohiro555/fitea'],
									:tags=>['IT','FUKUI'],
									:description=>'さかいのグループ'
								} )
	erb	'ok <a href="./">戻る</a>'
end


get '/devutil/create_GroupUserList' do
end


get '/devutil/create_UserDetailList' do
end

get '/devutil/create_GroupPostion' do
end

get '/devutil/create_TwitterAccount' do
end


get '/devutil/create_Association' do
end

