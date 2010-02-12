■開発環境準備

	1. dev_appserver.rb .
	2. http://localhost:8080/

	初回のみ
		■開発データ作成URL を参考にアクセス。

■開発データ作成URL
	http://localhost:8080/devutil/
	上記にアクセスして、クリックすると開発データが作成される
		・グループデータ作成
		・グループ間のつながりデータ作成
		・グループ位置の検索インデックス作成

■ローカルデータ確認
	http://localhost:8080/_ah/admin


■ダウンロードしたもの

	JavaScript(MIT)
		http://github.com/dknight/jQuery-Notify-bar

	JSON
		

	Twitter4j
		http://twitter4j.org/ja/index.html#download

■jarファイル追加

	trunk/java/のMyTwitter.jarとtwitter4j-core-2.1.0.jarを
	jruby/WEB-INF/lib/へコピーしてください。