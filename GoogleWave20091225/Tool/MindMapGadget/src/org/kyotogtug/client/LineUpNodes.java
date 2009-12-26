/**
 * 
 */
package org.kyotogtug.client;

import java.util.List;

import org.kyotogtug.client.data.Node;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * ノード整列クラス
 * @author Yuki
 *
 */
public class LineUpNodes {
	
	private int NODE_HORIZONTAL_MARGIN = 25;
	private int NODE_VERTICAL_MARGIN = 10;
	private int RIGHT_BRUNCH = 1;
	private int LEFT_BRUNCH = -1;
	
	/**
	 * ログを実行する
	 * @param gadgetForDebug
	 * @param msg
	 */
	private void log(MindMapGadget gadgetForDebug , String msg){
		if(gadgetForDebug != null){
			gadgetForDebug.log(msg);
		}
	}
	
	/**
	 * ノードを整列させる
	 * @param root 整列させるルートノード
	 * @param gadgetForDebug デバッグ用のGadget(実環境実行時にはnullを指定して下さい)
	 */
	public void lineUp(Node root , MindMapGadget gadgetForDebug){
		int mindMapWidth = root.getWidth() + getBrunchMaxWidth(root , LEFT_BRUNCH , gadgetForDebug);
		
		log(gadgetForDebug , "左ブランチ幅 :" + mindMapWidth);
		log(gadgetForDebug , "右ブランチ幅 :" + getBrunchMaxWidth(root , RIGHT_BRUNCH , gadgetForDebug));
		
		lineUp(root , mindMapWidth , 0 , 0 , LEFT_BRUNCH , gadgetForDebug);
	}
	
	/**
	 * 片側のノードの最大幅を取得する
	 * @param node
	 * @param orientation
	 * @return
	 */
	private int getBrunchMaxWidth(Node node , int orientation , MindMapGadget gadgetForDebug){
		int maxWidth = 0;
		int isBrunch;
		if(orientation == RIGHT_BRUNCH) isBrunch = 1;
		else isBrunch = 0;
		
		List<Node> children = node.getChildrenNode();
		for(Node child : children){
			if(isBrunch == 1){
				int nodeWidth = child.getWidth() + NODE_HORIZONTAL_MARGIN + getChildWidth(child);
				if(nodeWidth > maxWidth) maxWidth = nodeWidth;
			}
			
			isBrunch = 1 - isBrunch;
		}
		
		return maxWidth;
	}
	
	/**
	 * ノードを整列させる(内部利用)
	 * @param node 整列させるノード
	 * @param level 現在のレベル
	 */
	public void lineUp(Node node , int nodeX, int nodeY,int level , int orientation , MindMapGadget gadgetForDebug){
		/*Nodeのx座標とy座標をFixする*/
		log(gadgetForDebug, "整列中 : " + node.getText() + " " + nodeX + " " + nodeY);
		node.setX(nodeX);
		node.setY(nodeY + (getChildHeight(node) / 2));
		
		/* 子ノードがある場合、子ノードの位置を計算して与える */
		List<Node> children = node.getChildrenNode();

		int nextX = 0, nextY = nodeY;
		if(children.size() > 0){
			for(Node child : children){
				if(level == 0) orientation = orientation * (-1);
				
				int nodeHeight = getChildHeight(child);
				int alphaX = node.getWidth() + NODE_HORIZONTAL_MARGIN;
				
				nextX = nodeX + (alphaX * orientation);
				
				lineUp(child, nextX, nextY, level+1, orientation , gadgetForDebug);
				
				nextY += nodeHeight;
			}
		}
	}
	
	/**
	 * 子ノードの幅を取得する
	 * @param node
	 */
	public int getChildWidth(Node node){
		/*子ノードの幅の最大値を取得する*/
		int maxChildWidth = 0;
		List<Node> children = node.getChildrenNode();
		
		if(children.size() != 0){
			for(Node child : children){
				int childWidth = getChildWidth(child);
				if(childWidth > maxChildWidth) maxChildWidth = childWidth;
			}
		}
		
		/*自ノードの幅にマージンと子ノードの幅を加算して返す*/
		return NODE_HORIZONTAL_MARGIN + node.getWidth() + maxChildWidth;
	}
	
	/**
	 * 子ノードの高さを取得する
	 * @param node 高さを取得するノード
	 */
	public int getChildHeight(Node node){
		List<Node> children = node.getChildrenNode();
		if(children.size() == 0){
			/*自ノードの高さにマージンを加算して返す*/
			return node.getHeight() + NODE_VERTICAL_MARGIN;
		}
		else{
			/*子ノードの高さの合計を取得する*/
			int sumChildHeight = 0;
			for(Node child : children){
				sumChildHeight += getChildHeight(child);
			}
			return sumChildHeight;
		}
	}
	
	/**
	 * ノードの幅を計算してセットする
	 * @param root
	 */
	public void measureNodeSizes(Node root){
		if(root.getWidth() == -1){
			/*幅を計算して設定*/
			int nodeWidth = root.getText().length() * 8;
			if(nodeWidth < 10) nodeWidth = 10;
			
			root.setWidth(nodeWidth);
		}
		if(root.getHeight() == -1){
			/*高さを計算して設定*/
			root.setHeight(30);
		}
		
		List<Node> children = root.getChildrenNode();
		if(children.size() != 0){
			for(Node child : children){
				measureNodeSizes(child);
			}
		}
	}
}
