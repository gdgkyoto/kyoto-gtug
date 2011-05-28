package org.kyotogtug.android.adkInst;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;

import com.android.future.usb.UsbAccessory;
import com.android.future.usb.UsbManager;

public class AdkInst extends Activity {
    private static final String TAG = "DemoKit";

    private static final String ACTION_USB_PERMISSION = "org.kyotogtug.android.AdkInst.action.USB_PERMISSION";

    private UsbManager mUsbManager;
    private PendingIntent mPermissionIntent;
    private boolean mPermissionRequestPending;

    UsbAccessory mAccessory;
    ParcelFileDescriptor mFileDescriptor;
    FileInputStream mInputStream;
    FileOutputStream mOutputStream;

    public static final byte LED_SERVO_COMMAND = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUsbManager = UsbManager.getInstance(this);
        mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        // registerReceiver(mUsbReceiver, filter);

        if (getLastNonConfigurationInstance() != null) {
            mAccessory = (UsbAccessory) getLastNonConfigurationInstance();
            openAccessory(mAccessory);
        }

        setContentView(R.layout.main);

    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        if (mAccessory != null) {
            return mAccessory;
        } else {
            return super.onRetainNonConfigurationInstance();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        closeAccessory();
    }

    @Override
    public void onResume() {
        super.onResume();

        Intent intent = getIntent();
        if (mInputStream != null && mOutputStream != null) {
            return;
        }

        UsbAccessory[] accessories = mUsbManager.getAccessoryList();
        UsbAccessory accessory = (accessories == null ? null : accessories[0]);
        if (accessory != null) {
            if (mUsbManager.hasPermission(accessory)) {
                openAccessory(accessory);
            } else {
                // synchronized (mUsbReceiver) {
                // if (!mPermissionRequestPending) {
                // mUsbManager.requestPermission(accessory, mPermissionIntent);
                // mPermissionRequestPending = true;
                // }
                // }
            }
        } else {
            Log.d(TAG, "mAccessory is null");
        }
    }

    private void openAccessory(UsbAccessory accessory) {
        mFileDescriptor = mUsbManager.openAccessory(accessory);
        if (mFileDescriptor != null) {
            mAccessory = accessory;
            FileDescriptor fd = mFileDescriptor.getFileDescriptor();
            mInputStream = new FileInputStream(fd);
            mOutputStream = new FileOutputStream(fd);
            Log.d(TAG, "accessory opened");
        } else {
            Log.d(TAG, "accessory open fail");
        }
    }

    private void closeAccessory() {
        try {
            if (mFileDescriptor != null) {
                mFileDescriptor.close();
            }
        } catch (IOException e) {
        } finally {
            mFileDescriptor = null;
            mAccessory = null;
        }
    }

    public void onButton1Clicked(View view) {
        sendServo(1);
    }

    public void onButton2Clicked(View view) {
        sendServo(2);
    }

    public void onButton3Clicked(View view) {
        sendServo(3);
    }

    private void sendServo(int n) {
        for (int i = 0; i < 100; i += 10) {
            byte target = (byte) (n - 1 + 0x10);
            sendCommand(LED_SERVO_COMMAND, target, i);
        }
    }

    public void sendCommand(byte command, byte target, int value) {
        byte[] buffer = new byte[3];
        if (value > 255)
            value = 255;

        buffer[0] = command;
        buffer[1] = target;
        buffer[2] = (byte) value;
        if (mOutputStream != null && buffer[1] != -1) {
            try {
                mOutputStream.write(buffer);
            } catch (IOException e) {
                Log.e(TAG, "write failed", e);
            }
        }
    }
}