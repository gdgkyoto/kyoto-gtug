package org.kyoto_gtug.gae.jdo.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BigTableServiceAsync {
    void store(String name, Integer data, AsyncCallback callback);

    void getList(String name, AsyncCallback<List<ChartData>> callback);
}
