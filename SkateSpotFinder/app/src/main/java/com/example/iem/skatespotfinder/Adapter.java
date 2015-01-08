package com.example.iem.skatespotfinder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 1/8/15.
 */
public class Adapter extends ArrayAdapter<Spot>{

    private final List<Spot> mSpots;
    private Activity mContext;

    public Adapter(Activity aContext, List<Spot> aSpots) {
        super(aContext, R.layout.row_layout, aSpots);
        this.mContext = aContext;
        this.mSpots = aSpots;
    }

    static class ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        protected ImageButton imageButton;
    }

    @Override
    public View getView(int aPosition, View aConvertView, ViewGroup aParent) {
        View lView = null;
        if (aConvertView == null) {
            LayoutInflater lInflator = mContext.getLayoutInflater();
            lView = lInflator.inflate(R.layout.row_layout, null);
            final ViewHolder lViewHolder = new ViewHolder();
            lViewHolder.imageView = (ImageView) lView.findViewById(R.id.imageView);
            lViewHolder.textView = (TextView) lView.findViewById(R.id.textView);
            lViewHolder.imageButton = (ImageButton) lView.findViewById(R.id.imageButton);
            lViewHolder.imageButton
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
            lView.setTag(lViewHolder);
            lViewHolder.imageButton.setTag(mSpots.get(aPosition));
        } else {
            lView = aConvertView;
            ((ViewHolder) lView.getTag()).imageButton.setTag(mSpots.get(aPosition));
        }
        ViewHolder holder = (ViewHolder) lView.getTag();
        holder.textView.setText(mSpots.get(aPosition).getDescription());
        holder.imageView.setImageResource(R.drawable.blank_board);
        return lView;
    }
}
