package org.kyoto_gtug.gae.jdo.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ChartData implements IsSerializable {

    private String name;
    private Integer num;

    public ChartData() {
    }

    public ChartData(String name, Integer num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}