package com.study.majinhu.httpClient;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HttpClientUtil
 * @Description 通讯工具
 * @Author HZY
 * @Date 2019/5/27 10:38
 * @Version 1.0
 **/
public class HttpClientUtil {
    private static Logger logger= Logger.getLogger(HttpClientUtil.class);

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;

    /**
     * @Author HZY
     * @Description
     * @Date 17:42 2019/5/28
     * @Param [url, json]
     * @return java.lang.String
     **/
    public static String doGet(String url, Map<String, String> param) {
        // 创建Httpclient对象
        ConnectionKeepAliveStrategy kaStrategy = new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    //如果服务器没有设置keep-alive这个参数，我们就把它设置成1分钟
                    keepAlive = 60000;
                }
                return keepAlive;
            }

        };

        CloseableHttpClient httpclient = HttpClients.custom().setKeepAliveStrategy(kaStrategy).build();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 设置请求的超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * @Author HZY
     * @Description
     * @Date 17:42 2019/5/28
     * @Param [url, json]
     * @return java.lang.String
     **/
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * @Author HZY
     * @Description
     * @Date 17:42 2019/5/28
     * @Param [url, json]
     * @return java.lang.String
     **/
    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        ConnectionKeepAliveStrategy kaStrategy = new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    //如果服务器没有设置keep-alive这个参数，我们就把它设置成1分钟
                    keepAlive = 60000;
                }
                return keepAlive;
            }

        };

        CloseableHttpClient httpClient = HttpClients.custom().setKeepAliveStrategy(kaStrategy).build();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 设置请求的超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
            httpPost.setConfig(requestConfig);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
                System.out.println("-------------------------" + entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    /**
     * @Author HZY
     * @Description http形式发送
     * @Date 17:42 2019/5/28
     * @Param [url, json]
     * @return java.lang.String
     **/
    public static String doPostHttp(String url, String param) {
        // 创建Httpclient对象
        ConnectionKeepAliveStrategy kaStrategy = new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    //如果服务器没有设置keep-alive这个参数，我们就把它设置成1分钟
                    keepAlive = 60000;
                }
                return keepAlive;
            }

        };

        CloseableHttpClient httpClient = HttpClients.custom().setKeepAliveStrategy(kaStrategy).build();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 设置请求的超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
            httpPost.setConfig(requestConfig);
            // 创建参数列表
//            if (param != null) {
//                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
//                for (String key : param.keySet()) {
//                    paramList.add(new BasicNameValuePair(key, param.get(key)));
//                }
//                // 模拟表单
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
//                httpPost.setEntity(entity);
//            }
            StringEntity entity = new StringEntity(param, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    /**
     * @Author HZY
     * @Description https形式发送
     * @Date 17:42 2019/5/28
     * @Param [url, json]
     * @return java.lang.String
     **/
    public static String doPostHttps(String url, String param) {
        // 创建Httpclient对象
        ConnectionKeepAliveStrategy kaStrategy = new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    //如果服务器没有设置keep-alive这个参数，我们就把它设置成1分钟
                    keepAlive = 60000;
                }
                return keepAlive;
            }

        };
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
//		CloseableHttpClient httpClient = HttpClients.custom().setKeepAliveStrategy(kaStrategy).build();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 设置请求的超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
            httpPost.setConfig(requestConfig);
            // 创建参数列表
            if (param != null) {
//                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
//                for (String key : param.keySet()) {
//                    paramList.add(new BasicNameValuePair(key, param.get(key)));
//                }
//                // 模拟表单
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                // 创建请求内容
                StringEntity entity = new StringEntity(param, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(entity);
//            	httpPost.setEntity(new StringEntity(param, HTTP.UTF_8)); 
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * @Author HZY
     * @Description 发送json形式数据
     * @Date 17:42 2019/5/28
     * @Param [url, json]
     * @return java.lang.String
     **/
    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            System.out.println("================" + ContentType.APPLICATION_JSON);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return resultString;
    }

    /**
     * @Author HZY
     * @Description 发送json形式数据
     * @Date 17:42 2019/5/28
     * @Param [url, json]
     * @return java.lang.String
     **/
    public static String doPostStr(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_FORM_URLENCODED);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return resultString;
    }

    /**
     * @Author HZY
     * @Description HTTP POST 请求，如果失败重试3次
     * @Date 17:41 2019/5/28
     * @Param [paramUrl, paramData]
     * @return java.lang.String
     **/
    public static String RetryHttpsPostData(String paramUrl, String paramData) {
        String result = null;
        int retryTimes = 0;
        while (retryTimes < 4) { //重试3次
            if (retryTimes > 0) {
                logger.error("======== 重试次数 [{}]"+retryTimes);
            }
            if (paramUrl.startsWith("https://")) {
                result = doPostHttps(paramUrl, paramData);
            } else {
                result = doPostHttp(paramUrl, paramData);
            }

            if (StringUtils.isNotEmpty(result)) {
                break;
            }
            retryTimes++;
        }
        return result;
    }

    /**
     * @Author HZY
     * @Description 创建SSL安全连接
     * @Date 17:41 2019/5/28
     * @Param []
     * @return org.apache.http.conn.ssl.SSLConnectionSocketFactory
     **/
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }
}
