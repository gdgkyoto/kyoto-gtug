require 'tiny_ds'

#グループの基本情報
class  Group < TinyDS::Base
	property	    :groupname      ,:string     #50文字程度でどう?
	property	    :site_url       ,:text       #サイト情報
	property	    :logo_url       ,:text       #ロゴのURL
	property	    :listnames      ,:list       #リスト(文字列)の集合
	property	    :tags           ,:list       #タグ(文字列)の集合
	property	    :description    ,:text       #詳細

	property			:location				,:string     #緯度経度 例:36.173357,136.224976

	property	    :swcp           ,:string     #ソフトウェアチェックポイント
end

class ApplicationLog < TinyDS::Base
end

#グループのユーザリスト(twitter api のキャッシュ的存在)
class GroupUserList< TinyDS::Base
	property	    :groupname      ,:string
	property	    :users          ,:list       #ユーザ(文字列)の集合
	property	    :count          ,:integer    #ユーザ数

	property	    :swcp           ,:string     #ソフトウェアチェックポイント
end

#ユーザ詳細情報のリスト(twitter api のキャッシュ的存在)
class UserDetailList< TinyDS::Base
	property	    :username       ,:string     #ユーザ名
	property	    :location       ,:text       #twitterの表示位置
	property	    :pos            ,:list       #[緯度,経度]
	property	    :swcp           ,:string     #ソフトウェアチェックポイント
end

#グループの位置情報
class GroupPostion< TinyDS::Base
	property	    :posindex       ,:string     #緯度経度の丸め値
	property	    :groupname      ,:string     #グループ名

	# pos1 南西の緯度経度
	#	pos2 北東の緯度経度
	def self.get_groupname( pos1 , pos2 )
		logs = []
		keido_s = ((pos1[0]*60-9)/10)*10		#丸め処理
		keido_e = ((pos2[0]*60+9)/10)*10		#丸め処理
		groups = []
		range = Range.new(keido_s,keido_e)
		range.step(10) do |keido|
			ss = [keido/60.0,pos1[1]]
			se = [keido/60.0,pos2[1]]
			ks = create_key_name(ss)
			ke = create_key_name(se)
			logs << [ks,ke]
			self.query.filter(:posindex, ">=", ks).
										filter(:posindex, "<=", ke).
											all.all?{|r| groups << r.groupname }
		end
		groups
	end

	def self.create_key_name( pos )
		ido , keido = *pos
		ido_min = (ido*60).to_i
		keido_min = (keido*60).to_i
		sprintf("%+05d,%+05d", ido_min/10,keido_min/10)
	end

end

#ソフトウェアチェックポイント構造
class SoftwareCheckpoint< TinyDS::Base
	property	    :current    ,:string     #表示用のcheckpoint
	property	    :update     ,:string     #更新処理のcheckpoint
	property	    :state      ,:integer    #処理状態
	property	    :delete     ,:string     #削除中に利用
end


#グループ-グループ間の相関
class Association< TinyDS::Base
	property	    :group1group2   ,:string     #50文字x2 程度 (FITEA:WCAF)のような連結文字列を想定
	property	    :value    		  ,:integer    #強さ(正負)最大??
end

class TwitterAccount<TinyDS::Base
	property			:account		,:string
	property			:password		,:string
end
