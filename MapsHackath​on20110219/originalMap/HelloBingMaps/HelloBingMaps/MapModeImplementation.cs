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
    public interface MapModeImplementation
    {
        DrawingMode getDrawingMode();
        void start(MainPage mainPage);
        MapData end(MainPage mainPage);
        void onClick(MainPage mainPage, object sender, MapMouseEventArgs e);
        void onMouseMove(MainPage mainPage, object sender, MouseEventArgs e);
    }
}
