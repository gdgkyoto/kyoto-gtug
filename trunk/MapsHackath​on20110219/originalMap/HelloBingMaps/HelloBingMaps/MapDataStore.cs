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
using System.IO;
using System.Text;
using System.Diagnostics;
using System.IO.IsolatedStorage;

namespace HelloBingMaps
{
    public class MapDataStore
    {
        private List<MapLine> mapLines = new List<MapLine>();

        public void addAndDraw(MapData mapData, Map mainMap) {
            if (mapData != null)
            {
                if (mapData is MapLine)
                {
                    mapData.draw(mainMap);
                    mapLines.Add((MapLine)mapData);
                    store();
                }
            }
        }

        private void store() {
            MemoryStream mapLinesMS = new MemoryStream();
            DataContractSerializer serializer = new DataContractSerializer(typeof(List<MapLine>));

            serializer.WriteObject(mapLinesMS, this.mapLines);
            String mapLinesString = byteToString(mapLinesMS.ToArray());
            mapLinesMS.Close();

            Debug.WriteLine("Serialized : " + mapLines.Count + "\n" + mapLinesString);

            IsolatedStorageSettings.ApplicationSettings["mapLines"] = mapLinesString;
        }

        private String byteToString(byte[] array)
        {
            return Encoding.UTF8.GetString(array, 0, array.Length);
        }

        public void restoreAndDraw(Map mainMap) {
            /* Restore */
            if (IsolatedStorageSettings.ApplicationSettings.Contains("mapLines"))
            {
                String mapLinesString = IsolatedStorageSettings.ApplicationSettings["mapLines"] as String;

                DataContractSerializer serializer = new DataContractSerializer(typeof(List<MapLine>));
                MemoryStream ms = new MemoryStream(Encoding.UTF8.GetBytes(mapLinesString));
                this.mapLines = serializer.ReadObject(ms) as List<MapLine>;
                ms.Close();

                /* Draw */
                foreach (MapData mapData in mapLines)
                {
                    mapData.draw(mainMap);
                }
            }
        }

        public void resetData() {
            IsolatedStorageSettings.ApplicationSettings.Remove("mapLines");

            Debug.WriteLine("Deleted datas");
        }
    }
}
