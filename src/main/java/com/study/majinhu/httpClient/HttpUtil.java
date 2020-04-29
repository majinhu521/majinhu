package com.study.majinhu.httpClient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpec;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;

@SuppressWarnings("deprecation")
public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	private static int bufferSize= 1024;

	private static volatile HttpUtil instance;
	
	private ConnectionConfig connConfig;

	private SocketConfig socketConfig;

	private RequestConfig config;

	private ConnectionSocketFactory plainSF;

	private KeyStore trustStore;

	private SSLContext sslContext;

	private LayeredConnectionSocketFactory sslSF;

	private Registry<ConnectionSocketFactory> registry;

	private PoolingHttpClientConnectionManager connManager;

	private volatile HttpClient client;

	private volatile BasicCookieStore cookieStore;

	public static String defaultEncoding= "utf-8";
	public static String CONTENT_TYPE_JSON= "JSON";

	private static final Integer SOCKET_TIMEOUT = 3000;
	private static final Integer CONNECT_TIMEOUT = 3000;
	private static final Integer CONNECTION_REQUEST_TIMEOUT = 3000;

	//默认超时时间
	private static final int DEFAULT_TIME_OUT = 6000;
	//默认字符集
	private static final String DEFAULT_CHAR_SET = "UTF-8";

	private static List<NameValuePair> paramsConverter(Map<String, String> params){
		List<NameValuePair> nvps = new LinkedList<NameValuePair>();
		Set<Entry<String, String>> paramsSet = params.entrySet();
		for (Entry<String, String> paramEntry : paramsSet) {
		    String value = paramEntry.getValue();
			if(value != null && !"".equals(value)) {
				nvps.add(new BasicNameValuePair(paramEntry.getKey(), paramEntry.getValue()));
			}
		}
		return nvps;
	}

	public static String readStream(InputStream in, String encoding){
		if (in == null){
			return null;
		}
		try {
			InputStreamReader inReader= null;
			if (encoding == null){
				inReader= new InputStreamReader(in, defaultEncoding);
			}else{
				inReader= new InputStreamReader(in, encoding);
			}
			char[] buffer= new char[bufferSize];
			int readLen= 0;
			StringBuffer sb= new StringBuffer();
			while((readLen= inReader.read(buffer))!=-1){
				sb.append(buffer, 0, readLen);
			}
			inReader.close();
			return sb.toString();
		} catch (IOException e) {
			logger.error("读取返回内容出错", e);
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	private HttpUtil(){
		//设置连接参数
		connConfig = ConnectionConfig.custom().setCharset(Charset.forName(defaultEncoding)).build();
		socketConfig = SocketConfig.custom().setSoTimeout(SOCKET_TIMEOUT).build();

		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
		plainSF = new PlainConnectionSocketFactory();
		registryBuilder.register("http", plainSF);
		//指定信任密钥存储对象和连接套接字工厂
		try {
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, new AnyTrustStrategy()).build();
			sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			registryBuilder.register("https", sslSF);
		} catch (KeyStoreException e) {
			throw new RuntimeException(e);
		} catch (KeyManagementException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		registry = registryBuilder.build();
		//设置连接管理器
		connManager = new PoolingHttpClientConnectionManager(registry);
		connManager.setDefaultConnectionConfig(connConfig);
		connManager.setDefaultSocketConfig(socketConfig);
		//最大连接数
		connManager.setMaxTotal(1000);
		// 单个 host 最大连接数
		connManager.setDefaultMaxPerRoute(connManager.getMaxTotal());
		//指定cookie存储对象
		cookieStore = new BasicCookieStore();
		//构建客户端
        config = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).build();

		client= HttpClientBuilder.create().setDefaultRequestConfig(config).setDefaultCookieStore(cookieStore).setConnectionManager(connManager).build();
	}

	public static HttpUtil getInstance(){
		synchronized (HttpUtil.class) {
			if (HttpUtil.instance == null){
				instance = new HttpUtil();
			}
			return instance;
		}
	}

	public InputStream doGet(String url) throws URISyntaxException, ClientProtocolException, IOException{
		HttpResponse response= this.doGet(url, null);
		return response!=null ? response.getEntity().getContent() : null;
	}

	public String doGetForString(String url) throws URISyntaxException, ClientProtocolException, IOException{
		return HttpUtil.readStream(this.doGet(url), null);
	}

	public InputStream doGetForStream(String url, Map<String, String> queryParams) throws URISyntaxException, ClientProtocolException, IOException{
		HttpResponse response= this.doGet(url, queryParams);
		return response!=null ? response.getEntity().getContent() : null;
	}

	public String doGetForString(String url, Map<String, String> queryParams) throws URISyntaxException, ClientProtocolException, IOException{
		return HttpUtil.readStream(this.doGetForStream(url, queryParams), null);
	}

	/**
	 * 基本的Get请求
	 * @param url 请求url
	 * @param queryParams 请求头的查询参数
	 * @return
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public HttpResponse doGet(String url, Map<String, String> queryParams) throws URISyntaxException, ClientProtocolException, IOException{
		HttpGet gm = new HttpGet();
		URIBuilder builder = new URIBuilder(url);
		//填入查询参数
		if (queryParams!=null && !queryParams.isEmpty()){
			builder.setParameters(HttpUtil.paramsConverter(queryParams));
		}
		gm.setURI(builder.build());
		return client.execute(gm);
	}

	public HttpResponse doGet(String url, Map<String, String> queryParams,Map<String,String> headerParams) throws URISyntaxException, ClientProtocolException, IOException{
		HttpGet gm = new HttpGet();
		URIBuilder builder = new URIBuilder(url);
		//填入查询参数
		if (queryParams!=null && !queryParams.isEmpty()){
			builder.setParameters(HttpUtil.paramsConverter(queryParams));
		}
		gm.setURI(builder.build());
		if (headerParams != null && !headerParams.isEmpty()) {
			for (Entry<String, String> entry : headerParams.entrySet()) {
				if (entry.getKey() == null || entry.getKey().equals("")||entry.getValue() == null || entry.getValue().equals("")) {
					continue;
				}
				gm.addHeader(entry.getKey(), entry.getValue());
			}
		}
		return client.execute(gm);
	}



	public InputStream doPostForStream(String url, Map<String, String> queryParams, String contentType) throws URISyntaxException, ClientProtocolException, IOException {
		HttpResponse response = this.doPost(url, queryParams, null, contentType);
		return response!=null ? response.getEntity().getContent() : null;
	}

	public String doPostForString(String url, Map<String, String> queryParams, String contentType) throws URISyntaxException, ClientProtocolException, IOException {
		return HttpUtil.readStream(this.doPostForStream(url, queryParams, contentType), null);
	}

	public InputStream doPostForStream(String url, Map<String, String> queryParams, Map<String, String> formParams, String contentType) throws URISyntaxException, ClientProtocolException, IOException{
		HttpResponse response = this.doPost(url, queryParams, formParams, contentType);
		return response!=null ? response.getEntity().getContent() : null;
	}

	public String doPostRetString(String url, Map<String, String> queryParams, Map<String, String> formParams, String contentType) throws URISyntaxException, ClientProtocolException, IOException{
		return HttpUtil.readStream(this.doPostForStream(url, queryParams, formParams, contentType), null);
	}

	/**
	 * 基本的Post请求
	 * @param url 请求url
	 * @param queryParams 请求头的查询参数
	 * @param formParams post表单的参数
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public HttpResponse doPost(String url, Map<String, String> queryParams, Map<String, String> formParams, String contentType) throws URISyntaxException, ClientProtocolException, IOException{
		HttpPost pm = new HttpPost();
		URIBuilder builder = new URIBuilder(url);
		//填入查询参数
		if (queryParams!=null && !queryParams.isEmpty()){
			builder.setParameters(HttpUtil.paramsConverter(queryParams));
		}
		pm.setURI(builder.build());

		if(CONTENT_TYPE_JSON.equals(contentType)) {
			HttpEntity jsonEntity = new StringEntity(JsonUtil.obj2Json(formParams), ContentType.APPLICATION_JSON);
			pm.setEntity(jsonEntity);
		}else {
			//填入表单参数
			if (formParams != null && !formParams.isEmpty()) {
				pm.setEntity(new UrlEncodedFormEntity(HttpUtil.paramsConverter(formParams),"UTF-8"));
			}
		}
		return client.execute(pm);
	}

	/**
	 * 带head的post请求
	 * @param url
	 * @param headerParams
	 * @param formParams
	 * @param contentType
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResponse doPostWithHead(String url, Map<String, String> headerParams, Map<String, String> queryParams ,Map<String, String> formParams, String contentType) throws URISyntaxException, ClientProtocolException, IOException{

		HttpPost pm = new HttpPost();
		URIBuilder builder = new URIBuilder(url);

		// 头部参数
		if (headerParams != null && !headerParams.isEmpty()) {
			for (Entry<String, String> entry : headerParams.entrySet()) {
				if (entry.getKey() == null || entry.getKey().equals("")||entry.getValue() == null || entry.getValue().equals("")) {
					continue;
				}
				pm.addHeader(entry.getKey(), entry.getValue());
			}
		}

		// url参数
		if (queryParams!=null && !queryParams.isEmpty()){
			builder.setParameters(HttpUtil.paramsConverter(queryParams));
		}
		pm.setURI(builder.build());

		// form提交参数
		if(CONTENT_TYPE_JSON.equals(contentType)) {
			HttpEntity jsonEntity = new StringEntity(JsonUtil.obj2Json(formParams), ContentType.APPLICATION_JSON);
			pm.setEntity(jsonEntity);
		}else {
			//填入表单参数
			if (formParams != null && !formParams.isEmpty()) {
				pm.setEntity(new UrlEncodedFormEntity(HttpUtil.paramsConverter(formParams)));
			}
		}
		return client.execute(pm);
	}

	public HttpResponse doPost(String url, String body) throws URISyntaxException, ClientProtocolException, IOException {
		HttpPost pm = new HttpPost();
		URIBuilder builder = new URIBuilder(url);
		pm.setURI(builder.build());
		HttpEntity jsonEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
		pm.setEntity(jsonEntity);
		return client.execute(pm);
	}

	/**
	 * 获取当前Http客户端状态中的Cookie
	 * @param domain 作用域
	 * @param port 端口 传null 默认80
	 * @param path Cookie路径 传null 默认"/"
	 * @param useSecure Cookie是否采用安全机制 传null 默认false
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Map<String, Cookie> getCookie(String domain, Integer port, String path, Boolean useSecure){
		if (domain == null){
			return null;
		}
		if (port==null){
			port= 80;
		}
		if (path==null){
			path="/";
		}
		if (useSecure==null){
			useSecure= false;
		}
		List<Cookie> cookies = cookieStore.getCookies();
		if (cookies==null || cookies.isEmpty()){
			return null;
		}

		CookieOrigin origin= new CookieOrigin(domain, port, path, useSecure);
		BestMatchSpec cookieSpec = new BestMatchSpec();
		Map<String, Cookie> retVal= new HashMap<String, Cookie>();
		for (Cookie cookie : cookies) {
			if(cookieSpec.match(cookie, origin)){
				retVal.put(cookie.getName(), cookie);				
			}
		}
		return retVal;
	}

	/**
	 * 批量设置Cookie
	 * @param cookies cookie键值对图
	 * @param domain 作用域 不可为空
	 * @param path 路径 传null默认为"/"
	 * @param useSecure 是否使用安全机制 传null 默认为false
	 * @return 是否成功设置cookie
	 */
	public boolean setCookie(Map<String, String> cookies, String domain, String path, Boolean useSecure){
		synchronized (cookieStore) {
			if (domain==null){
				return false;
			}
			if (path==null){
				path= "/";
			}
			if (useSecure==null){
				useSecure= false;
			}
			if (cookies==null || cookies.isEmpty()){
				return true;
			}
			Set<Entry<String, String>> set= cookies.entrySet();
			String key= null;
			String value= null;
			for (Entry<String, String> entry : set) {
				key= entry.getKey();
				if (key==null || key.isEmpty() || value==null || value.isEmpty()){
					throw new IllegalArgumentException("cookies key and value both can not be empty");
				}
				BasicClientCookie cookie= new BasicClientCookie(key, value);
				cookie.setDomain(domain);
				cookie.setPath(path);
				cookie.setSecure(useSecure);
				cookieStore.addCookie(cookie);
			}
			return true;
		}
	}

	/**
	 * 设置单个Cookie
	 * @param key Cookie键
	 * @param value Cookie值
	 * @param domain 作用域 不可为空
	 * @param path 路径 传null默认为"/"
	 * @param useSecure 是否使用安全机制 传null 默认为false
	 * @return 是否成功设置cookie
	 */
	public boolean setCookie(String key, String value, String domain, String path, Boolean useSecure){
		Map<String, String> cookies= new HashMap<String, String>();
		cookies.put(key, value);
		return setCookie(cookies, domain, path, useSecure);
	}

	
	private class AnyTrustStrategy implements TrustStrategy{
		@Override
		public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			return true;
		}
	}
}

