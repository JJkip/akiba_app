package com.sourcey.akiba;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class SelectedChallengesFragment extends Fragment{
    private SectionedRecyclerViewAdapter sectionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_selected_challenges, container, false);

        sectionAdapter = new SectionedRecyclerViewAdapter();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    class ChallengesSection extends StatelessSection {
        final static int FIFTY_BOB_CHALLEGE = 0;
        final static int HUNDRED_BOB_CHALLENGE = 1;
        final int topic;

        String title;
        List<String> list;
        int imgPlaceholderResId;

        public ChallengesSection(int topic) {
            super(R.layout.section_ex2_header, R.layout.section_ex2_footer, R.layout.section_ex2_item);

            this.topic = topic;

            switch (topic) {
                case FIFTY_BOB_CHALLEGE:
                    this.title = getString(R.string.fifty_bob_challenge);
                    this.list = getChallenge(R.array.fifty_bob_challenge);
                    this.imgPlaceholderResId = R.drawable.ic_public_black_48dp;
                    break;
                case HUNDRED_BOB_CHALLENGE:
                    this.title = getString(R.string.hundred_bob_challenge);
                    this.list = getChallenge(R.array.hundred_bob_challenge);
                    this.imgPlaceholderResId = R.drawable.ic_business_black_48dp;
                    break;
            }

        }

        private List<String> getChallenge(int arrayResource) {
            return new ArrayList<>(Arrays.asList(getResources().getStringArray(arrayResource)));
        }
        @Override
        public int getContentItemsTotal() {
            return list.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            String[] item = list.get(position).split("\\|");

            itemHolder.tvHeader.setText(item[0]);
            itemHolder.tvDate.setText(item[1]);
            itemHolder.imgItem.setImageResource(imgPlaceholderResId);

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on position #%s of Section %s", sectionAdapter.getSectionPosition(itemHolder.getAdapterPosition()), title), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);
        }

        @Override
        public RecyclerView.ViewHolder getFooterViewHolder(View view) {
            return new FooterViewHolder(view);
        }

        @Override
        public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;

            footerHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on footer of Section %s", title), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        public HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;

        public FooterViewHolder(View view) {
            super(view);

            rootView = view;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final ImageView imgItem;
        private final TextView tvHeader;
        private final TextView tvDate;

        public ItemViewHolder(View view) {
            super(view);

            rootView = view;
            imgItem = (ImageView) view.findViewById(R.id.imgItem);
            tvHeader = (TextView) view.findViewById(R.id.tvHeader);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
        }
    }

}
