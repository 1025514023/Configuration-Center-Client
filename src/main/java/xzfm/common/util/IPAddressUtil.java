package xzfm.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by wangxizhong on 17/3/19.
 */
public class IPAddressUtil {

    public static String getLocalHostName()  {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return inetAddress.getHostName();
    }

    public static String getLocalAddress() {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return inetAddress.getHostAddress();
    }
}
