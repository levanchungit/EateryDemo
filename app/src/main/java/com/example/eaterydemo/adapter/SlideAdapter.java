package com.example.eaterydemo.adapter;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.eaterydemo.R;

import java.util.ArrayList;

public class SlideAdapter extends PagerAdapter {

    ImageView ivKhuyenMai;
    int[] images;
    LayoutInflater layoutInflater;
    Context context;

    public SlideAdapter(int[] images, Context context) {
        this.images = images;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.slide_images,container,false);
        ivKhuyenMai = view.findViewById(R.id.ivKhuyenMai);

        ivKhuyenMai.setImageDrawable(context.getDrawable(images[position]));
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
