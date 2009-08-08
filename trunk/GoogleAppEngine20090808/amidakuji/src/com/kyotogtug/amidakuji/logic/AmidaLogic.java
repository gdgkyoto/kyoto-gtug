package com.kyotogtug.amidakuji.logic;

import java.util.*;
import java.util.logging.Logger;

import com.kyotogtug.amidakuji.dao.*;
import com.kyotogtug.amidakuji.dao.impl.AmidakujiDaoImpl;
import com.kyotogtug.amidakuji.dao.impl.LineDaoImpl;
import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;
import com.kyotogtug.amidakuji.jdo.entity.Line;

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



	//-------------------- メソッド --------------------

	/**
	 * コンストラクタ
	 * @param id - あみだくじID
	 */
	AmidaLogic(long id){
		this.id = id;
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
	 * @param y - 線の左上のY座標 (0＜Y＜AMIDA_LENGTH)
	 */
	public synchronized boolean insertLine(int x, int y){

		//初期化されていない場合
		if(!initialized)  throw new IllegalStateException("ロジックオブジェクトは初期化されていません");

		//Y座標チェック
		if(y<=0 || y>=AmidaConfig.AMIDA_LENGTH)
			throw new IllegalArgumentException("y座標が不正です : y=" + y);

		//X座標チェック
		if(false) throw new IllegalArgumentException("x座標が不正です : x=" + x);

		//更新処理
		refresh();

		//指定されたy座標が現在のy座標より大きい場合、許可される
		if(variableStatus.getCurrentPositionY()>=y){
			Line line = new Line();
			line.setCreateUser(null);
			line.setCreateTime(new Date());
			line.setXPoint(x);
			line.setYPoint(y);

			ILineDao dao = new LineDaoImpl();
			dao.insertLine(id, new Line());
			return true;
		}

		return false;
	}

	/**
	 * オブジェクトの初期化を行います
	 */
	public synchronized void initialize(){

		if(initialized) return;

		//データ取得
		IAmidakujiDao dao = new AmidakujiDaoImpl();
		Amidakuji amidakuji = dao.getAmidakujiById(this.id);
		if(amidakuji==null) throw new IllegalArgumentException("Amidakujiオブジェクト取得に失敗 : id=" + id);


		//固定情報を作成
		AmidaFixedStatus fixedStatus = new AmidaFixedStatus();
		fixedStatus.setId(id);
		fixedStatus.setTitle(amidakuji.getTitle());
		fixedStatus.setUrlList(amidakuji.getImageUrlList());
		fixedStatus.setUserList(amidakuji.getMailAddressList());
		fixedStatus.setEndTime(amidakuji.getEndTime());
		this.fixedStatus = fixedStatus;
		log.fine("固定情報の初期化が行われました。");

		//可変情報を作成
		AmidaVariableStatus variableStatus = new AmidaVariableStatus();
		variableStatus.setCurrentPositionY(-1); //Y座標は-1
		variableStatus.setFinished(false); //finishedフラグはfalseにしておく
		variableStatus.setLineList(null);
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
		newVariableStatus.setCurrentPosition(preVariableStatus.getCurrentPosition());
		newVariableStatus.setFinished(preVariableStatus.isFinished());
		newVariableStatus.setLineList(preVariableStatus.getLineList());

		//すでに前の段階で終わっている場合はそのまま返す。
		if(preVariableStatus.isFinished()){
			newVariableStatus.lock();
			this.variableStatus = newVariableStatus;
			log.fine("すでに終了しているため、状態は更新されませんでした。");
			return;
		}


		//終了判定
		long leftTime = fixedStatus.getEndTime().getTime() - currentTime.getTime(); //残り時間
		if(leftTime<0) leftTime=0;
		newVariableStatus.setFinished((leftTime>0) ? false : true);

		//Y座標を計算
		long elapsedTime = AmidaConfig.TOTAL_TIME - leftTime; //経過時間
		if (elapsedTime <0) elapsedTime=0;
		int y = (leftTime>0) ? (int)((long)elapsedTime / AmidaConfig.TIME_TO_MOVE) : AmidaConfig.AMIDA_LENGTH;
		newVariableStatus.setCurrentPositionY(y);

		//Y座標が前と変わらない場合、線履歴、X座標の更新はスキップする
		if(y==preVariableStatus.getCurrentPositionY()){
			newVariableStatus.lock();
			this.variableStatus = newVariableStatus;
			log.fine("Y座標に変化がないため、線履歴とX座標は更新されませんでした : y=" + y);
			return;
		}

		//現在のX座標を計算
		int xsize = fixedStatus.getUserList().size();
		Integer[] xpos = new Integer[xsize];
		for(int i=0; i<xsize; i++){
			xpos[i]=i;
		}

		//lineList取得
		IAmidakujiDao dao = new AmidakujiDaoImpl();
		Amidakuji amida = dao.getAmidakujiById(id);
		List<Line> lineList = amida.getLineList();
		log.info("line count=" + lineList.size());
		List<Line> sortList = new ArrayList<Line>(lineList);

		//lineList2をy座標順にソートする
		int n = sortList.size();
		for(int i=0;i<n;i++){
		    int min = i ;
		    for(int j=i+1;j<n;j++){
		    	if(sortList.get(min).getYPoint()>sortList.get(j).getYPoint())  min = j ;
		    }
		    //スワップ
		    Line a = sortList.get(i);
		    Line b = sortList.get(min);
		    sortList.set(min, a);
		    sortList.set(i, b);
		}


		//スワップ
		for(Line line: sortList){
			System.out.println(line.getXPoint() + "," + line.getYPoint());
			int x=line.getXPoint();
			int tmp = xpos[x];
			xpos[x] = xpos[x+1];
			xpos[x+1] = tmp;
		}

		//線リストから生成
		List<List<Object>> position = new ArrayList<List<Object>>();
		for(int i=0; i<xpos.length; i++){
			List<Object> plist = new ArrayList<Object>();
			plist.add(Integer.valueOf(xpos[i]));
			plist.add(Integer.valueOf(y));
			plist.add(fixedStatus.getUserList().get(i));
			position.add(plist);
		}

		//線リスト作成
		List<List<Object>> lineListOutput = new ArrayList<List<Object>>();
		for(Line line : lineList){
			List<Object>e  = new ArrayList<Object>();
			e.add(line.getXPoint());
			e.add(line.getYPoint());
			if(line.getCreateUser()!=null){
				e.add(line.getCreateUser().getEmail());
			}
			else{
				e.add(null);
			}
			lineListOutput.add(e);
		}

		//結果をセットする
		newVariableStatus.setLineList(lineListOutput);
		newVariableStatus.setCurrentPosition(position);

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



