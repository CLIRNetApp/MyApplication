package com.clirnet.admin.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

public class MainActivity extends AppCompatActivity {

    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.fullscreen_content);

        webView = (WebView)findViewById(R.id.fullscreen_content);
        String apikey=getResources().getString(R.string.apikey);
           Log.e("apikey",""+apikey);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("file:///android_asset/www/index.html");
        //you can also link to a website. Example:
        //webView.loadUrl("www.google.com");
        //I have included web permissions in the AndroidManifest.xml
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabasePath("/data/data/"+this.getPackageName()+"/databases/");

        Button openDialogButton = (Button)findViewById(R.id.button1);

        openDialogButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(getApplicationContext());

                dialogBuilder
                        .withTitle("Modal Dialog")                                  //.withTitle(null)  no title
                        .withTitleColor("#FFFFFF")                                  //def
                        .withDividerColor("#11000000")                              //def
                        .withMessage("This is a modal Dialog.")                     //.withMessage(null)  no Msg
                        .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                        .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)
                        .withIcon(getResources().getDrawable(R.drawable.languge))
                        .withDuration(700)                                          //def
                        .withEffect(Effectstype.Fall)                                         //def Effectstype.Slidetop
                        .withButton1Text("OK")                                      //def gone
                        .withButton2Text("Cancel")                                  //def gone
                        .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                        .setCustomView(R.layout.custom_view,v.getContext())         //.setCustomView(View or ResId,context)
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setButton2Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(),"i'm btn2",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                /*final Dialog openDialog = new Dialog(MainActivity.this);
                openDialog.setContentView(R.layout.cust_dialog);
                //openDialog.setTitle("Custom Dialog Box");
                TextView dialogTextContent = (TextView)openDialog.findViewById(R.id.dialog_text);
                ImageView dialogImage = (ImageView)openDialog.findViewById(R.id.dialog_image);
                Button dialogCloseButton = (Button)openDialog.findViewById(R.id.dialog_button);
                dialogCloseButton.setOnClickListener(new OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        openDialog.dismiss();
                    }
                });
                openDialog.show();*/
            }

        });


    }

    @Override
    public void onBackPressed()
    {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


}
