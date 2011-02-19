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
    public enum DrawingMode { 
        None, Line, Video, Image
    }

    public partial class MainPage : UserControl
    {
        private DrawingMode currentMode = DrawingMode.None;
        public DrawingMode CurrentMode {
            set { 
                /* Do nothing when the current drawing mode and the value is same. */
                if (value != this.currentMode)
                {
                    /* Quit the previous mode */
                    switch (this.currentMode)
                    {
                        case DrawingMode.Line:
                            quitDrawingLine();
                            break;
                        case DrawingMode.Image:
                            break;
                        case DrawingMode.Video:
                            break;
                    }

                    this.currentMode = value;

                    /* Start the next mode */
                    switch (value)
                    { 
                        case DrawingMode.Line:
                            startDrawingLine();
                            break;
                        case DrawingMode.Image:
                            break;
                        case DrawingMode.Video:
                            break;
                    }
                }
            }
            get { return this.currentMode; }
        }

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
            if (this.CurrentMode == DrawingMode.Line)
            {
                /* When in drawing mode */
                this.CurrentMode = DrawingMode.None;
            }
            else { 
                /* Turn on drawing line mode */
                this.CurrentMode = DrawingMode.Line;
            }
        }

        private void startDrawingLine()
        {
            // DO NOT change drawing mode in this method.
            drawALineButton.Content = "Turn off drawing line mode";

            this.locationCollection = new LocationCollection();

            Debug.WriteLine("Start drawing a line");
        }

        private void quitDrawingLine()
        {
            // DO NOT change drawing mode in this method.

            /* Turn off drawing line mode */
            drawALineButton.Content = "Draw a line";

            /* Create a layer and add to the map */
            MapLine mapLine = new MapLine();
            mapLine.setLocationCollection(this.locationCollection);
            mapLine.draw(mainMap);

            Debug.WriteLine("Stopped drawing a line");
        }

        private void mapClickedInLineMode(object sender, MapMouseEventArgs e)
        {

            Location location = new Location();
            if (mainMap.TryViewportPointToLocation(e.ViewportPoint, out location))
            {
                /* Success */
                this.locationCollection.Add(location);
                Debug.WriteLine("Point (" + location.Longitude + "," + location.Latitude + ")");
            }
            else
            {
                /* Fails */
                Debug.WriteLine("Something wrong has happened in converting viewport to location.");
            }
        }

        private void mainMap_MouseClick(object sender, MapMouseEventArgs e)
        {
            switch (this.CurrentMode) { 
                case DrawingMode.Line:
                    mapClickedInLineMode(sender,e);
                    break;
                case DrawingMode.Video:
                    break;
                case DrawingMode.Image:
                    break;
            }
        }
    }
}
