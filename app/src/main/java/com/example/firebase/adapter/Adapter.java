
package com.example.firebase.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firebase.R;
import com.example.firebase.ent.Example;
import com.example.firebase.ent.Videos;
import com.squareup.picasso.Picasso;


public class Adapter extends ListAdapter<Example, Adapter.Holder> {

    private OnItemClickListener listener;

    public Adapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Example> DIFF_CALLBACK = new DiffUtil.ItemCallback<Example>() {
        @Override
        public boolean areItemsTheSame(@NonNull Example example, @NonNull Example t1) {
            return example.getUser().getId() == (t1.getUser().getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Example example, @NonNull Example t1) {
            return example.getUser().getName().equals(t1.getUser().getName())
                    && example.getUser().getUsername().equals(t1.getUser().getUsername());
        }
    };

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Example current = getItem(position);

        for(Videos videos: current.getVideos()){
            holder.textView_video_title.setText(videos.getName());
            holder.textView_channel_name.setText(videos.getChannel().getName());
            Picasso.get().load(videos.getImageUrl()).into(holder.imageView_video_thumbnail);
            Picasso.get().load(videos.getChannel().getProfileImageUrl()).into(holder.imageView_channel_profile);

        }

//        holder.textView_video_title.setText(current.getVideos().get(0).getName());
//        holder.textView_channel_name.setText(current.getVideos().get(0).getChannel().getName());
//        Picasso.get().load(current.getVideos().get(0).getImageUrl()).into(holder.imageView_video_thumbnail);
//        Picasso.get().load(current.getVideos().get(0).getChannel().getProfileImageUrl()).into(holder.imageView_channel_profile);

    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView textView_video_title;
        private TextView textView_channel_name;
        private ImageView imageView_video_thumbnail;
        private ImageView imageView_channel_profile;

        public Holder(@NonNull View v) {
            super(v);

            this.textView_video_title = v.findViewById(R.id.textView_video_title);
            this.textView_channel_name = v.findViewById(R.id.textView_channel_name);
            this.imageView_video_thumbnail = v.findViewById(R.id.imageView_video_thumbnail);
            this.imageView_channel_profile = v.findViewById(R.id.imageView_channel_profile);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Example example);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
                                