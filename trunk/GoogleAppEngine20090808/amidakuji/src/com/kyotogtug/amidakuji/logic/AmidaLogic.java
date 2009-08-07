package com.kyotogtug.amidakuji.logic;

import java.util.*;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Link;
import com.google.appengine.api.users.User;


/**
 * アミダくじのロジックを実装するクラスです。<br>
 * このクラスのインスタンスは、AmidaLogicFactoryクラスのget()メソッドで取得してください。
 * @author R.Takahashi
 */
public final class AmidaLogic {

	//ロガー
	private static final Logger log = Logger.getLogger(AmidaLogic.class.getName());




	//-------------------- 変数 --------------------

	//初期化フラグ
	private boolean initialized = false;

	//あみだくじID
	private long id;

	//あみだくじの固定情報
	private AmidaFixedStatus fixedStatus = null;

	//あみだくじの可変情報
	private AmidaVariableStatus variableStatus = null;

	//デバッグ用
	private Date endTimeForDebug;


	//-------------------- メソッド --------------------

	/**
	 * コンストラクタ
	 * @param id - あみだくじID
	 */
	AmidaLogic(long id){
		this.id = id;

		//デバッグ用
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MILLISECOND, (int)AmidaConfig.TOTAL_TIME);
		endTimeForDebug = cal.getTime();
	}

	/**
	 * IDを取得します。
	 * @return id - あみだくじID
	 */
	long getId(){
		return this.id;
	}


	/**
	 * あみだくじの状態を返します<br>
	 * このメソッドはスレッド同期されています。<br>
	 * 先に、initialize()メソッドで初期化を行う必要があります。
	 */
	public synchronized AmidaStatus getStatus(){

		//初期化されていない場合
		if(!initialized) throw new IllegalStateException("ロジックオブジェクトは初期化されていません");

		//更新処理
		refresh();

		//アミダくじの状態を表す
		AmidaStatus status = new AmidaStatus(fixedStatus, variableStatus);

		//状態を返す
		return status;
	}


	/**
	 * あみだくじに横線の挿入を行います<br>
	 * 入力不可能な場合、例外を投げます。<br>
	 * このメソッドはスレッド同期されています。<br>
	 * 先に、initialize()メソッドで初期化を行う必要があります。
	 * @param x - 線の左上のX座標 (0≦X＜参加人数)
	 * @param y - 線の左上のY座標 (0＜Y＜AMIDA_LENGTH+AMIDA_LAST_LENGTH)
	 */
	public synchronized boolean insertLine(int x, int y){

		//初期化されていない場合
		if(!initialized)  throw new IllegalStateException("ロジックオブジェクトは初期化されていません");

		//Y座標チェック
		if(y<=0 || y>=AmidaConfig.AMIDA_LENGTH + AmidaConfig.AMIDA_LAST_LENGTH)
			throw new IllegalArgumentException("y座標が不正です : y=" + y);

		//X座標チェック
		if(false) throw new IllegalArgumentException("x座標が不正です : x=" + x);

		//更新処理
		refresh();

		//指定されたy座標が現在のy座標より大きい場合、許可される
		if(variableStatus.getCurrentPositionY()>=y){
			return true;
		}

		return false;
	}

	/**
	 * オブジェクトの初期化を行います
	 */
	public synchronized void initialize(){

		if(initialized) return;

		//固定情報を作成
		AmidaFixedStatus fixedStatus = new AmidaFixedStatus();
		fixedStatus.setId(id);
		fixedStatus.setTitle("test");
		fixedStatus.setUrlList(new ArrayList<Link>());
		fixedStatus.setUserList(new ArrayList<User>());
		fixedStatus.setEndTime(endTimeForDebug);
		this.fixedStatus = fixedStatus;
		log.fine("固定情報の初期化が行われました。");

		//線履歴をDBから取り出す
		AmidaVariableStatus variableStatus = new AmidaVariableStatus();
		variableStatus.setCurrentPositionY(-1); //Y座標は-1
		variableStatus.setFinished(false); //finishedフラグはfalseにしておく
		variableStatus.lock(); //オブジェクトロック
		this.variableStatus = variableStatus;
		log.fine("可変情報の初期化が行われました。");

		initialized = true;
	}


	/**
	 * 現在情報を作成します
	 */
	private void refresh(){

		AmidaVariableStatus preVariableStatus = variableStatus;
		AmidaVariableStatus newVariableStatus = new AmidaVariableStatus();

		//データをコピーする(現在時刻以外)
		Date currentTime = new Date();
		newVariableStatus.setCurrentTime(currentTime);
		newVariableStatus.setCurrentPositionY(preVariableStatus.getCurrentPositionY());
		newVariableStatus.setCurrentsPositionX(preVariableStatus.getCurrentPositionX());
		newVariableStatus.setFinished(preVariableStatus.isFinished());

		//すでに前の段階で終わっている場合はそのまま返す。
		if(preVariableStatus.isFinished()){
			newVariableStatus.lock();
			this.variableStatus = newVariableStatus;
			log.fine("すでに終了しているため、状態は更新されませんでした。");
			return;
		}

		//可変情報を更新する。
		//更新順序は、Y座標、線履歴、X座標の順(未実装)
		//線履歴が更新された場合、結果をDBに保存する

		//終了判定
		long leftTime = fixedStatus.getEndTime().getTime() - currentTime.getTime(); //残り時間
		if(leftTime<0) leftTime=0;
		newVariableStatus.setFinished((leftTime>0) ? false : true);

		//Y座標を計算
		long elapsedTime = AmidaConfig.TOTAL_TIME - leftTime; //経過時間
		int y = (leftTime>0) ? (int)((long)elapsedTime / AmidaConfig.TIME_TO_MOVE) : AmidaConfig.AMIDA_LENGTH;
		newVariableStatus.setCurrentPositionY(y);

		//Y座標が前と変わらない場合、線履歴、X座標の更新はスキップする
		if(y==preVariableStatus.getCurrentPositionY()){
			newVariableStatus.lock();
			this.variableStatus = newVariableStatus;
			log.fine("Y座標に変化がないため、線履歴とX座標は更新されませんでした : y=" + y);
			return;
		}

		//線履歴を計算
		newVariableStatus.setCurrentsPositionX(new ArrayList<Integer>());


		//オブジェクトをロック
		newVariableStatus.lock();
		this.variableStatus = newVariableStatus;
		log.fine("あみだくじの状態が更新されました : y=" + y);

	}



	/**
	 * 線履歴をDBから取り出します(未実装)
	 */
	/*
	private List<Line> restoreLineHistory(){

	}
	*/


}



