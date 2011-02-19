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
using System.Diagnostics;

namespace HelloBingMaps.MapModes
{
    public class MapLineMode : MapModeImplementation
    {
        private LocationCollection locationCollection = new LocationCollection();

        public void start(MainPage mainPage)
        {
            mainPage.drawALineButton.Content = "Turn off drawing line mode";
            this.locationCollection = new LocationCollection();

            Debug.WriteLine("Start drawing a line");
        }

        public MapData end(MainPage mainPage)
        {
            /* Turn off drawing line mode */
            mainPage.drawALineButton.Content = "Draw a line";

            /* Create a layer and add to the map */
            MapLine mapLine = new MapLine();
            mapLine.setLocationCollection(this.locationCollection);
            //mapLine.draw(mainPage.mainMap);

            Debug.WriteLine("Stopped drawing a line");

            return mapLine;
        }

        public void onClick(MainPage mainPage, object sender, MapMouseEventArgs e)
        {
            Location location = new Location();
            if (mainPage.mainMap.TryViewportPointToLocation(e.ViewportPoint, out location))
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

        public void onMouseMove(MainPage mainPage, object sender, MouseEventArgs e)
        {
        }

        public DrawingMode getDrawingMode()
        {
            return DrawingMode.Line;
        }
    }
}
