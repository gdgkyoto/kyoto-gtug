import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;


/**
 * 
 * @author Kenji
 *
 */
public class NicoNamaAuth {
	private static String AUTH_URL1 = "https://secure.nicovideo.jp/secure/login?site=nicolive_antenna";
	private static String AUTH_URL2 = "http://live.nicovideo.jp/api/getalertstatus";
	private static String AUTH_URL3 = "http://live.nicovideo.jp/api/getplayerstatus";
	private static String URL4 = "http://live.nicovideo.jp/api/getpostkey?block_no=0";
	
	/** ブラウザのCookieから取得したセッションIDを設定する
	 *  セッションIDはnicoとかでクッキーを検索し、user_sessionの値を設定する */
	private String user_session = "*";
	private String liveId = "lv51307702";
	
	private String emailAddress = "";
	private String password = "";
	
	private String commentMessage = "初見です。";
	
	
	private DefaultHttpClient client ;
	
	/**
	 * 認証を行い、認証チケットを取得する。
	 * 
	 * @param mailAddress
	 * @param password
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private String getAuthTicket(String mailAddress, String password) throws ClientProtocolException, IOException{
		HttpPost request = new HttpPost(AUTH_URL1);
		HttpClientParams.setCookiePolicy(client.getParams(), CookiePolicy.BROWSER_COMPATIBILITY); 
		
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("mail", mailAddress));
		paramList.add(new BasicNameValuePair("password", password));
		request.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
		HttpResponse httpResponse = client.execute(request);
		int status = httpResponse.getStatusLine().getStatusCode();
		if (HttpStatus.SC_OK == status){
			List<Cookie> cookies = ((AbstractHttpClient) client).getCookieStore().getCookies();
			System.out.println("CookieSize="+cookies.size());
			for(Cookie cookie : cookies){
				System.out.println("name="+cookie.getName()+" value="+cookie.getValue());
			}
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			httpResponse.getEntity().writeTo(outputStream);
			String body = new String(outputStream.toByteArray(),"UTF-8");
			String ticket = StringUtils.substringBetween(body, "<ticket>", "</ticket>");
			System.out.println("Ticket="+ticket+"\nbody="+body);
			
			for(Header header : httpResponse.getAllHeaders()){
				System.out.println("Name="+header.getName()+" Value="+header.getValue() );
			}
			return ticket;
		}
		return null;
	}
	
	
	
	/**
	 * 各種情報を取得する。
	 * 
	 * 
	 * @param ticket
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private AuthResult getAlertStatus(String ticket) throws ClientProtocolException, IOException{ 
		HttpPost request = new HttpPost(AUTH_URL2);
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("ticket", ticket));
		request.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
		HttpResponse httpResponse = client.execute(request);
		int status = httpResponse.getStatusLine().getStatusCode();
		if (HttpStatus.SC_OK == status){
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			httpResponse.getEntity().writeTo(outputStream);
			String body = new String(outputStream.toByteArray(),"UTF-8");
			AuthResult authResult = new AuthResult();
			authResult.setTicket(ticket);
			authResult.setCommentServerAddress(StringUtils.substringBetween(body, "<addr>", "</addr>"));
			authResult.setCommentServerPort(Integer.parseInt(StringUtils.substringBetween(body, "<port>", "</port>")));
			authResult.setThreadId(StringUtils.substringBetween(body, "<thread>", "</thread>"));
			authResult.setUserId(StringUtils.substringBetween(body, "<user_id>", "</user_id>"));
			
			System.out.println("Ticket="+ticket+"\nbody="+body);
			return authResult;
		}
		return null;
	}
	
	/**
	 * コメントサーバ接続のための情報を取得する。
	 * @param liveNo
	 * @param ticket
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private AuthResult getLive(String liveNo,String ticket) throws ClientProtocolException, IOException{
		HttpPost request = new HttpPost(AUTH_URL3);
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("v", liveNo));
		request.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
		BasicClientCookie cookie = new BasicClientCookie("user_session", user_session);
		cookie.setDomain(".nicovideo.jp");
		cookie.setPath("/");
		client.getCookieStore().addCookie( cookie );
		
		// 実行
		HttpResponse httpResponse = client.execute(request);
		int status = httpResponse.getStatusLine().getStatusCode();
		if (HttpStatus.SC_OK == status){
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			httpResponse.getEntity().writeTo(outputStream);
			String body = new String(outputStream.toByteArray(),"UTF-8");
			System.out.println("body="+body);

			AuthResult authResult = new AuthResult();
			authResult.setTicket(ticket);
			authResult.setCommentServerAddress(StringUtils.substringBetween(body, "<addr>", "</addr>"));
			authResult.setCommentServerPort(Integer.parseInt(StringUtils.substringBetween(body, "<port>", "</port>")));
			authResult.setThreadId(StringUtils.substringBetween(body, "<thread>", "</thread>"));
			authResult.setOpenTime(Long.parseLong(StringUtils.substringBetween(body, "<open_time>", "</open_time>")));
			System.out.println("Live Ticket="+ticket+"\nbody="+body+" thread="+authResult.getThreadId());
			return authResult;
		}
		return null;
	}
	
	/**
	 * コメントを投稿する際に使用するvposを取得する。
	 * @param authResult
	 * @return
	 */
	private long getVpos(AuthResult authResult){
		long time = System.currentTimeMillis() / 1000;
		System.out.println("time="+time);
		System.out.println("open_time="+authResult.getOpenTime());
		long vpos = (time - authResult.getOpenTime()) * 100; 
		System.out.println("vpos="+vpos);
		
		return vpos;
	}
	
	/**
	 * コメントを投稿する際に使用するポストキーを取得する。
	 * @param threadId
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private String getPostKey(String threadId) throws ClientProtocolException, IOException{
		HttpPost request = new HttpPost(URL4);
		HttpClientParams.setCookiePolicy(client.getParams(), CookiePolicy.BROWSER_COMPATIBILITY); 
		
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("thread", threadId));
		request.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
		HttpResponse httpResponse = client.execute(request);
		int status = httpResponse.getStatusLine().getStatusCode();
		if (HttpStatus.SC_OK == status){
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			httpResponse.getEntity().writeTo(outputStream);
			String body = new String(outputStream.toByteArray(),"UTF-8");
			String postKey = body.replace("postkey=", "");
			System.out.println("postKey="+postKey);
			return postKey;
		}
		return null;
	}
	
	/**
	 * コメントサーバに接続し、コメントを取得する。
	 * このメソッドは接続が切断されるまでブロックする。
	 * 
	 * @param authResult
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void connectCommentServer(AuthResult authResult) throws UnknownHostException, IOException{
		System.out.println("server="+authResult.getCommentServerAddress()+" port="+authResult.getCommentServerPort()+" ticket="+authResult.getTicket());
		Socket sock = new Socket(authResult.getCommentServerAddress(),authResult.getCommentServerPort());
		System.out.println("connected!");
		BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream(),"UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(),"UTF-8"));
		bw.write("<thread thread=\""+authResult.getThreadId()+"\" res_from=\"-200\" version=\"20061206\">\0");
		bw.flush();
		
		
		System.out.println("send");
		String line = null;
		char ch ;
		StringBuilder sb = new StringBuilder();
		String commentTicket = null;
		
		
		while(true){
			ch = (char) br.read();
			if (ch=='\0'){
				line = sb.toString(); 
				if (line.indexOf(" ticket=\"") != -1){
					commentTicket = StringUtils.substringBetween(line, "ticket=\"", "\" revision=");
					sendComment(bw,authResult,commentTicket,commentMessage);
					System.out.println("投稿!!");
				}
				sb.delete(0,sb.length());
			}else{
				sb.append(ch);
			}
		}
	}
	
	/**
	 * コメントを投稿する
	 * @param writer
	 * @param authResult
	 * @param commentTicket
	 * @param text
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private void sendComment(BufferedWriter writer, AuthResult authResult, String commentTicket, String text) throws ClientProtocolException, IOException{
		long vpos = getVpos(authResult);
		String postKey = getPostKey(authResult.getThreadId());
		writer.write("<chat thread=\""+authResult.getThreadId()+"\" ticket=\""+commentTicket+"\" vpos=\""+vpos+"\" postkey=\""+postKey+"\" mail=\" 184\" user_id=\""+authResult.getUserId()+"\" premium=\"1\">え</chat>\0");
	}
	
	public static void main(String[] args){
		NicoNamaAuth main = new NicoNamaAuth();
		main.start();
	}
	
	private void start(){
		client = new DefaultHttpClient();
		try {
			String ticket = getAuthTicket(emailAddress,password);
			AuthResult authResult1 = getAlertStatus(ticket);
			AuthResult authResult = getLive(liveId, ticket);
			authResult.setUserId(authResult1.getUserId());
			connectCommentServer(authResult);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class AuthResult{
		private String commentServerAddress;
		private int commentServerPort;
		private String threadId;
		private String ticket;
		private String userId;
		private long openTime;
		public String getCommentServerAddress() {
			return commentServerAddress;
		}
		public void setCommentServerAddress(String commentServerAddress) {
			this.commentServerAddress = commentServerAddress;
		}
		public int getCommentServerPort() {
			return commentServerPort;
		}
		public void setCommentServerPort(int commentServerPort) {
			this.commentServerPort = commentServerPort;
		}
		public String getThreadId() {
			return threadId;
		}
		public void setThreadId(String threadId) {
			this.threadId = threadId;
		}
		public String getTicket() {
			return ticket;
		}
		public void setTicket(String ticket) {
			this.ticket = ticket;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public long getOpenTime() {
			return openTime;
		}
		public void setOpenTime(long openTime) {
			this.openTime = openTime;
		}
		
	}

}
