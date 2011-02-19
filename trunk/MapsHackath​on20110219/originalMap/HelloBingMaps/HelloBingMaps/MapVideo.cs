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
using System.Runtime.Serialization;
using Microsoft.Maps.MapControl;
using System.Diagnostics;

namespace HelloBingMaps
{
    [DataContract]
    public class MapVideo : MapData
    {
        Map ref_mainMap = null;

        [DataMember]
        public Location location = new Location();

        [DataMember]
        public String youtubeMovieId;

        public void setYoutubeMovieId(String videoId) {
            this.youtubeMovieId = videoId;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public void draw(Map map)
        {
            MapLayer imageLayer = new MapLayer();

            Image image = new Image();
            image.Source = new System.Windows.Media.Imaging.BitmapImage(new Uri("movie.png", UriKind.Relative));
            image.Opacity = 0.8;
            image.Stretch = System.Windows.Media.Stretch.None;
            image.MouseLeftButtonDown += new MouseButtonEventHandler(image_MouseLeftButtonDown);

            PositionOrigin position = PositionOrigin.Center;

            imageLayer.AddChild(image, location, position);
            map.Children.Add(imageLayer);

            ref_mainMap = map;
        }

        void image_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            //Debug.WriteLine("The image is clicked");
            //WebBrowser youtubePlayer = new WebBrowser();
            //youtubePlayer.NavigateToString("<html><body style=\"margin:0;overflow:hidden;\"><iframe title=\"YouTube video player\" width=\"560\" height=\"349\" src=\"http://www.youtube.com/v/" + this.youtubeMovieId  + "&autoplay=1\" frameborder=\"0\" allowfullscreen autoplay=\"1\"></iframe></body></html>");

            //youtubePlayer.Width = 560;
            //youtubePlayer.Height = 350;
            //ref_mainMap.Children.Add(youtubePlayer);

            YoutubeChildWindow youtubeChildWindow = new YoutubeChildWindow();

            youtubeChildWindow.Show();
            youtubeChildWindow.setYoutubeVieoId(this.youtubeMovieId);
        }
    }
}
