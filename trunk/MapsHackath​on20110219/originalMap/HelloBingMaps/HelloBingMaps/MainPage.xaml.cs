using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Maps.MapControl.Core;
using Microsoft.Maps.MapControl;

namespace HelloBingMaps
{
    public partial class MainPage : UserControl
    {
        private bool drawingLineMode = false;

        public MainPage()
        {
            InitializeComponent();
        }

        private void changeToMercatorlModeButton_Click(object sender, RoutedEventArgs e)
        {
            mainMap.Mode = new MercatorMode();
        }

        private void changeToAerialModeButton_Click(object sender, RoutedEventArgs e)
        {
            mainMap.Mode = new AerialMode();
        }

        private void drawALineButton_Click(object sender, RoutedEventArgs e)
        {
            drawingLineMode = true;
        }

        private void mainMap_MouseClick(object sender, MapMouseEventArgs e)
        {
            if (drawingLineMode) {
            }
        }

    }
}
