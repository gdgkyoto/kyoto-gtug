﻿using System;
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
using HelloBingMaps.MapModes;

namespace HelloBingMaps
{
    public enum DrawingMode { 
        None, Line, Video, Image
    }

    public partial class MainPage : UserControl
    {
        private MapModeImplementation currentMapMode = createNewMode(DrawingMode.None);

        public DrawingMode CurrentMode {
            set { 
                /* Do nothing when the current drawing mode and the value is same. */
                if (value != this.CurrentMode)
                {
                    /* Quit the previous mode */
                    this.currentMapMode.end(this);
                    
                    /* Change to the new mode */
                    this.currentMapMode = createNewMode(value);
                    this.currentMapMode.start(this);
                }
            }
            get {
                return this.currentMapMode.getDrawingMode();
            }
        }

        private static MapModeImplementation createNewMode(DrawingMode mode) {
            switch (mode) { 
                case DrawingMode.Line:
                    return new MapLineMode();
                case DrawingMode.Image:
                    return new MapImageMode();
                case DrawingMode.Video:
                    return new MapVideoMode();
                default:
                    return new MapNoneMode();
            }
        }

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

        private void mainMap_MouseClick(object sender, MapMouseEventArgs e)
        {
            this.currentMapMode.onClick(this, sender, e);
        }

        private void mainMap_MouseMove(object sender, MouseEventArgs e)
        {
            this.currentMapMode.onMouseMove(this, sender, e);
        }
    }
}
