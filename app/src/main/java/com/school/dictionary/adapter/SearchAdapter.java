package com.school.dictionary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.school.dictionary.R;
import com.school.dictionary.content.TextContent;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private Context context;
    private List<TextContent> list;
    private SearchClickCallBack searchClickCallBack;

    public SearchAdapter(Context context, List<TextContent> list, SearchClickCallBack searchClickCallBack) {
        this.context = context;
        this.list = list;
        this.searchClickCallBack = searchClickCallBack;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_adapter_layout, viewGroup, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder viewHolder, int i) {
        viewHolder.setData(list.get(i), i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private TextView adapter_tv;
        private RelativeLayout adapter_re;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_tv = itemView.findViewById(R.id.adapter_tv);
            adapter_re = itemView.findViewById(R.id.adapter_re);
        }

        public void setData(TextContent content, final int position) {
            adapter_tv.setText(content.getContent().replace(" ", ""));

            adapter_re.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchClickCallBack.searchClickCallBack(position);
                }
            });
        }
    }

    public interface SearchClickCallBack {
        void searchClickCallBack(int position);
    }
}
