package org.kyotogtug.client.data;
/**
 * マインドマップのノードとノードを繋ぐエッジを表すクラス
 * @author Kenji
 *
 */
public class Edge {
	/**EdgeのID*/
	private int id;
	
	/**Edgeの開始ノード*/
	private Node fromNode;
	

	/**Edgeの終了ノード*/
	private Node toNode;
	
	/**Edgeの色*/
	private String color;
	
	public Edge(){
		
	}

//	/**
//	 * 
//	 * @param fromNode
//	 * @param toNode
//	 */
//	public Edge( Node fromNode , Node toNode ){
//		setFromNode(fromNode);
//		setToNode(toNode);
//		fromNode.
//	}
	
	/**
	 * リンク関係を削除する
	 */
	public void dispose(){
		
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Node getFromNode() {
		return fromNode;
	}

	public void setFromNode(Node fromNode) {
		this.fromNode = fromNode;
	}

	public Node getToNode() {
		return toNode;
	}

	public void setToNode(Node toNode) {
		this.toNode = toNode;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
