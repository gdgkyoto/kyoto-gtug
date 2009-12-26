package org.kyotogtug.client.data;

import java.util.ArrayList;
import java.util.List;

public class NodeUtility {
	
	/**
	 * ルートノードを指定すると、すべてのノードのリストに変換する。
	 * 
	 * @param rootNode
	 * @return
	 */
	public static List<Node> getAllNodeList( Node rootNode ){
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.add(rootNode);
		appendList(nodeList, rootNode);
		return nodeList;
	}
	
	/**
	 * getAllNodeListから再帰的にすべてのノードを探索し、引数のlistにaddするメソッド
	 * @param list
	 * @param node
	 */
	private static void appendList( List<Node> list , Node node ){
		for( Node child : node.getChildrenNode() ){
			list.add(child);
			appendList(list, child);
		}
	}

}
