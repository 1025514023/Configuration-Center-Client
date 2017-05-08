package xzfm.common.boot.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by wangxizhong on 2017/3/13.
 */
public class HttpClientHandler {
    private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";

    private static void initHttpClientConnectionPool() {
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);// max connection by pool
            cm.setDefaultMaxPerRoute(5);// route max connection,default 2
        }
    }

    /**
     * get HttpClient from pool
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        initHttpClientConnectionPool();
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * @param url
     * @return
     */
    public static String httpGetRequest(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        uriBuilder.setParameters(pairs);

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        uriBuilder.setParameters(pairs);

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return getResult(httpGet);
    }

    public static String httpPostRequest(String url) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws IOException {
        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

        return getResult(httpPost);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = params.entrySet().stream().map(param -> new BasicNameValuePair(param.getKey(),
                        String.valueOf(param.getValue()))).collect(Collectors.toCollection(ArrayList::new));

        return pairs;
    }

    /**
     * handler Http request
     *
     * @param request
     */
    private static String getResult(HttpRequestBase request) throws IOException {
        // CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = httpClient.execute(request);
        // response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // long len = entity.getContentLength();// -1 length not know
            String result = EntityUtils.toString(entity);
            response.close();
            // httpClient.close();
            return result;
        }
        return EMPTY_STR;
    }
}
