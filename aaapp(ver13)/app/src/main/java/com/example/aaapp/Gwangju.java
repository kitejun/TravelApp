package com.example.aaapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Gwangju extends Font {

    ListView list;

    private String[] mudeungmountain = new String[10];
    private String[] park518 = new String[10];
    private String[] uchipark = new String[10];

    private String[] names = new String[3];
    private String[] addrs = new String[3];
    private String[] times = new String[3];

    Integer[] images = { R.drawable.mudeungmountain,
            R.drawable.park518,
            R.drawable.uchipark
    };

    Integer[] musics = {R.raw.normal,R.raw.china, R.raw.cow, R.raw.everland, R.raw.feelup, R.raw.gwalmegi,
            R.raw.josun,R.raw.probangs,R.raw.science, R.raw.sheep};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gwangju);
        final Intent intent = new Intent(this, JN.class);

        mudeungmountain = getResources().getStringArray(R.array.MudeungMountain);
        park518 = getResources().getStringArray(R.array.Park518);
        uchipark = getResources().getStringArray(R.array.UchiPark);

        names = new String[]{mudeungmountain[0], park518[0], uchipark[0]};
        addrs = new String[]{mudeungmountain[2], park518[2], uchipark[2]};
        times = new String[]{mudeungmountain[3], park518[3], uchipark[3]};

        Gwangju.CustomList adapter = new Gwangju.CustomList(Gwangju.this);
        list=(ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent.putExtra("title", mudeungmountain);
                        intent.putExtra("image", images[0]);
                        intent.putExtra("music", musics[0]);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("title", park518);
                        intent.putExtra("image", images[1]);
                        intent.putExtra("music", musics[4]);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("title", uchipark);
                        intent.putExtra("image", images[2]);
                        intent.putExtra("music", musics[0]);
                        startActivity(intent);
                        break;
                }

            }
        });
    }

    class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList(Activity context) {
            super(context, R.layout.listitem, names);
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView name = (TextView) rowView.findViewById(R.id.name);
            TextView addr = (TextView) rowView.findViewById(R.id.addr);
            TextView time = (TextView) rowView.findViewById(R.id.time);

            name.setText(names[position]);
            addr.setText(addrs[position]);
            time.setText(times[position]);
            imageView.setImageResource(images[position]);
            return rowView;
        }
    }

}
