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

namespace HelloBingMaps
{
    public partial class YoutubeChildWindow : ChildWindow
    {
        private String youtubeVideoID = null;

        public void setYoutubeVieoId(String youtubeId) {
            this.youtubeVideoID = youtubeId;
            youtubeBrowser.NavigateToString("<html><body style=\"margin:0;overflow:hidden;\"><iframe title=\"YouTube video player\" width=\"560\" height=\"349\" src=\"http://www.youtube.com/v/" + this.youtubeVideoID + "&autoplay=1\" frameborder=\"0\" allowfullscreen autoplay=\"1\"></iframe></body></html>");
        
        }

        public YoutubeChildWindow()
        {
            InitializeComponent();
        }

        private void OKButton_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = true;
        }

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = false;
        }
    }
}

