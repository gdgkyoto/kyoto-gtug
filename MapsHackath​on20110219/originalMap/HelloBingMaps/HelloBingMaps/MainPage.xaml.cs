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
using System.Diagnostics;

namespace HelloBingMaps
{
    public partial class MainPage : UserControl
    {
        private bool drawingLineMode = false;
        private LocationCollection locationCollection = new LocationCollection();

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
            if (this.drawingLineMode)
            {
                /* Turn off drawing line mode */
                this.drawingLineMode = false;
                drawALineButton.Content = "Draw a line";

                /* Create a layer and add to the map */
                MapLine mapLine = new MapLine();
                mapLine.setLocationCollection(this.locationCollection);
                mapLine.draw(mainMap);

                Debug.WriteLine("Stopped drawing a line");
            }
            else { 
                /* Turn on drawing line mode */
                this.drawingLineMode = true;
                drawALineButton.Content = "Turn off drawing line mode";

                this.locationCollection = new LocationCollection();

                Debug.WriteLine("Start drawing a line");
            }
        }

        private void mainMap_MouseClick(object sender, MapMouseEventArgs e)
        {
            if (drawingLineMode) {
                Location location = new Location();
                if (mainMap.TryViewportPointToLocation(e.ViewportPoint, out location))
                {
                    /* Success */
                    this.locationCollection.Add(location);
                    Debug.WriteLine("Point (" + location.Longitude + "," + location.Latitude + ")");
                }
                else { 
                    /* Fails */
                    Debug.WriteLine("Something wrong has happened in converting viewport to location.");
                }
            }
        }
    }
}
