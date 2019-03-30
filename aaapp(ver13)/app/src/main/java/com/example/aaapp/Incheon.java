package com.example.aaapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Incheon extends Font {

    ListView list;

    private String[] Bomunsa = new String[10];
    private String[] FairtaleVillag = new String[10];
    private String[] ChinaTown = new String[10];

    private String[] names = new String[3];
    private String[] addrs = new String[3];
    private String[] times = new String[3];

    Integer[] images = { R.drawable.bomunsa,
            R.drawable.fairtalevillag,
            R.drawable.chinatown
    };

    Integer[] musics = {R.raw.normal,R.raw.china, R.raw.cow, R.raw.everland, R.raw.feelup, R.raw.gwalmegi,
            R.raw.josun,R.raw.probangs,R.raw.science, R.raw.sheep};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incheon);
        final Intent intent = new Intent(this, JN.class);

        Bomunsa = getResources().getStringArray(R.array.Bomunsa);
        FairtaleVillag = getResources().getStringArray(R.array.FairtaleVillag);
        ChinaTown = getResources().getStringArray(R.array.ChinaTown);

        names = new String[]{Bomunsa[0], FairtaleVillag[0], ChinaTown[0]};
        addrs = new String[]{Bomunsa[2], FairtaleVillag[2], ChinaTown[2]};
        times = new String[]{Bomunsa[3], FairtaleVillag[3], ChinaTown[3]};

        Incheon.CustomList adapter = new Incheon.CustomList(Incheon.this);
        list=(ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent.putExtra("title", Bomunsa);
                        intent.putExtra("image", images[0]);
                        intent.putExtra("music", musics[0]);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("title", FairtaleVillag);
                        intent.putExtra("image", images[1]);
                        intent.putExtra("music", musics[0]);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("title", ChinaTown);
                        intent.putExtra("image", images[2]);
                        intent.putExtra("music", musics[1]);
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
