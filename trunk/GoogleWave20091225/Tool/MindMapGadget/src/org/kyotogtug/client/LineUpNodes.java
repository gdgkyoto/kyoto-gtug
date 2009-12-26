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
	
	private int NODE_HORIZONTAL_MARGIN = 10;
	private int NODE_VERTICAL_MARGIN = 10;
	private int RIGHT_BRUNCH = 1;
	private int LEFT_BRUNCH = -1;
	
	/**
	 * ノードを整列させる
	 * @param root 整列させるルートノード
	 */
	public void lineUp(Node root , MindMapGadget gadgetForDebug){
		lineUp(root , 0 , 0 , 0 , LEFT_BRUNCH , gadgetForDebug);
	}
	
	/**
	 * ノードを整列させる(内部利用)
	 * @param node 整列させるノード
	 * @param level 現在のレベル
	 */
	public void lineUp(Node node , int nodeX, int nodeY,int level , int orientation , MindMapGadget gadgetForDebug){
		/*Nodeのx座標とy座標をFixする*/
		gadgetForDebug.log("整列中 : " + node.getText() + " " + nodeX + " " + nodeY);
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
		List<Node> children = root.getChildrenNode();
		if(children.size() != 0){
			for(Node child : children){
				if(child.getWidth() == -1){
					/*幅を計算して設定*/
					child.setWidth(50);
				}
				if(child.getHeight() == -1){
					/*高さを計算して設定*/
					child.setHeight(30);
				}
			}
		}
	}
}
