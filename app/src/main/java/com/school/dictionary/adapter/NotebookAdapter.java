package com.school.dictionary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.school.dictionary.R;
import com.school.dictionary.content.TextContent;

import java.util.List;

public class NotebookAdapter extends RecyclerView.Adapter<NotebookAdapter.NotebookViewHolder> {

    private Context context;
    private List<TextContent> list;
    private NoteClickCallBack noteClickCallBack;

    public NotebookAdapter(Context context, List<TextContent> list, NoteClickCallBack noteClickCallBack) {
        this.context = context;
        this.list = list;
        this.noteClickCallBack = noteClickCallBack;
    }

    @NonNull
    @Override
    public NotebookAdapter.NotebookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.notebook_adapter_layout, viewGroup, false);
        return new NotebookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotebookAdapter.NotebookViewHolder viewHolder, int i) {
        viewHolder.setData(list.get(i), i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NotebookViewHolder extends RecyclerView.ViewHolder {

        private TextView content_tv;
        private ImageView star;
        private RelativeLayout adapter_re;

        public NotebookViewHolder(@NonNull View itemView) {
            super(itemView);
            content_tv = itemView.findViewById(R.id.content);
            star = itemView.findViewById(R.id.star);
            adapter_re = itemView.findViewById(R.id.adapter_re);
        }

        public void setData(TextContent content, final int position) {
            content_tv.setText(content.getContent().replace(" ", ""));

            if (content.getStar()) {
                star.setImageResource(R.drawable.ic_star_select);
            } else {
                star.setImageResource(R.drawable.ic_star_unselect);
            }

            adapter_re.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    noteClickCallBack.noteClickCallBack(position);
                }
            });
        }
    }

    public interface NoteClickCallBack {
        void noteClickCallBack(int position);
    }
}
