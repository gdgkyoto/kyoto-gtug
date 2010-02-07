require 'tiny_ds'

#グループの基本情報
class  Group < TinyDS::Base
	property	    :groupname      ,:string     #50文字程度でどう?
	property	    :site_url       ,:text       #サイト情報
	property	    :logo_url       ,:text       #ロゴのURL
	property	    :listnames      ,:list       #リスト(文字列)の集合
	property	    :tags           ,:list       #タグ(文字列)の集合
	property	    :description    ,:text       #詳細

	property	    :swcp           ,:string     #ソフトウェアチェックポイント
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
	property	    :value      ,:integer    #強さ(正負)最大??
end

