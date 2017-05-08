package xzfm.common.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by wangxizhong on 16/12/25.
 */
public class UUIDUtil extends ThreadLocalUtilHolder<UUID> implements Serializable {


    private static class UUIDSingletonHolder {
        static UUIDUtil instance = new UUIDUtil();
    }

    public static String getUUIDToLowerCase() {
        return Arrays.stream(UUIDSingletonHolder.instance.get().randomUUID().toString().toLowerCase()
                .split("-"))
                .collect(Collectors.joining());
    }

    public static String getUUIDToUpperCase() {
        return Arrays.stream(UUIDSingletonHolder.instance.get().randomUUID().toString().toUpperCase()
                .split("-"))
                .collect(Collectors.joining());
    }

    @Override
    protected UUID newInstance() {
        byte[] randomBytes = new byte[16];
        long mostSigBits = 0;
        for (int i = 0; i < 8; i++) {
            mostSigBits = (mostSigBits << 8) | (randomBytes[i] & 0xff);
        }
        long leastSigBits = 0;
        for (int i = 8; i < 16; i++) {
            leastSigBits = (leastSigBits << 8) | (randomBytes[i] & 0xff);
        }
        return new UUID(mostSigBits, leastSigBits);
    }
}
