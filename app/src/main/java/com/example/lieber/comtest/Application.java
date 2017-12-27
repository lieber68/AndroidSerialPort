/*
 * Copyright 2009 Cedric Priscal
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package com.example.lieber.comtest;

import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortFinder;

public class Application extends android.app.Application {

    private static final String TAG = Application.class.getSimpleName();

    public SerialPortFinder mSerialPortFinder = new SerialPortFinder();
    private SerialPort mSerialPort = null;

//    private SerialPort read;
//
//    private SerialPort send;

    public SerialPort getSerialPort() throws SecurityException, IOException, InvalidParameterException {
        if (mSerialPort == null) {
            /* Read serial port parameters */

            String packageName = getPackageName();
            SharedPreferences sp = getSharedPreferences(packageName + "_preferences", MODE_PRIVATE);
            String path = sp.getString("DEVICE", "");
            int baudrate = Integer.decode(sp.getString("BAUDRATE", "-1"));

			/* Check parameters */
            if ((path.length() == 0) || (baudrate == -1)) {
                throw new InvalidParameterException();
            }
            Log.e(TAG, "getSerialPort: " + path);

			/* Open the serial port */
            mSerialPort = new SerialPort(new File(path), baudrate, 0);
        }
        return mSerialPort;
    }

//    public SerialPort getReadSerialPort() throws SecurityException, IOException, InvalidParameterException {
//        if (read == null) {
//            /* Open the serial port */
//            read = new SerialPort(new File("/dev/ttyUSB7"), 9600, 0);
//        }
//        return read;
//    }
//
//    public SerialPort getSendSerialPort() throws SecurityException, IOException, InvalidParameterException {
//        if (send == null) {
//            /* Open the serial port */
//            send = new SerialPort(new File("/dev/ttyUSB8"), 9600, 0);
//        }
//        return send;
//    }

//    public void clear() {
//        if (read != null) {
//            read.close();
//            read = null;
//        }
//        if (send != null) {
//            send.close();
//            send = null;
//        }
//    }

    public void closeSerialPort() {
        if (mSerialPort != null) {
            mSerialPort.close();
            mSerialPort = null;
        }
    }
}
