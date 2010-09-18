
//Secret:
//eb1291d3420e78f1

// 画像検索を行う関数
//function photo_search ( param ) {
//function photo_search () {
function getFlickr () {
    // APIリクエストパラメタの設定
		var param = {};
    param.api_key  = 'f5f7f352859ffcd156d8feb974aa6619';
		param.user_id = '54049510@N06';
    param.method   = 'flickr.favorites.getPublicList';
//    param.method   = 'flickr.photosets.getPhotos';
    //param.per_page = 1;
    //param.sort     = 'date-posted-desc';
    param.format   = 'json';
    param.jsoncallback = 'jsonFlickrApi';
		param.extras = 'url_m';

    // APIリクエストURLの生成(GETメソッド)
    var url = 'http://www.flickr.com/services/rest/?'+
               obj2query( param );

    // script 要素の発行
    var script  = document.createElement( 'script' );
    script.type = 'text/javascript';
    script.src  = url;
    document.body.appendChild( script );
};

// 現在の表示内容をクリアする
function remove_children ( id ) {
    var div = document.getElementById( id );
    while ( div.firstChild ) { 
        div.removeChild( div.lastChild );
    }
};

// オブジェクトからクエリー文字列を生成する関数
function obj2query ( obj ) {
    var list = [];
    for( var key in obj ) {
        var k = encodeURIComponent(key);
        var v = encodeURIComponent(obj[key]);
        list[list.length] = k+'='+v;
    }
    var query = list.join( '&' );
    return query;
}

// Flickr検索終了後のコールバック関数
function jsonFlickrApi ( data ) {
    // データが取得できているかチェック
    if ( ! data ) return;

		var list = data.photos.photo;
		var urls = new Array();
    for( var i=0; i<list.length; i++ ) {
				var photo = list[i].url_m;
				urls.push(photo);
    }
		return urls;
}