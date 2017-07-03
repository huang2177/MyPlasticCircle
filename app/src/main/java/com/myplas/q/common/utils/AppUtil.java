package com.myplas.q.common.utils;

import android.text.TextUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class AppUtil {
    private static final String TAG = AppUtil.class.getSimpleName();

    public static String getLocalIP()
    {
        String ip = null;
        String self = "127.0.0.1";

        try
        {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;

            while(networkInterfaces.hasMoreElements())
            {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();

                while(inetAddresses.hasMoreElements())
                {
                    inetAddress = inetAddresses.nextElement();

                    if(!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address)
                    {
                        ip = inetAddress.getHostAddress();

                        if(!ip.equals(self))
                        {
                            break;
                        }
                        else
                        {
                            ip = null;
                        }
                    }
                }
            }
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }

        return TextUtils.isEmpty(ip)? self: ip;
    }
}