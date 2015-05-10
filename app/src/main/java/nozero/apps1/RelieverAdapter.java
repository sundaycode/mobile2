package nozero.apps1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramadhanario on 26/04/2015.
 */
public class RelieverAdapter extends ArrayAdapter{
    List list = new ArrayList();

    public RelieverAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class DataHandler{
        TextView name;
        TextView content;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHandler handler;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.reliever, parent, false);
            handler = new DataHandler();
            handler.name = (TextView)row.findViewById(R.id.textView9);
            handler.content = (TextView)row.findViewById(R.id._message);
            row.setTag(handler);
        }
        else{
            handler = (DataHandler) row.getTag();
        }

        RelieverProvider pesan;
        pesan = (RelieverProvider)this.getItem(position);
        handler.name.setText(pesan.getNama());
        handler.content.setText(pesan.getIsi());

        return row;
    }
}
