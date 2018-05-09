package kafkaconsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class HTTPClients {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "http://localhost:8888/";

	private static final String POST_URL = "http://192.168.15.65:3000/api/operations/requestToPanel";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		sendPOST();
		System.out.println("POST DONE");

	}
	
	private static void sendPOST() throws IOException {

		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(20000)
				.setConnectTimeout(20000)
				.setSocketTimeout(20000).build();
		
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		
		HttpPost httpPost = new HttpPost(POST_URL);
		httpPost.addHeader("User-Agent", USER_AGENT);
		httpPost.addHeader("content-type", "application/json");
		httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJGSjg2R2NGM2pUYk5MT2NvNE52WmtVQ0lVbWZZQ3FvcXRPUWVNZmJoTmxFIn0.eyJqdGkiOiI3NjljNmFjYS05MDExLTQwOWMtYjgwMy1hNjgwMWNjYjJiYTciLCJleHAiOjE1MzA2MDQ2MzQsIm5iZiI6MCwiaWF0IjoxNTI1NDIwNjM0LCJpc3MiOiJodHRwOi8vaXFjdGVzdC5pcW9sc3lzLmNvbTozMDAwMy9hdXRoL3JlYWxtcy9xb2xzeXMtc2VjdXJpdHkiLCJhdWQiOiJub2RlanMtY29ubmVjdCIsInN1YiI6ImYxZWQwOWUxLTQwMTUtNGU0ZS1iNzJjLTk1ZWViZjkyNDRjZiIsInR5cCI6IkJlYXJlciIsImF6cCI6Im5vZGVqcy1jb25uZWN0IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiOTQ4NDFlN2UtNWI2OC00M2Q2LTg1OGYtNWQwODllM2Q2NGMzIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJhZG1pbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsidmlldy1wcm9maWxlIl19fSwibmFtZSI6IlFPTFNZUyBBRE1JTiIsInByZWZlcnJlZF91c2VybmFtZSI6ImFkbWluIiwiZ2l2ZW5fbmFtZSI6IlFPTFNZUyIsImZhbWlseV9uYW1lIjoiQURNSU4iLCJlbWFpbCI6ImFkbWluQHFvbHN5cy5jb20ifQ.JUGCEX1-5yXjTwrccsFkBio2kgKPk5CMrAU2yF9SwPX3woF12vbmG52ws05ZpG9rwKauddioCbdyaA75lClFMbD1045adDo7tzWXe6XCNbu0ydbrPOEt2BQxDcAfpNCQpbrvLat7eoQZJWBRQAZZjS98YC6pArPJeXKtL18deeA");
				String data = "{\"reqData\":{ "
				+ "\"imei\": \"001107000030058\", "
				+ "\"ticket\": \"sekhar\", "
				+ "\"timestamp\": 678845, "
				+ "\"source\": \"PANEL\","
				+ "\"event\":\"ARM_STATE\","
				+ "\"operation\": \"SET\","
				+ "\"device_id\": 0,"
				+ "\"dealer_id\": \"QolsysDealer\","
				+ "\"data\": {\"PROP_PANEL_ARM_TYPE\": \"PANEL_ARM_AWAY\"}}}";
		
		StringEntity stringEntity = new StringEntity(data.toString());	    
		httpPost.setEntity(stringEntity);

		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

		System.out.println("POST Response Status:: " + httpResponse.getStatusLine().getStatusCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();

		// print result
		System.out.println(response.toString());
		httpClient.close();
	}
	
	private static void sendGET() throws IOException {

		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(20000)
				.setConnectTimeout(20000)
				.setSocketTimeout(20000).build();
		
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

		HttpGet httpGet = new HttpGet(GET_URL);
		httpGet.addHeader("User-Agent", USER_AGENT);
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

		System.out.println("GET Response Status:: " + httpResponse.getStatusLine());

		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();

		// print result
		System.out.println(response.toString());
		httpClient.close();
	}


}