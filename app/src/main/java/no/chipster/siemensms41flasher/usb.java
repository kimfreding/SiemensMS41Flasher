package no.chipster.siemensms41flasher;

import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.util.Log;
import android.content.Context;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import java.lang.Thread;
import java.lang.Object;

import com.ftdi.j2xx.D2xxManager;
import com.ftdi.j2xx.FT_Device;

import static java.lang.Thread.*;

public class usb extends AppCompatActivity{
        public static D2xxManager ftD2xx = null;
        public FT_Device flasher;

        boolean mThreadIsStopped = true;
        Handler mHandler = new Handler();
        Thread mThread;
        //Context ctx;
        Context context = ApplicationContextProvider.getContext();

        public void openDevice() {

                if(flasher != null) {
                        if(flasher.isOpen()) {
                                if(mThreadIsStopped) {
                                        //updateView(true);
                                        flasher.setDataCharacteristics(D2xxManager.FT_DATA_BITS_8,D2xxManager.FT_STOP_BITS_2,D2xxManager.FT_PARITY_EVEN);
                                        flasher.setBaudRate(9600);
                                        flasher.purge((byte) (D2xxManager.FT_PURGE_TX | D2xxManager.FT_PURGE_RX));
                                        flasher.restartInTask();
                                        //new Thread(mLoop).start();
                                }
                                return;
                        }
                }

                int devCount = 0;
                devCount = ftD2xx.createDeviceInfoList(context);

                //Log.d(TAG, "Device number : "+ Integer.toString(devCount));

                D2xxManager.FtDeviceInfoListNode[] deviceList = new D2xxManager.FtDeviceInfoListNode[devCount];
                ftD2xx.getDeviceInfoList(devCount, deviceList);

                if(devCount <= 0) {
                        return;
                }

                if(flasher == null) {
                        flasher = ftD2xx.openByIndex(context, 0);
                } else {
                        synchronized (flasher) {
                                flasher = ftD2xx.openByIndex(context, 0);
                        }
                }

                if(flasher.isOpen()) {
                        if(mThreadIsStopped) {
                                //updateView(true);
                                flasher.setDataCharacteristics(D2xxManager.FT_DATA_BITS_8,D2xxManager.FT_STOP_BITS_2,D2xxManager.FT_PARITY_EVEN);
                                flasher.setBaudRate(9600);
                                flasher.purge((byte) (D2xxManager.FT_PURGE_TX | D2xxManager.FT_PURGE_RX));
                                flasher.restartInTask();
                                //new Thread(mLoop).start();
                        }
                }
        }
        private Runnable mLoop = new Runnable() {
                @Override
                public void run() {

                    if(flasher == null) {
                        try {
                            openDevice();
                            Thread.sleep(100);
                        }
                        catch(InterruptedException e){
                            System.out.println("got interrupted!");
                        }
                    }

                }

        };

        public void closeDevice() {
                mThreadIsStopped = true;
                //updateView(false);
                if(flasher != null) {
                        flasher.close();
                }
        }

}
