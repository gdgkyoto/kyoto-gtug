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
using System.Collections.Generic;
using Microsoft.Maps.MapControl;
using System.Runtime.Serialization;

namespace HelloBingMaps
{
    public class MapDataStore
    {
        private List<MapData> mapDatas = new List<MapData>();

        public void addAndDraw(MapData mapData, Map mainMap) {
            mapData.draw(mainMap);
            mapDatas.Add(mapData);
        }

        private void store() {
            DataContractSerializer serializer = new DataContractSerializer(typeof(List<MapData>));
        }

        public void restoreAndDraw(Map mainMap) {
            /* Restore */


            /* Draw */
            foreach(MapData mapData in mapDatas){
                mapData.draw(mainMap);
            }
        }
    }
}
