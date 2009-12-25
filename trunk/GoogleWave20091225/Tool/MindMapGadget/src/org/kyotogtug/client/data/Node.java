package org.kyotogtug.client.data;

import java.util.ArrayList;
import java.util.List;



/**
 * マインドマップのノードを表すクラス
 * @author Kenji
 *
 */
public class Node {
	
	/** ノードID */
	private int nodeId;
	
	/**ノードの親ノードのEdge*/
	private Edge parent;
	
	/**ノードの子ノードのEdge*/
	private List<Edge> children;
	
	/** ノードのテキスト */
	private String text;
	
	/**ノードに対応するBlipのID*/
	private String blipId;
	/**ノードの座標 X*/
	private int x;
	/**ノードの座標 X*/
	private int y;
	
	/**-ノードの幅*/
	private int width;
	/**ノードの高さ*/
	private int height;
	
	public Node(){
		children = new ArrayList<Edge>();
	}
	
	
	/**
	 * 親のノードを取得する。
	 * 親のノードが存在しない場合はnullを返す。
	 * @return
	 */
	public Node getParentNode(){
		if( parent == null ){
			return null;
		}else{
			Node parentNode =  parent.getFromNode();
			return parentNode ;
		}
	}
	
	/**
	 * 子のノードのリストを取得する。
	 * 子のノードが存在しない場合は、サイズが0のリストを返す。
	 * @return
	 */
	public List<Node> getChildrenNode(){
		List<Node> childrenNode = new ArrayList<Node>();
		for( Edge edge : children){
			childrenNode.add(edge.getToNode());
		}
		return childrenNode;
	}
	
	/**
	 * 子のノードを追加する。
	 * Edgeは自動で生成され設定される。
	 * @param node
	 */
	public void addChildNode( Node node ){
		Edge edge = new Edge();
		edge.setFromNode(this);
		edge.setToNode(node);
		children.add(edge);
		node.setParent(edge);
	}
	
//	/**
//	 * 親ノードを設定する。
//	 * 
//	 * @param parent
//	 */
//	public void setParentNode( Node parentNode ){
//		
//		// すでに親ノードへのリンクがある場合は削除する
//		if( parent != null ){
//			
//		}
//		
//		
//		Edge edge = new Edge();
//		edge.setFromNode(this);
//		edge.setToNode(node);
//	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public Edge getParent() {
		return parent;
	}
	public void setParent(Edge parent) {
		this.parent = parent;
	}
	public List<Edge> getChildren() {
		return children;
	}
	public void setChildren(List<Edge> children) {
		this.children = children;
	}
	public String getBlipId() {
		return blipId;
	}
	public void setBlipId(String blipId) {
		this.blipId = blipId;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
}
