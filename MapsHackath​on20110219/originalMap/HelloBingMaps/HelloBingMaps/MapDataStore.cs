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
        private List<MapVideo> mapVideos = new List<MapVideo>();
        private List<MapImage> mapImages = new List<MapImage>();

        public void addAndDraw(MapData mapData, Map mainMap) {
            if (mapData != null)
            {
                if (mapData is MapLine)
                {
                    mapData.draw(mainMap);
                    mapLines.Add((MapLine)mapData);
                    store();
                }
                if (mapData is MapVideo)
                {
                    mapData.draw(mainMap);
                    mapVideos.Add((MapVideo)mapData);
                    store();
                    
                }
                if (mapData is MapImage)
                {
                    mapData.draw(mainMap);
                    mapImages.Add((MapImage)mapData);
                    store();
                }
            }
        }

        private void store() {
            IsolatedStorageSettings.ApplicationSettings["mapLines"] = returnSerializedString<List<MapLine>>(this.mapLines);
            IsolatedStorageSettings.ApplicationSettings["mapVideos"] = returnSerializedString<List<MapVideo>>(this.mapVideos);
            Debug.WriteLine("Number of images : " + this.mapImages.Count);
            IsolatedStorageSettings.ApplicationSettings["mapImages"] = returnSerializedString<List<MapImage>>(this.mapImages);
        }

        private String returnSerializedString<Type>(Type list) {
            MemoryStream ms = new MemoryStream();
            DataContractSerializer serializer = new DataContractSerializer(typeof(Type));

            serializer.WriteObject(ms, list);
            String serializedString = byteToString(ms.ToArray());
            ms.Close();

            return serializedString;
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

            if (IsolatedStorageSettings.ApplicationSettings.Contains("mapVideos"))
            {
                String mapVideosString = IsolatedStorageSettings.ApplicationSettings["mapVideos"] as String;

                DataContractSerializer serializer = new DataContractSerializer(typeof(List<MapVideo>));
                MemoryStream ms = new MemoryStream(Encoding.UTF8.GetBytes(mapVideosString));
                this.mapVideos = serializer.ReadObject(ms) as List<MapVideo>;
                ms.Close();

                /* Draw */
                foreach (MapData mapData in mapVideos)
                {
                    mapData.draw(mainMap);
                }
            }

            if (IsolatedStorageSettings.ApplicationSettings.Contains("mapImages"))
            {
                String mapImageString = IsolatedStorageSettings.ApplicationSettings["mapImages"] as String;
                Debug.WriteLine("An image has found.");

                DataContractSerializer serializer = new DataContractSerializer(typeof(List<MapImage>));
                MemoryStream ms = new MemoryStream(Encoding.UTF8.GetBytes(mapImageString));
                this.mapImages = serializer.ReadObject(ms) as List<MapImage>;
                ms.Close();

                Debug.WriteLine("Number " + mapImages.Count);

                /* Draw */
                foreach (MapData mapData in mapImages)
                {
                    mapData.draw(mainMap);
                }
            }
        }


        public void resetData() {
            IsolatedStorageSettings.ApplicationSettings.Remove("mapLines");
            IsolatedStorageSettings.ApplicationSettings.Remove("mapVideos");
            IsolatedStorageSettings.ApplicationSettings.Remove("mapImages");


            Debug.WriteLine("Deleted datas");
        }
    }
}
