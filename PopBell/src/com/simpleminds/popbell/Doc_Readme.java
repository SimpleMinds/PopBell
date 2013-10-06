/*
 *PopBell Application for Android
 *Copyright (C) 2013 SimpleMinds Team
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.simpleminds.popbell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

public class Doc_Readme extends ActionBarActivity {
	
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    private Handler handler;
	String url;
	TextView helloTxt;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doclayout);
        helloTxt = (TextView)findViewById(R.id.contents);
        handler = new Handler();
		new Thread(runnable).start();
    }
    
    Runnable runnable = new Runnable(){
		public void run(){
			DefaultHttpClient httpclient = new DefaultHttpClient();
			url = "https://raw.github.com/SimpleMinds/PopBell/master/README.txt";
			HttpGet httppost = new HttpGet(url);
			Log.d("url", url);
			HttpResponse response = null;
			try {
				response = httpclient.execute(httppost);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			HttpEntity ht = response.getEntity();
			BufferedHttpEntity buf = null;
			try {
				buf = new BufferedHttpEntity(ht);
			} catch (IOException e) {
				e.printStackTrace();
			}
			InputStream is = null;
			try {
				is = buf.getContent();
			} catch (IOException e) {
				e.printStackTrace();
			}
			InputStreamReader isn = null;
			try {
				isn = new InputStreamReader(is, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			BufferedReader r = new BufferedReader(isn);
			final StringBuilder total = new StringBuilder();
			String line;
			try {
				while ((line = r.readLine()) != null){
					total.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			handler.post(new Runnable(){
				public void run(){
					helloTxt.setText(total.toString());
				}
			});
		}
	};
}