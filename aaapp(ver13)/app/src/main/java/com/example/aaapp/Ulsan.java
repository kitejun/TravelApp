package com.example.aaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Ulsan extends Font {

    ListView list;

    private String[] ganjeolgot = new String[10];
    private String[] Daewang = new String[10];
    private String[] Jasujeong = new String[10];

    private String[] names = new String[3];
    private String[] addrs = new String[3];
    private String[] times = new String[3];

    Integer[] images = { R.drawable.ganjeolgot,
            R.drawable.daewang,
            R.drawable.jasujeong
    };

    Integer[] musics = {R.raw.normal,R.raw.china, R.raw.cow, R.raw.everland, R.raw.feelup, R.raw.gwalmegi,
            R.raw.josun,R.raw.probangs,R.raw.science, R.raw.sheep};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulsan);

        final Intent intent = new Intent(this, JN.class);

        ganjeolgot = getResources().getStringArray(R.array.ganjeolgot);
        Daewang = getResources().getStringArray(R.array.Daewang);
        Jasujeong = getResources().getStringArray(R.array.Jasujeong);

        names = new String[]{ganjeolgot[0], Daewang[0], Jasujeong[0]};
        addrs = new String[]{ganjeolgot[2], Daewang[2], Jasujeong[2]};
        times = new String[]{ganjeolgot[3], Daewang[3], Jasujeong[3]};

        Ulsan.CustomList adapter = new Ulsan.CustomList(Ulsan.this);
        list=(ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent.putExtra("title", ganjeolgot);
                        intent.putExtra("image", images[0]);
                        intent.putExtra("music", musics[0]);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("title", Daewang);
                        intent.putExtra("image", images[1]);
                        intent.putExtra("music", musics[5]);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("title", Jasujeong);
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
