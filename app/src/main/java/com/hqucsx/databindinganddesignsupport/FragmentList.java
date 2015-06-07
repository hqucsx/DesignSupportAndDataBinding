package com.hqucsx.databindinganddesignsupport;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by csx on 15/6/7.
 */
public class FragmentList extends Fragment {

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;
    private View rootView;
    private ArrayList<Data> datas;

    private MySimpleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, rootView);
        setupRecyclerView();
        return rootView;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        datas = new ArrayList<>();
        Data data;
        for (int i = 0; i < 20; i++) {
            data = new Data("Title" + i, "成为一个更好的人，成为一个有用的人" + i, Cheeses.getRandomCheeseDrawable());

            datas.add(data);
        }

        adapter = new MySimpleAdapter(getActivity(),datas);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public static class MySimpleAdapter extends RecyclerView.Adapter<MySimpleAdapter.ViewHolder> {

        private ArrayList<Data> datas;
        private Context context;

        public MySimpleAdapter(Context context,ArrayList<Data> datas) {
            this.context = context;
            this.datas = datas;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvTitle.setText(datas.get(position).getTitle());
            holder.tvDescription.setText(datas.get(position).getDescription());

            Glide.with(context)
                    .load(datas.get(position).getPoster())
                    .fitCenter()
                    .into(holder.ivPoster);
            final View view = holder.view;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 20, 0);
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
//                            mContext.startActivity(new Intent(mContext, DetailActivity.class));
                        }
                    });
                    animator.start();
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }


        /**
         * ViewHolder
         */
        public static class ViewHolder extends RecyclerView.ViewHolder {
            View view;
            @InjectView(R.id.iv_poster)
            ImageView ivPoster;
            @InjectView(R.id.tv_title)
            TextView tvTitle;
            @InjectView(R.id.tv_description)
            TextView tvDescription;
            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                ButterKnife.inject(this,itemView);
            }
        }
    }


}
