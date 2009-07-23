package org.kyoto_gtug.gae.jdo.client;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.PieChart;

public class BigTable implements EntryPoint {

    private final BigTableServiceAsync greetingService = GWT.create(BigTableService.class);
    private Widget widget = new Label("データを表示します");

    public void onModuleLoad() {
        final TextBox tbName = new TextBox();
        tbName.setText("蕎麦");

        final TextBox tbData = new TextBox();
        tbData.setText("10");

        final Button btnStore = new Button("保存");

        final DialogBox dialogBox = new DialogBox();
        dialogBox.setText("結果");
        dialogBox.setAnimationEnabled(true);
        final Button btnClose = new Button("閉じる");
        btnClose.getElement().setId("closeButton");
        final Label lblServerResponse = new Label();
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(lblServerResponse);
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.add(btnClose);
        dialogBox.setWidget(dialogVPanel);

        btnClose.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
                btnStore.setEnabled(true);
                btnStore.setFocus(true);
            }
        });

        btnStore.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                btnStore.setEnabled(false);
                btnStore.setFocus(false);
                int data = Integer.parseInt(tbData.getText());
                greetingService.store(tbName.getText(), data, new AsyncCallback() {

                    public void onSuccess(Object result) {
                        lblServerResponse.setText("成功");
                        dialogBox.center();
                    }

                    public void onFailure(Throwable caught) {
                        lblServerResponse.setText("失敗");
                        caught.printStackTrace();
                    }
                });
            }
        });

        final AsyncCallback<List<ChartData>> callback = new AsyncCallback<List<ChartData>>() {

            public void onSuccess(List<ChartData> result) {
                HashMap<String, Integer> map = new HashMap<String, Integer>();
                for (ChartData random : result) {
                    String name = random.getName();
                    Integer num = map.get(name);
                    if (num == null) {
                        num = new Integer(0);
                    }
                    num += random.getNum();
                    map.put(name, num);
                }
                DataTable data = DataTable.create();
                data.addColumn(ColumnType.STRING, "名前");
                data.addColumn(ColumnType.NUMBER, "数");

                data.addRows(map.size());
                int count = 0;
                for (String name : map.keySet()) {
                    data.setValue(count, 0, name);
                    data.setValue(count, 1, map.get(name));
                    count++;
                }

                PieChart.Options options = PieChart.Options.create();
                options.setWidth(400);
                options.setHeight(240);
                options.set3D(true);
                options.setTitle("GAEのデータ");
                RootPanel.get("chartContainer").remove(widget);
                widget = new PieChart(data, options);
                RootPanel.get("chartContainer").add(widget);
            }

            public void onFailure(Throwable caught) {

            }
        };

        final Button btnGet = new Button("データの表示");
        btnGet.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                greetingService.getList("", callback);
            }
        });

        HorizontalPanel panel = new HorizontalPanel();
        panel.add(tbName);
        panel.add(tbData);
        panel.add(btnStore);

        VerticalPanel vpanel = new VerticalPanel();
        vpanel.add(panel);
        vpanel.add(btnGet);
        RootPanel.get("container").add(vpanel);
        RootPanel.get("chartContainer").add(widget);
    }
}
