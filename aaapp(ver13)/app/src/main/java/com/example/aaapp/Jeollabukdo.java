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

public class Jeollabukdo extends Font {

    ListView list;

    private String[] Hanok = new String[10];
    private String[] Imsil = new String[10];
    private String[] Gwanghanru = new String[10];

    private String[] names = new String[3];
    private String[] addrs = new String[3];
    private String[] times = new String[3];

    Integer[] images = { R.drawable.hanok,
            R.drawable.imsil,
            R.drawable.gwanghanru
    };

    Integer[] musics = {R.raw.normal,R.raw.china, R.raw.cow, R.raw.everland, R.raw.feelup, R.raw.gwalmegi,
            R.raw.josun,R.raw.probangs,R.raw.science, R.raw.sheep};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeollabukdo);

        final Intent intent = new Intent(this, JN.class);

        Hanok = getResources().getStringArray(R.array.Hanok);
        Imsil = getResources().getStringArray(R.array.Imsil);
        Gwanghanru = getResources().getStringArray(R.array.Gwanghanru);

        names = new String[]{Hanok[0], Imsil[0], Gwanghanru[0]};
        addrs = new String[]{Hanok[2], Imsil[2], Gwanghanru[2]};
        times = new String[]{Hanok[3], Imsil[3], Gwanghanru[3]};

        Jeollabukdo.CustomList adapter = new Jeollabukdo.CustomList(Jeollabukdo.this);
        list=(ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent.putExtra("title", Hanok);
                        intent.putExtra("image", images[0]);
                        intent.putExtra("music", musics[6]);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("title", Imsil);
                        intent.putExtra("image", images[1]);
                        intent.putExtra("music", musics[0]);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("title", Gwanghanru);
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
