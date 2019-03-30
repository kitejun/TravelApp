package com.example.aaapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class MainActivity extends Font {
    Intent seoul_intent;
    Intent Incheon_intent;
    Intent gg_intent;
    Intent daejeon_intent;
    Intent gyeongsangbukdo_intent;
    Intent daegu_intent;
    Intent gyeongsangnamdo_intent;
    Intent busan_intent;
    Intent jeonnam_intent;
    Intent gwangju_intent;
    Intent ulsan_intent;
    Intent chungnam_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gg_intent = new Intent(this, Gyeonggido.class);
        seoul_intent = new Intent(this, Seoul.class);
        Incheon_intent = new Intent(this, Incheon.class);
        daejeon_intent = new Intent(this, Daejeon.class);
        gyeongsangbukdo_intent = new Intent(this, Gyeongsangbukdo.class);
        daegu_intent = new Intent(this, Daegu.class);
        gyeongsangnamdo_intent = new Intent(this, Gyeongsangnamdo.class);
        busan_intent = new Intent(this, Busan.class);
        jeonnam_intent = new Intent(this, Jeonnam.class);
        gwangju_intent = new Intent(this, Gwangju.class);
        ulsan_intent = new Intent(this, Ulsan.class);
        chungnam_intent = new Intent(this, Chungnam.class);
    }

    public void Area(View v){

        switch (v.getId()){
            case R.id.jn:
                PopupMenu jn_popMenu = new PopupMenu(this, v);
                jn_popMenu.getMenuInflater().inflate(R.menu.jn_popup,jn_popMenu.getMenu());
                jn_popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.jeonnam:
                                startActivity(jeonnam_intent);
                                break;
                            case R.id.gwangju:
                                startActivity(gwangju_intent);
                                break;
                        }
                        return false;
                    }
                });
                jn_popMenu.show();
                break;


            case R.id.gg:
                PopupMenu gg_popMenu = new PopupMenu(this, v);
                gg_popMenu.getMenuInflater().inflate(R.menu.gg_popup,gg_popMenu.getMenu());
                gg_popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       switch (item.getItemId()){
                           case R.id.incheon:
                               startActivity(Incheon_intent);
                               break;
                           case R.id.seoul:
                               startActivity(seoul_intent);
                               break;
                           case R.id.gyeongi:
                               startActivity(gg_intent);
                               break;
                       }
                       return false;
                   }
               });
                gg_popMenu.show();
                break;

            case R.id.gw:
                startActivity(new Intent(this, Gangwondo.class));
                break;

            case R.id.cn:
                PopupMenu cn_popMenu = new PopupMenu(this, v);
                cn_popMenu.getMenuInflater().inflate(R.menu.cn_popup,cn_popMenu.getMenu());
                cn_popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.daejeon:
                                startActivity(daejeon_intent);
                                break;
                            case R.id.cn:
                                startActivity(chungnam_intent);
                                break;
                        }
                        return false;
                    }
                });
                cn_popMenu.show();
                break;

            case R.id.cb:
                startActivity(new Intent(this, Chungbuk.class));

            case R.id.gb:
                PopupMenu gb_popMenu = new PopupMenu(this, v);
                gb_popMenu.getMenuInflater().inflate(R.menu.gb_popup, gb_popMenu.getMenu());
                gb_popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.gyeongsangbukdo:
                                startActivity(gyeongsangbukdo_intent);
                                break;
                            case R.id.daegu:
                                startActivity(daegu_intent);
                                break;
                            case R.id.ulsan:
                                startActivity(ulsan_intent);
                        }
                        return false;
                    }
                });
                gb_popMenu.show();
                break;

            case R.id.jb:
                startActivity(new Intent(this, Jeollabukdo.class));
                break;

            case R.id.gn:
                PopupMenu gn_popMenu = new PopupMenu(this, v);
                gn_popMenu.getMenuInflater().inflate(R.menu.gn_popup, gn_popMenu.getMenu());
                gn_popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.gyeongsangnamdo:
                                startActivity(gyeongsangnamdo_intent);
                                break;
                            case R.id.busan:
                                startActivity(busan_intent);
                                break;
                        }
                        return false;
                    }
                });
                gn_popMenu.show();
                break;

            case R.id.jj:
                startActivity(new Intent(this, Jeju.class));
                break;

        }
    }

}

