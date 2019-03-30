package com.example.aaapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class JN extends Font {
    class Point {
        // 위도
        public double x;
        // 경도
        public double y;
        public String addr;
        // 포인트를 받았는지 여부
        public boolean havePoint;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("geo:");
            builder.append(y);
            builder.append(",");
            builder.append(x);

            return builder.toString();
        }
    }

    private TextView[] textViews = new TextView[6];
    private HorizontalScrollView mChild;
    private Button button;
    private String[] titles = new String[10];
    private ImageView image;
    private ImageButton imageButton;
    private static MediaPlayer mp;
    int j =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jn);
        Intent intent = getIntent();
        titles = intent.getStringArrayExtra("title");


        textViews[0] = (TextView)findViewById(R.id.name);
        textViews[1] = (TextView)findViewById(R.id.intro);
        textViews[2] = (TextView)findViewById(R.id.addr);
        textViews[3] = (TextView)findViewById(R.id.time);
        textViews[4] = (TextView)findViewById(R.id.pay);
        textViews[5] = (TextView)findViewById(R.id.phone);
        button = (Button) findViewById(R.id.explain);
        image = (ImageView) findViewById(R.id.main_image);
        imageButton = (ImageButton)findViewById(R.id.sound);

        image.setImageResource(intent.getIntExtra("image", 0));

        for(int i=0 ; i<6;i++) {
            if(i==1)
                continue;
            textViews[i].setText(titles[i]);
        }

        mChild = (HorizontalScrollView) findViewById(R.id.child_View);
        mChild.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        mp= MediaPlayer.create(this,intent.getIntExtra("music",0));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
    }

    private Bitmap downloadUrl(String strUrl) throws IOException {
        Bitmap bitmap = null;
        InputStream iStream = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(iStream);
        } catch (Exception e) {
            Log.d("다운중",e.toString());
        } finally {
            iStream.close();
        }
        return bitmap;
    }

    class DownloadTask extends AsyncTask<String, Integer, Bitmap[]> {
        Bitmap bitmap[] = new Bitmap[4];

        @Override
        protected Bitmap[] doInBackground(String... url) {
            for(int i=0; i<4; i++) {
                try {
                    bitmap[i] = downloadUrl(url[i+6]);
                } catch (Exception e) {
                    Log.d("다운로드 받는중", e.toString());
                }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap[] bitmap) {
            ImageView iView[] = new ImageView[4];

            for(int i=0 ; i<4 ; i++) {
                iView[i]=(ImageView)findViewById(R.id.image1 + i);
                iView[i].setImageBitmap(bitmap[i]);
            }
        }
    }

    public void explain(View v) {

        if(button.getText().toString()=="접기") {
            button.setText("설명보기");
            textViews[1].setTextSize(0);
            textViews[1].setText("");
            textViews[1].setVisibility(View.INVISIBLE);
        }
        else {
            button.setText("접기");
            textViews[1].setTextSize(20);
            textViews[1].setText(titles[1]);
            textViews[1].setVisibility(View.VISIBLE);
        }
    }

    public void ImageDown(View v) {
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(titles);
    }

    private Point getPointFromGeoCoder(String addr) {
        Point point = new Point();
        point.addr = addr;

        Geocoder geocoder = new Geocoder(this);
        List<Address> listAddress;
        try {
            listAddress = geocoder.getFromLocationName(addr, 1);
        } catch (IOException e) {
            e.printStackTrace();
            point.havePoint = false;
            return point;
        }

        if (listAddress.isEmpty()) {
            point.havePoint = false;
            return point;
        }

        point.havePoint = true;
        point.x = listAddress.get(0).getLongitude();
        point.y = listAddress.get(0).getLatitude();
        return point;
    }

    public void map (View v) {
        Point point = new Point();
        point = getPointFromGeoCoder(titles[2]);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(point.toString()));
        startActivity(intent);
    }

    // 소리재생
    public void Sound(View v) {

        switch (j){

            case 0:
                imageButton.setImageResource(R.drawable.volumeoff);
                mp.pause();
                j=1;
                break;

            case 1:
                imageButton.setImageResource(R.drawable.volumeup);
                mp.start();
                j=0;
                break;
        }
    }

    public void Call(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+titles[5]));
        startActivity(intent);
    }

    public void URL_connect(View v) {
        if(titles[10].equals("X")) {
            Toast.makeText(this, "홈페이지가 없습니다.",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("" + titles[10]));
            startActivity(intent);
        }
    }

}