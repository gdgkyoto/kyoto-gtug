package org.kyoto_gtug.gae.jdo.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("datastore")
public interface BigTableService extends RemoteService {
    void store(String name, Integer data);

    List<ChartData> getList(String name);
}
