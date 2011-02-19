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
using System.Runtime.Serialization;

namespace HelloBingMaps
{
    [DataContract]
    public class MapLine: MapData
    {
        [DataMember]
        public LocationCollection locationCollection = new LocationCollection();

        public void setLocationCollection(LocationCollection locationCollection)
        {
            this.locationCollection = locationCollection;
        }

        public MapPolyline getMapPolyline() { 
            return new MapPolyline() {
                Locations = this.locationCollection,
                Stroke = new SolidColorBrush(Colors.Blue),
                StrokeThickness = 4
            };
        }

        public void draw(Map map)
        {
            MapLayer mapLayer = new MapLayer();
            mapLayer.Children.Add(getMapPolyline());

            map.Children.Add(mapLayer);

            System.Console.WriteLine("Draw a line : Number of vertexes " + this.locationCollection.Count);
        }
    }
}
