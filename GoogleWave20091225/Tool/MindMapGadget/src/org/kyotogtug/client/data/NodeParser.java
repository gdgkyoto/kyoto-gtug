package org.kyotogtug.client.data;

import java.util.LinkedList;
import java.util.List;
import org.cobogw.gwt.waveapi.gadget.client.State;



/**
 * ノードの永続化を行うクラス
 * 
 * このクラスの変更は北村が担当します。
 * 
 * @author Kenji
 *
 */
public class NodeParser {
	
	/** マインドマップのノード情報すべてをテキスト化した情報 */
	public static final String SHARED_STATE_DATA = "DATA";
	
	/**
	 * SharedStateにマインドマップのデータを保存する。
	 * 本来はここに実装すべきではないけれど、作業分担を考慮してここに配置。
	 * @param state
	 */
	public void saveToSharedState( State state , Node rootNode ){
		String text = toString(rootNode);
		state.submitValue(SHARED_STATE_DATA, text);
	}
	
	/**
	 * SharedStateからノードの情報を読み込み、パースしてルートのNodeインスタンスを生成する。
	 * @param state
	 * @return
	 */
	public Node getRootNodeFromSharedState( State state ){
		String text = state.get(SHARED_STATE_DATA);
		Node rootNode = parseNode(text);
		return rootNode;
	}
	
	
	/**
	 * ノードのテキスト形式をノードインスタンスに変換する
	 * @param nodetext
	 * @return
	 */
	public Node parseNode( String nodetext ){
		
		// 文字列を1行ずつのListにする
		String[] split = nodetext.split("\n");
		List<String> lines = new LinkedList<String>();
		for( String line : split ){
			lines.add(line);
		}
		
		Node rootNode = parseNodeRecursive(null, lines);
		
		return rootNode;
	}
	
	/**
	 * 再帰でノードをインスタンスに復元
	 * @param parentNode
	 * @param lines
	 * @return
	 */
	public Node parseNodeRecursive( Node parentNode , List<String> lines ){
		
		// データの終端
		if( lines.size() == 0 ){
			return null;
		}
		
		// 先頭行取りだし
		String line = lines.get(0);
		lines.remove(0);

		// ノードの終端であれば終わる
		if( line.trim().equals("]")){
			return null;
		}
	
		// ノードをパース
		Node node = parseNodeLine(line);
		if( node == null ){
			return null;
		}
		
		// 子のノードのパース
		while(true){
			Node childNode = parseNodeRecursive(node, lines);
			// 子のノードが存在しない場合は終了
			if( childNode == null ){
				break;
			}
			node.addChildNode(childNode);
		}
		return node;
	}
	
	
	/**
	 * 引数のStringの1行目を削除したStringを返す。
	 * 削除する前に1行詩かデータが存在しない場合はnullを返す。
	 * @param text
	 * @return
	 */
	private String nextLine( String text ){
		int index = text.indexOf("\n");
		if( index != -1 ){
			String nextLine = text.substring(index + 1);
			return nextLine;
		}
		return null;
	}
	
	
	/**
	 * ノードのテキスト形式の1行を変換する
	 * @param line
	 * @return
	 */
	public Node parseNodeLine( String line ){
		line = line.replace("[", "");
		String[] split = line.split(":");
		Node node = null;
		if( split.length >= 7 ){
			int i = 0;
			node = new Node();
			node.setNodeId(Integer.parseInt(split[i++]));
			node.setText(split[i++]);
			node.setBlipId(split[i++]);
			node.setX(Integer.parseInt(split[i++]));
			node.setY(Integer.parseInt(split[i++]));
			node.setWidth(Integer.parseInt(split[i++]));
			node.setHeight(Integer.parseInt(split[i++]));
		}
		return node;
	}
	
	
	/**
	 * ノードをテキスト形式に変換する。
	 * [id:title:x:y:width:height
	 *   [id:title:x:y:width:height
	 *   ]
	 *   [id:title:x:y:width:height
	 *     [id:title:x:y:width:height
	 *     ]
	 *   ]
	 * ]
	 * @param rootNode
	 * @return
	 * @throws XMLStreamException 
	 */
	public String toString(Node rootNode) {
		
		StringBuilder sb = new StringBuilder();
		addItem(sb, rootNode , 0 );
		return sb.toString();
	}
	
	/**
	 * ノードのテキスト表現を取得する。
	 * @param sb
	 * @param node
	 * @param depth
	 */
	private void addItem( StringBuilder sb , Node node , int depth ){
		if( node == null ){
			return;
		}
		sb.append("[");
		sb.append(node.getNodeId());
		sb.append(":");
		sb.append(node.getText());
		sb.append(":");
		sb.append(node.getBlipId());
		sb.append(":");
		sb.append(node.getX());
		sb.append(":");
		sb.append(node.getY());
		sb.append(":");
		sb.append(node.getWidth());
		sb.append(":");
		sb.append(node.getHeight());
		sb.append("\n");
		
		for( Node child : node.getChildrenNode() ){
			addItem(sb, child, depth+1);
		}
		sb.append("]\n");
	}
	

}
