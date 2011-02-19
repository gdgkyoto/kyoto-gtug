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

        public void setLocation(Location location){
            this.location = location;
        }

        public void draw(Map map){

            MapLayer imageLayer = new MapLayer();

            Image image = new Image();
            //Define the URI location of the image
            image.Source = new System.Windows.Media.Imaging.BitmapImage(new Uri("image.jpg", UriKind.Relative));
            //Define the image display properties
            image.Opacity = 0.8;
            image.Stretch = System.Windows.Media.Stretch.None;
            //The map location to place the image at
            // location = new Location() { Latitude = -45, Longitude = 122 };
            //Center the image around the location specified
            PositionOrigin position = PositionOrigin.Center;

            //Add the image to the defined map layer
            imageLayer.AddChild(image, location, position);
            //Add the image layer to the map
            map.Children.Add(imageLayer);
        }

    }
}
