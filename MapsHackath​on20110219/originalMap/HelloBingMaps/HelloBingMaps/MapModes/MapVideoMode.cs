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

namespace HelloBingMaps.MapModes
{
    public class MapVideoMode : MapModeImplementation
    {

        public DrawingMode getDrawingMode()
        {
            return DrawingMode.Video;
        }

        public void start(MainPage mainPage)
        {
            throw new NotImplementedException();
        }

        public MapData end(MainPage mainPage)
        {
            throw new NotImplementedException();
        }

        public void onClick(MainPage mainPage, object sender, Microsoft.Maps.MapControl.MapMouseEventArgs e)
        {
            throw new NotImplementedException();
        }

        public void onMouseMove(MainPage mainPage, object sender, MouseEventArgs e)
        {
            throw new NotImplementedException();
        }
    }
}
