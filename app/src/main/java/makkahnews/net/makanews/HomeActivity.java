package makkahnews.net.makanews;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;

public class HomeActivity extends AppCompatActivity {

    private SpinKitView spinKit;
    private WebView webView;
    private TextView load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        spinKit = findViewById(R.id.spin_kit);
        webView = findViewById(R.id.web_view);
        load = findViewById(R.id.load);

        webView.setWebViewClient(new WebViewClient());
        // you must enable java script option
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.makkahnews.net/");


        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                //progressBar.setProgress(progress);
                if (progress == 100) {
                    webView.setVisibility(View.VISIBLE);
                    spinKit.setVisibility(View.GONE);
                    load.setVisibility(View.GONE);
                }
            }
        });

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "1");
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle(getString(R.string.app_name));
        mBuilder.setContentText("مرحبا بكم فى النسخة التجريبية الاولى");
        mBuilder.setDefaults(Notification.DEFAULT_ALL);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (mNotificationManager != null)
            mNotificationManager.notify(1, mBuilder.build());

    }
}
