﻿#pragma checksum "C:\Users\Yuki\documents\visual studio 2010\Projects\HelloBingMaps\HelloBingMaps\MainPage.xaml" "{406ea660-64cf-4c82-b6f0-42d48172a799}" "E94943D4B7C18ABFF849DCCF80B7ED3C"
//------------------------------------------------------------------------------
// <auto-generated>
//     このコードはツールによって生成されました。
//     ランタイム バージョン:4.0.30319.1
//
//     このファイルへの変更は、以下の状況下で不正な動作の原因になったり、
//     コードが再生成されるときに損失したりします。
// </auto-generated>
//------------------------------------------------------------------------------

using Microsoft.Maps.MapControl;
using System;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Automation.Peers;
using System.Windows.Automation.Provider;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Interop;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Resources;
using System.Windows.Shapes;
using System.Windows.Threading;


namespace HelloBingMaps {
    
    
    public partial class MainPage : System.Windows.Controls.UserControl {
        
        internal System.Windows.Controls.Grid LayoutRoot;
        
        internal Microsoft.Maps.MapControl.Map mainMap;
        
        internal System.Windows.Controls.Button changeToMercatorlModeButton;
        
        internal System.Windows.Controls.Button changeToAerialModeButton;
        
        internal System.Windows.Controls.Button drawALineButton;
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Windows.Application.LoadComponent(this, new System.Uri("/HelloBingMaps;component/MainPage.xaml", System.UriKind.Relative));
            this.LayoutRoot = ((System.Windows.Controls.Grid)(this.FindName("LayoutRoot")));
            this.mainMap = ((Microsoft.Maps.MapControl.Map)(this.FindName("mainMap")));
            this.changeToMercatorlModeButton = ((System.Windows.Controls.Button)(this.FindName("changeToMercatorlModeButton")));
            this.changeToAerialModeButton = ((System.Windows.Controls.Button)(this.FindName("changeToAerialModeButton")));
            this.drawALineButton = ((System.Windows.Controls.Button)(this.FindName("drawALineButton")));
        }
    }
}

