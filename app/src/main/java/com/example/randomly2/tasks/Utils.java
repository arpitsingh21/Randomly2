package com.example.randomly2.tasks;

import android.content.Context;
import android.os.NetworkOnMainThreadException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Utils {

    public static int getInternetStatus() {
        if (!isInternetAvailable(5000)) {
            return 0;
        }
        return 1;
    }

    private static boolean isInternetAvailable(int timeOutMs) {

        try {
            Socket sock = new Socket();
            InetSocketAddress addr = new InetSocketAddress("google.com", 80);
            try {
                sock.connect(addr, timeOutMs);
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                try {
                    sock.close();
                } catch (IOException e) {
                }
            }
        } catch (NetworkOnMainThreadException e) {
            e.printStackTrace();
            return false;
        }
    }
}
