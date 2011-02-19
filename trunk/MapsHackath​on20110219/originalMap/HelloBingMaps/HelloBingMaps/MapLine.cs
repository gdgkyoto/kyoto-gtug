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
    public class MapLine : MapData
    {
        private LocationCollection vertex = new LocationCollection();

        void draw()
        {
            System.Console.WriteLine("Draw a line : Number of vertexes " + this.vertex.Count);
        }

        void setVertex(LocationCollection locationCollection) {
            this.vertex = locationCollection;
        }
    }
}
