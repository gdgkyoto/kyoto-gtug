package org.kyotogtug.client;

import gwt.canvas.client.Canvas;

import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

@com.google.gwt.gadgets.client.Gadget.ModulePrefs(title = "SimpleGadget", author = "yournamehere", author_email = "yournamehere@gmail.com")
public class MindMapGadget extends Gadget<UserPreferences> {
	
	/** マインドマップを描画するCanvas */
	private Canvas canvas;
	
	/** 新しいノードのタイトルを入力するためのテキストボックス */
	private TextBox nodeTitleTextBox;
	
	/** 投稿ボタン */
	private Button submitButton;
	
	/** ノードの削除ボタン */
	private Button deleteButton;
	
	@Override
	protected void init(UserPreferences preferences) {
		 VerticalPanel vpanel = new VerticalPanel();
		 HorizontalPanel hvpanel = new HorizontalPanel();
		 
		 canvas = new Canvas();
		 nodeTitleTextBox = new TextBox();
		 submitButton = new Button();
		 deleteButton = new Button();
		 
		 submitButton.setText("Submit");
		 deleteButton.setText("Delete");
		 
		 vpanel.add(canvas);
		 vpanel.add(hvpanel);
		 hvpanel.add(nodeTitleTextBox);
		 hvpanel.add(submitButton);
		 hvpanel.add(deleteButton);
		 
		 RootPanel.get().add(vpanel);
	}

}
