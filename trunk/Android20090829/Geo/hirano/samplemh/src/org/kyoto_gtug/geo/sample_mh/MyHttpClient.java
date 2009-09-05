/*
   Copyright 2009 adamrocker

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

// MyHttpClient.java
package org.kyoto_gtug.geo.sample_mh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;

import static org.kyoto_gtug.geo.sample_mh.GeoSampleMHActivity.LOG_TAG;

public class MyHttpClient extends DefaultHttpClient {

    private HttpGet httpGet;

    public MyHttpClient() {
        Log.i(LOG_TAG, "constructor MyHttpClient");
        httpGet = new HttpGet();
    }

    public InputStream getInputStreamOnWeb(String uri) {
        Log.i(LOG_TAG, "getInputStreamOnWeb");

        try {
            httpGet.setURI(new URI(uri));
        } catch (URISyntaxException e1) {
            Log.e(LOG_TAG, "URISyntaxException");
            e1.printStackTrace();
            return null;
        }

        HttpResponse response = null;
        try {
            response = execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            Log.i(LOG_TAG, "Status Code: " + statusCode);
            if (200 <= statusCode && statusCode < 300) {
                // success!!
                return response.getEntity().getContent();
            }
        } catch (ClientProtocolException e) {
            Log.e(LOG_TAG, "ClientProtocolException");
            e.printStackTrace();
        } catch (IllegalStateException e) {
            Log.e(LOG_TAG, "IllegalStateException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException");
            e.printStackTrace();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception");
            e.printStackTrace();
        }

        return null;
    }

    public String getStringOnWeb(String uri) {
        Log.i(LOG_TAG, "getStringOnWeb");
        InputStream is = getInputStreamOnWeb(uri);
        if(is == null){
            return null;
        }

        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer buffer = new StringBuffer();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException");
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }
}
