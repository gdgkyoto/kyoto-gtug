/**
 * jp.ddo.brightgenerous.android.sample.slot
 * Slot.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.slot;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

import jp.ddo.brightgenerous.android.sample.slot.view.LogView;
import jp.ddo.brightgenerous.android.sample.slot.view.SlotView;
import jp.ddo.brightgenerous.android.sample.slot.view.slotcell.ImageCell;
import jp.ddo.brightgenerous.android.sample.slot.view.slotcell.SlotCell;

/**
 * スロットアクティビティクラス。
 * 
 * @version 1.0
 * @author KATOU
 */
public class Slot extends Activity {

	/** デフォルトFPS */
	static private int DEFAULT_FPS = 30;

	/** デフォルトFPV */
	static private int DEFAULT_FPV = 5;

	/** スロットビュー 1 */
	protected SlotView slotView1 = null;

	/** スロットビュー 2 */
	protected SlotView slotView2 = null;

	/** スロットビュー 3 */
	protected SlotView slotView3 = null;

	/** スロット停止ボタン1 */
	protected Button slotButton1 = null;

	/** スロット停止ボタン2 */
	protected Button slotButton2 = null;

	/** スロット停止ボタン3 */
	protected Button slotButton3 = null;

	/** 開始ボタン */
	protected Button startButton = null;

	/** テキストビュー */
	protected TextView textView = null;

	/** ログビュー */
	protected LogView logView = null;

	/** センサーマネージャ */
	protected SensorManager sensorManager = null;

	/** センサー */
	protected Sensor sensor = null;

	/** センサーイベントリスナ */
	protected SensorEventListener sensorEventListener = new SensorEventListener() {

		public void onAccuracyChanged(Sensor inSensor, int inAccuracy) {
		}

		public void onSensorChanged(SensorEvent inEvent) {
			if (inEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {
				if (inEvent.sensor == Slot.this.sensor) {
					int speed = 0;
					int direction = SlotCell.DIRECTION_DOWN;
					if (Slot.this.isOrientationPortrait()) {
						float pitch = inEvent.values[1];
						if (pitch <= -90.0f) {
							pitch = (pitch + 90.0f) * -1.0f;
						} else if (pitch <= 0.0f) {
							pitch += 90.0f;
						} else if (pitch <= 90.0f) {
							direction = SlotCell.DIRECTION_UP;
							pitch = 90.0f - pitch;
						} else {
							direction = SlotCell.DIRECTION_UP;
							pitch -= 90.0f;
						}
						speed = (int) (pitch * pitch * Math.sqrt(pitch) / 400);
						if (speed < 1) {
							speed = 1;
						}
					} else {
						float pitch = inEvent.values[2];
						if (pitch < 0.0f) {
							direction = SlotCell.DIRECTION_UP;
							pitch += 90.0f;
						} else {
							pitch = 90.0f - pitch;
						}
						speed = (int) (pitch * pitch * Math.sqrt(pitch) / 400);
						if (speed < 1) {
							speed = 1;
						}
					}
					Slot.this.slotView1.getCell().setFpv(speed);
					Slot.this.slotView2.getCell().setFpv(speed);
					Slot.this.slotView3.getCell().setFpv(speed);
					Slot.this.slotView1.getCell().setDirection(direction);
					Slot.this.slotView2.getCell().setDirection(direction);
					Slot.this.slotView3.getCell().setDirection(direction);
				}
			}
		}
	};

	/**
	 * コンストラクタ。
	 */
	public Slot() {
	}

	/**
	 * 
	 * 
	 * @param inBundle
	 */
	@Override
	public void onCreate(Bundle inBundle) {
		super.onCreate(inBundle);
		this.setContentView(R.layout.slot);

		this.textView = (TextView) this.findViewById(R.id.textMessage);

		this.slotView1 = (SlotView) this.findViewById(R.id.viewSlot1);
		this.slotView1.setFps(DEFAULT_FPS);
		this.slotView1.setCell(new ImageCell(this.slotView1, DEFAULT_FPV, ImageManager.getInstance().getRandomIds()));
		this.slotView1.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View inV, MotionEvent inEvent) {
				Slot.this.stopSlot1();
				return true;
			}
		});
		this.slotButton1 = (Button) this.findViewById(R.id.buttonSlot1);
		this.slotButton1.setOnClickListener(new OnClickListener() {

			public void onClick(View inV) {
				Slot.this.stopSlot1();
			}
		});

		this.slotView2 = (SlotView) this.findViewById(R.id.viewSlot2);
		this.slotView2.setFps(DEFAULT_FPS);
		this.slotView2.setCell(new ImageCell(this.slotView2, DEFAULT_FPV, ImageManager.getInstance().getRandomIds()));
		this.slotView2.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View inV, MotionEvent inEvent) {
				Slot.this.stopSlot2();
				return true;
			}
		});
		this.slotButton2 = (Button) this.findViewById(R.id.buttonSlot2);
		this.slotButton2.setOnClickListener(new OnClickListener() {

			public void onClick(View inV) {
				Slot.this.stopSlot2();
			}
		});

		this.slotView3 = (SlotView) this.findViewById(R.id.viewSlot3);
		this.slotView3.setFps(DEFAULT_FPS);
		this.slotView3.setCell(new ImageCell(this.slotView3, DEFAULT_FPV, ImageManager.getInstance().getRandomIds()));
		this.slotView3.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View inV, MotionEvent inEvent) {
				Slot.this.stopSlot3();
				return true;
			}
		});
		this.slotButton3 = (Button) this.findViewById(R.id.buttonSlot3);
		this.slotButton3.setOnClickListener(new OnClickListener() {

			public void onClick(View inV) {
				Slot.this.stopSlot3();
			}
		});

		this.startButton = (Button) this.findViewById(R.id.buttonStart);
		this.startButton.setOnClickListener(new OnClickListener() {

			public void onClick(View inV) {
				Slot.this.startSlots();
			}
		});

		this.logView = (LogView) this.findViewById(R.id.logMessage);

		{
			this.sensor = null;
			this.sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
		}

		this.initSlots();
	}

	/**
	 * 
	 */
	@Override
	protected void onStart() {
		super.onStart();

		ImageManager.getInstance().reset(this.getApplicationContext(), 100, 100);

		this.slotView1.getCell().reset();
		this.slotView2.getCell().reset();
		this.slotView3.getCell().reset();
	}

	/**
	 * 
	 */
	@Override
	protected void onStop() {
		this.endSensor();
		super.onStop();
	}

	/**
	 * 
	 * 
	 * @param inMenu
	 * @return 成否
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu inMenu) {
		super.onCreateOptionsMenu(inMenu);

		inMenu.add(0, 0, Menu.NONE, R.string.menu_list);
		return true;
	}

	/**
	 * 
	 * 
	 * @param inMenuItem
	 * @return 成否
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem inMenuItem) {

		Intent intent = new Intent(this.getApplicationContext(), ImageList.class);
		this.startActivity(intent);

		return super.onOptionsItemSelected(inMenuItem);
	}

	/**
	 * 縦かどうかを取得する。
	 * 
	 * @return 縦長かどうか
	 */
	protected boolean isOrientationPortrait() {
		return this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
	}

	/**
	 * センサーを開始する。
	 */
	private void startSensor() {
		if (this.sensor == null) {
			List<Sensor> sensors = this.sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
			if ((sensors != null) && !sensors.isEmpty()) {
				this.sensor = sensors.get(0);
				boolean sensorRegisted = this.sensorManager.registerListener(this.sensorEventListener, this.sensor, SensorManager.SENSOR_DELAY_NORMAL);
				if (!sensorRegisted) {
					this.sensor = null;
				}
			}
		}
	}

	/**
	 * センサーを終了する。
	 */
	private void endSensor() {
		if (this.sensor != null) {
			this.sensorManager.unregisterListener(this.sensorEventListener, this.sensor);
			this.sensor = null;
		}
	}

	/**
	 * ログを追加する。
	 * 
	 * @param inLog ログ
	 */
	protected void addLog(CharSequence inLog) {
		if (this.logView != null) {
			this.logView.addLog(inLog);
		}
	}

	/**
	 * スロットを初期化する。
	 */
	protected void initSlots() {
		this.textView.setText(R.string.title);
		this.startButton.setText(R.string.start);

		this.endSensor();
	}

	/**
	 * スロットを開始する。
	 */
	protected void startSlots() {
		this.textView.setText(R.string.title_sub);
		this.startButton.setText(R.string.start_sub);

		this.slotView1.getCell().setFpv(DEFAULT_FPV);
		this.slotView2.getCell().setFpv(DEFAULT_FPV);
		this.slotView3.getCell().setFpv(DEFAULT_FPV);

		this.slotView1.start();
		this.slotView2.start();
		this.slotView3.start();

		this.startSensor();

		this.checkState();
		this.addLog("-----SLOTを開始-----");
	}

	/**
	 * スロット1を停止する。
	 */
	protected void stopSlot1() {
		if (this.slotView1.isRunning()) {
			this.slotView1.stop();
			int value = this.slotView1.getValue();
			this.checkState();
			this.addLog("SLOT1を停止 : " + value);
			this.checkEnd();
		}
	}

	/**
	 * スロット2を停止する。
	 */
	protected void stopSlot2() {
		if (this.slotView2.isRunning()) {
			this.slotView2.stop();
			int value = this.slotView2.getValue();
			this.checkState();
			this.addLog("SLOT2を停止 : " + value);
			this.checkEnd();
		}
	}

	/**
	 * スロット3を停止する。
	 */
	protected void stopSlot3() {
		if (this.slotView3.isRunning()) {
			this.slotView3.stop();
			int value = this.slotView3.getValue();
			this.addLog("SLOT3を停止 : " + value);
			this.checkState();
			this.checkEnd();
		}
	}

	/**
	 * 終了をチェックする。
	 */
	protected void checkEnd() {
		if (!this.slotView1.isRunning() && !this.slotView2.isRunning() && !this.slotView3.isRunning()) {
			int value1 = this.slotView1.getValue();
			int value2 = this.slotView2.getValue();
			int value3 = this.slotView3.getValue();
			CharSequence result = "" + value1 + ":" + value2 + ":" + value3;
			this.addLog("結果 : " + result);
			this.addLog("-----SLOTを終了-----");

			if ((value1 == value2) && (value1 == value3)) {
				this.complete(value1);
			}

			this.initSlots();
		}
	}

	/**
	 * 状態の変化をチェックする。
	 */
	protected void checkState() {
		if (this.slotView1.isRunning()) {
			this.slotButton1.setClickable(true);
		} else {
			this.slotButton1.setClickable(false);
		}
		if (this.slotView2.isRunning()) {
			this.slotButton2.setClickable(true);
		} else {
			this.slotButton2.setClickable(false);
		}
		if (this.slotView3.isRunning()) {
			this.slotButton3.setClickable(true);
		} else {
			this.slotButton3.setClickable(false);
		}
		if (this.slotView1.isRunning() || this.slotView2.isRunning() || this.slotView3.isRunning()) {
			this.startButton.setClickable(false);
		} else {
			this.startButton.setClickable(true);
		}
	}

	/**
	 * 画像が一致した場合の処理をする。
	 * 
	 * @param inValue 画像 ID
	 */
	private void complete(int inValue) {
		AlertDialog.Builder ab = new AlertDialog.Builder(this);
		ab.setTitle(ImageManager.getInstance().getMessage(inValue));
		ab.setMessage(R.string.dialog_complete);
		ab.setIcon(new BitmapDrawable(ImageManager.getInstance().getImage(inValue)));
		ab.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface inDialog, int inWhich) {
				inDialog.dismiss();
			}
		});
		ab.show();
	}
}
