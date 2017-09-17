package com.rak;

import android.util.Log;

import com.google.android.things.pio.PeripheralManagerService;
import com.google.android.things.pio.UartDevice;
import com.google.android.things.pio.UartDeviceCallback;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by Naresh Krish on 9/17/2017.
 * Concrete impleentation for the WisNode LoRa node board by RAK Wireless
 */

public class WisnodeLora extends Wisnode{

    private final String TAG = "WisnodeLora";
    protected UartDevice mDevice;

    /***
     * REQUEST CODES
     */
    public static final int VERSION_REQUEST = 1;
    public static final int SLEEP_REQUEST = 2;
    public static final int RESET_REQUEST = 3;
    public static final int RELOAD_REQUEST = 4;
    public static final int MODE_REQUEST = 5;
    public static final int SET_CONFIG_REQUEST = 6;
    public static final int GET_CONFIG_REQUEST = 7;
    public static final int JOIN_REQUEST = 8;
    public static final int SEND_MESSAGE_REQUEST = 9;
    public static final int RECIEVE_MESSAGE_REQUEST = 10;
    public static final int RF_CONFIG_REQUEST = 11;
    public static final int TX_CONTINUE_REQUEST = 12;
    public static final int RX_CONTINUE_REQUEST = 13;
    public static final int TX_STOP_REQUEST = 14;
    public static final int RX_STOP_REQUEST = 15;
    public static final int STATUS_REQUEST = 16;
    public static final int UART_CONFIG_REQUEST = 17;
    /**
     * Constrcutor that takes the portname for the Uart on which the wisnode board
     * is located
     * @param wisnodePortName - name of the wisnode port
     */
    public WisnodeLora(String wisnodePortName, int baudrate){
        if(wisnodePortName != null & wisnodePortName.length() > 0){
            Log.d(TAG, "Wisnode module requested on port :: " + wisnodePortName + " with baudarate :: " + baudrate );
            try{
                mDevice = new PeripheralManagerService().openUartDevice(wisnodePortName);

                // Some basic UART configuration.
                // Make sure to configure the other device the exact same way.
                mDevice.setBaudrate(baudrate);
                mDevice.setDataSize(8);
                mDevice.setParity(UartDevice.PARITY_NONE);
                mDevice.setStopBits(1);
                mConnected = true;

            }catch (IOException exp){
                Log.e(TAG, "There was an issue opening the UART port with name :: " + wisnodePortName);
            }
        }

    }

    @Override
    public void close() throws IOException {
        if (mDevice != null) {
            try {
                mDevice.close();
            } finally {
                mDevice = null;
            }
        }
    }

    @Override
    public UartDevice getUARTDevice() {
        return mDevice;
    }
}
