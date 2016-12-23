package com.polovnev.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.polovnev.constants.Constants;
import com.polovnev.exception.ClientErrorException;
import com.polovnev.exception.ServerErrorException;
import com.polovnev.trader.Trader;


public class HttpConnection {
	
	private String getUrl(Trader trader) {
		StringBuilder stringBuilder = new StringBuilder(Constants.URL);
		stringBuilder.append(trader.getUrlName());
		stringBuilder.append("/clients/");
		stringBuilder.append(Constants.USER_ID);
		stringBuilder.append("/messages/");
		return stringBuilder.toString();
	}
	
	private byte[] getPostDataBytes(Map<String,Object> params) throws UnsupportedEncodingException {
		StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), Constants.CHARSET));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), Constants.CHARSET));
        }
        return postData.toString().getBytes(Constants.CHARSET);
	}
	
	private String getResponse(HttpURLConnection con) throws IOException {
		Reader in = new BufferedReader(new InputStreamReader(con.getInputStream(), Constants.CHARSET));
		StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        return  sb.toString();
	}
	
	private HttpURLConnection getHttpURLConnection(String method, String urlString) throws IOException {
		System.out.println(urlString);
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(method);
		return con;
	}
	
	private void printResponse(HttpURLConnection con) throws IOException, ClientErrorException, ServerErrorException {
		int responseCode = con.getResponseCode();
		if(400 <= responseCode && responseCode < 500)
			throw new ClientErrorException(responseCode);
		if(responseCode >= 500)
			throw new ServerErrorException(responseCode);
		System.out.println("Response code: " + responseCode);
		String response = getResponse(con);
        System.out.println("Response: " + response);
	}
	
	public void sendMessage(String message, Trader trader) throws IOException, ClientErrorException, ServerErrorException {
		String urlString = getUrl(trader);
		HttpURLConnection con = getHttpURLConnection("POST", urlString);
		Map<String,Object> params = new HashMap<String, Object>();
		byte[] postDataBytes = getPostDataBytes(params);
		con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		con.setDoOutput(true);
        con.getOutputStream().write(postDataBytes);
        printResponse(con);
	}
	
	public void getMessages(Trader trader) throws IOException, ClientErrorException, ServerErrorException {
		String urlString = getUrl(trader);
		HttpURLConnection con = getHttpURLConnection("GET", urlString);
		printResponse(con);
	}

}
