package phone.app;

/**
 * 電話関連処理イベントリスナ
 * RotaryDialViewに設定して、RotaryDialViewの変化をRotaryDialActivityに通知する。
 * @author KENJI
 *
 */
public interface DialEventListener {
	/** コンタクトリストを開く */
	public void openContactListView();
	/** ダイヤル画面を開く */
	public void openDialView();
}
