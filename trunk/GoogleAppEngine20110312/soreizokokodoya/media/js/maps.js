var map;
var url = '/media/js/sites.json';

function initialize() {
  var initLatLng = new google.maps.LatLng( 35.943191, 136.18869900000004);
  var myOptions = {
    zoom: 14,
    center: initLatLng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  }
  map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

  callJSONP('/media/js/maps.js');

  // set events
  google.maps.event.addListener(map, 'zoom_changed', function() {
    var dataurl = url;
    callJSONP(url);
  });
  google.maps.event.addListener(map, 'bounds_changed', function() {
    var dataurl = url;
    callJSONP(url);
  });
}

callJSONP = function(url) {
  var target = document.createElement('script');
  target.charset = 'utf-8';
  target.src = url;
  document.body.appendChild(target);
}

function setMarkers(locations) {
  for (var i = 0; i < locations.length; i++) {
    var site = locations[i];
    var myLatLng = new google.maps.LatLng(site.latitude, site.longitude);
    var contentString = '<p>'+
        '速度: ' + site.speed /1000 +' Mbpx<br />'+
        '日時: ' + site.measured_time + '<br />'+
        '住所: ' + site.adress + '<br />'+
        'キャリア: ' + site.carrier + '<br />'+
        '備考: ' + site.comment + '<br />'+
        '</p>';
    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        html: contentString,
        title: site.address
    });
    var infowindow = new google.maps.InfoWindow({
        content: 'loading...'
    });
    google.maps.event.addListener(marker, 'click', function() {
      infowindow.setContent(this.html);
      infowindow.open(map, this);
    });
  }

}
