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
    public class MapNoneMode : MapModeImplementation
    {

        public DrawingMode getDrawingMode()
        {
            return DrawingMode.None;
        }

        public void start(MainPage mainPage)
        {
            // Do nothing
        }

        public MapData end(MainPage mainPage)
        {
            // Do nothing
            return null;
        }

        public void onClick(MainPage mainPage, object sender, Microsoft.Maps.MapControl.MapMouseEventArgs e)
        {
            // Do nothing
        }

        public void onMouseMove(MainPage mainPage, object sender, MouseEventArgs e)
        {
            // Do nothing
        }
    }
}
