package com.flightwall.app;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.JavascriptInterface;
import android.view.View;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class MainActivity extends Activity {
 WebView web; ExecutorService pool=Executors.newSingleThreadExecutor();
 @Override public void onCreate(Bundle b){super.onCreate(b);getWindow().getDecorView().setSystemUiVisibility(5894|1024|512);web=new WebView(this);web.getSettings().setJavaScriptEnabled(true);web.getSettings().setDomStorageEnabled(true);web.setWebViewClient(new WebViewClient());web.addJavascriptInterface(new Bridge(),"Native");setContentView(web);web.loadUrl("file:///android_asset/index.html");}
 class Bridge {
 @JavascriptInterface public void refresh(){pool.execute(()->{String json=get("https://api.airplanes.live/v2/point/25.7016/-80.3580/10");final String payload=json==null?"null":json;runOnUiThread(()->web.evaluateJavascript("receive("+org.json.JSONObject.quote(payload)+")",null));});}
 @JavascriptInterface public void photo(String hex){if(!hex.matches("[a-fA-F0-9]{6}"))return;pool.execute(()->{String json=get("https://api.planespotters.net/pub/photos/hex/"+hex);final String payload=json==null?"null":json;runOnUiThread(()->web.evaluateJavascript("receivePhoto("+org.json.JSONObject.quote(payload)+")",null));});}
 }
 String get(String url){try{HttpURLConnection c=(HttpURLConnection)new URL(url).openConnection();c.setConnectTimeout(12000);c.setReadTimeout(12000);c.setRequestProperty("User-Agent","FlightWallPersonal/1.0");try(InputStream in=c.getInputStream();ByteArrayOutputStream out=new ByteArrayOutputStream()){byte[] b=new byte[8192];int n;while((n=in.read(b))!=-1)out.write(b,0,n);return out.toString("UTF-8");}finally{c.disconnect();}}catch(Exception e){return null;}}
 @Override protected void onDestroy(){pool.shutdownNow();web.destroy();super.onDestroy();}
}
