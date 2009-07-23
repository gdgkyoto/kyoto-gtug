package org.kyoto_gtug.gae.jdo.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import org.kyoto_gtug.gae.jdo.client.BigTableService;
import org.kyoto_gtug.gae.jdo.client.ChartData;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class BigTableServiceImpl extends RemoteServiceServlet implements BigTableService {

    private Logger logger = Logger.getLogger(BigTableServiceImpl.class.toString());

    @Override
    public void store(String name, Integer data) {
        StoreData storeData = new StoreData(name, data);
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(storeData);
        } finally {
            pm.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<ChartData> getList(String name) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        String query = "select from " + StoreData.class.getName();
        logger.info("query = " + query);
        List<StoreData> list = (List<StoreData>) pm.newQuery(query).execute();
        ArrayList<ChartData> listChartData = new ArrayList<ChartData>();
        for (StoreData storedData : list) {
            String nameRandom = storedData.getName();
            logger.info("name = " + nameRandom);
            Integer numRandom = storedData.getData();
            logger.info("num  = " + numRandom);
            listChartData.add(new ChartData(nameRandom, numRandom));
        }
        return listChartData;
    }

}
