using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Maps.MapControl;

namespace HelloBingMaps
{
    public class MapImage : MapData
    {
        private Location location = new Location();
        private String URL;

        public MapImage(String URL)
        {
            this.URL = URL;
        }

        public void setLocation(Location location){
            this.location = location;
        }

        public void draw(Map map){

            MapLayer imageLayer = new MapLayer();

            Image image = new Image();
            Uri uri = new Uri(this.URL);
            image.Source = new System.Windows.Media.Imaging.BitmapImage(uri);

            //Define the image display properties
            image.Opacity = 0.8;
            image.Stretch = System.Windows.Media.Stretch.None;

            //Center the image around the location specified
            PositionOrigin position = PositionOrigin.Center;

            //Add the image to the defined map layer
            imageLayer.AddChild(image, location, position);
            //Add the image layer to the map
            map.Children.Add(imageLayer);
        }

    }
}
