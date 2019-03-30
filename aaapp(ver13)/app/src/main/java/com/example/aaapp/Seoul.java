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

public class Seoul extends Font {

    ListView list;

    private String[] Namsan = new String[10];
    private String[] Changdeokgung = new String[10];
    private String[] Lotteworld = new String[10];

    private String[] names = new String[3];
    private String[] addrs = new String[3];
    private String[] times = new String[3];

    Integer[] images = { R.drawable.namsans,
            R.drawable.changduckgungs,
            R.drawable.lotteworlds
    };

    Integer[] musics = {R.raw.normal,R.raw.china, R.raw.cow, R.raw.everland, R.raw.feelup, R.raw.gwalmegi,
            R.raw.josun,R.raw.probangs,R.raw.science, R.raw.sheep};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seoul);

        final Intent intent = new Intent(this, JN.class);

        Namsan = getResources().getStringArray(R.array.Namsan);
        Changdeokgung = getResources().getStringArray(R.array.Changdeokgung);
        Lotteworld = getResources().getStringArray(R.array.Lotteworld);

        names = new String[]{Namsan[0], Changdeokgung[0], Lotteworld[0]};
        addrs = new String[]{Namsan[2], Changdeokgung[2], Lotteworld[2]};
        times = new String[]{Namsan[3], Changdeokgung[3], Lotteworld[3]};

        Seoul.CustomList adapter = new Seoul.CustomList(Seoul.this);
        list=(ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent.putExtra("title", Namsan);
                        intent.putExtra("image", images[0]);
                        intent.putExtra("music", musics[0]);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("title", Changdeokgung);
                        intent.putExtra("image", images[1]);
                        intent.putExtra("music", musics[6]);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("title", Lotteworld);
                        intent.putExtra("image", images[2]);
                        intent.putExtra("music", musics[4]);
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
