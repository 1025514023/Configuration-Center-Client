package xzfm.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.function.Consumer;


/**
 * Created by wangxizhong on 2016/10/27.
 */
public class TimeUtil {
    public static String longToString(long timestamp, String format) {
        if (StringUtil.validateEmptyOrNull(format)) {
            return "timeFormat is null";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Timestamp(timestamp));
    }

    public static Timestamp todayBeginTime() {
        return Timestamp.valueOf(LocalDate.now().atStartOfDay());
    }

    public static Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static <T> void runTimeToMillisecond(Consumer<T> runSomeThing) {
        long startTime = System.currentTimeMillis();
        runSomeThing.accept(null);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    public static <T> void runTimeToSecond(Consumer<T> runSomeThing) {
        long startTime = System.nanoTime();
        runSomeThing.accept(null);
        long endTime = System.nanoTime();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ns");
    }

    /*public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = format.parse("2012-10-11 07:18:54");
        Date date2 = format.parse("2012-12-11 09:18:44");

        String diff = diffDate(date1.getTime(), date2.getTime());

        System.out.println(diff);
    }*/
    // TODO: 2016/11/2  api doc

    /**
     * 获取两个时间的时间查 如1天2小时30分钟
     */
    public static String diffDate(long from, long to) {
        String diff = "";
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long _diff = to - from;
        if (_diff <= 0) {
            diff = "0d";
            return diff;
        }
        // 计算差多少天
        long day = _diff / nd;
        // 计算差多少小时
        long hour = _diff % nd / nh;
        // 计算差多少分钟
        long min = _diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        if (day > 0) {
            diff += day + "d ";
        }
        if (hour > 0) {
            diff += hour + "h ";
        }
        if (min > 0) {
            diff += min + "m";
        }
        return diff;
    }
}
