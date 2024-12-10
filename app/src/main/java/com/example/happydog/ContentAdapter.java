// ContentAdapter.java
package com.example.happydog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> implements Filterable {
    private Context context;
    private List<Content> contentList;
    private List<Content> contentListFull;

    public ContentAdapter(Context context, List<Content> contentList) {
        this.context = context;
        this.contentList = contentList;
        contentListFull = new ArrayList<>(contentList);
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_content, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        Content content = contentList.get(position);
        holder.title.setText(content.getTitle());
        holder.description.setText(content.getDescription());
        holder.image.setImageResource(content.getImageResId());

        // Set an onClickListener for the image
        holder.image.setOnClickListener(v -> {
            Intent intent = new Intent(context, ContentDetailActivity.class);
            intent.putExtra("imageResId", content.getImageResId());
            intent.putExtra("title", content.getTitle());
            intent.putExtra("description", content.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    @Override
    public Filter getFilter() {
        return contentFilter;
    }

    private Filter contentFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Content> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(contentListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Content item : contentListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            contentList.clear();
            contentList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, description;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }
}
