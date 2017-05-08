package xzfm.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by wangxizhong on 16/12/28.
 */
public class GsonUtil extends ThreadLocalUtilHolder<GsonBuilder> {
    @Override
    protected GsonBuilder newInstance() {
        return new GsonBuilder();
    }

    private static class GsonSingletonHolder {
        static GsonUtil instance = new GsonUtil();
    }

    public static Gson getGsonInstance() {
        return GsonSingletonHolder.instance.get().create();
    }
}
