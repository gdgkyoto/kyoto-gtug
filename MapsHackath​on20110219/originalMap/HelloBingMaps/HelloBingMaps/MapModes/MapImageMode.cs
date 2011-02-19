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
    public class MapImageMode : MapModeImplementation
    {

        private URLWindow window = new URLWindow();
        private MainPage mainPage;
        private MapMouseEventArgs mouseEvent; 

        public MapImageMode()
        {
            window.Closed += new EventHandler(window_Closed);
        }
        
        void window_Closed(object sender, EventArgs e)
        {
            String URL = window.getURL();
            Debug.WriteLine("URL: " + URL);

            Location location = new Location();

            if (this.mainPage.mainMap.TryViewportPointToLocation(this.mouseEvent.ViewportPoint, out location))
            {
                /* Success */
                Debug.WriteLine("Point (" + location.Longitude + "," + location.Latitude + ")");

                MapImage image = new MapImage(URL);
                Debug.WriteLine("yabai: " + URL);
                image.setLocation(location);
                image.draw(mainPage.mainMap);
            }
            else
            {
                /* Fails */
                Debug.WriteLine("Something wrong has happened in converting viewport to location.");
            }
        }
        
        public DrawingMode getDrawingMode()
        {
            return DrawingMode.Image;
        }

        public void start(MainPage mainPage)
        {
            // do nothing
        }

        public MapData end(MainPage mainPage)
        {
            // do nothing
            return null;
        }

        

        public void onClick(MainPage mainPage, object sender, MapMouseEventArgs e)
        {
            this.mainPage = mainPage;
            this.mouseEvent = e;
            window.Show(); 
        }

        public void onMouseMove(MainPage mainPage, object sender, MouseEventArgs e)
        {
            // do nothing
        }
    }
}
