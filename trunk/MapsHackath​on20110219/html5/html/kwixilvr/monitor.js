var map;
var startPos = {
    lat: 35
    , lng: 135
};

function setMarker(latLng){
    var marker = new google.maps.Marker({
        position: latLng
        , map: map
    });
}

function handlePos(pos){
    var latLng = new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude);

    map.panTo(latLng);
    setMarker(latLng);
}

function handleError(error){
    if(console){
        console.log(error);
    }
    else{
        alert(error);
    }
}

function mapSelfPos(){
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(handlePos, handleError, {
                enableHighAccuracy: false
                // 5秒以内に取得できなかったらエラー
                , timeout: 5000
                // 1分以内に取得した位置情報のみ使用する
                , maximumAge: 60 * 1000
            }
        );
    }
    else{
        alert("Geolocation API disabled");
    }
}

$(document).ready(function(){
    $.getJSON("/savetheworld/api/helps.json", function(json){
        var helpList = $("#help-list");
        for( var i = 0; i < json.length; i++){
            var help = json[i];
            helpList.append("<p>" + help.id + ":" + help.message + ":(" + help.lat + "," + help.lng + ")</p>");
        }
    });

    var latlng = new google.maps.LatLng(startPos.lat, startPos.lng);
    var options = {
        center: latlng,
        zoom: 15,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map($("#map")[0], options);

    google.maps.event.addListenerOnce(map, 'tilesloaded', function(){
        setTimeout(mapSelfPos, 3000);
    });
});
