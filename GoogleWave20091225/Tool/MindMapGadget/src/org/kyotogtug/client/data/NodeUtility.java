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
	
	/**
	 * 新しいノードIDを生成する。
	 * すべてのノードの中で最大のノードIDに+1した値を返す。
	 * 
	 * @param rootNode ルートノード
	 * @return
	 */
	public static int nextNodeId( Node rootNode ){
		int INIT_ID = -99999;
		List<Node> nodeList = getAllNodeList(rootNode);
		int maxId = INIT_ID;
		for( Node node : nodeList ){
			if( node.getNodeId() > maxId ){
				maxId = node.getNodeId();
			}
		}
		// 新しいノードIDの生成
		return maxId + 1;
	}

}
