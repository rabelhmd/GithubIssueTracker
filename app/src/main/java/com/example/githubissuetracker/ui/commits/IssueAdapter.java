package com.example.githubissuetracker.ui.commits;

import com.bumptech.glide.Glide;
import com.example.githubissuetracker.R;
import com.example.githubissuetracker.databinding.IssueItemBinding;
import com.example.githubissuetracker.models.issueListItem.IssueListItem;
import com.example.githubissuetracker.utilities.DateFormatter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NotifyDataSetChanged")
public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.IssueViewHolder> {

    private List<IssueListItem> issues = new ArrayList<>();
    private final IssueAdapterCallback callback;

    public IssueAdapter(IssueAdapterCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public IssueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.issue_item, parent, false);
        return new IssueViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull IssueViewHolder holder, int position) {
        IssueListItem issue = issues.get(position);
        holder.binding.issueTitle.setText(issue.getTitle());
        holder.binding.authorName.setText(issue.getUser().getLogin());
        Glide.with(holder.itemView.getContext())
                .load(issue.getUser().getAvatarUrl())  
                .placeholder(R.drawable.avatar_placeholder)  
                .error(R.drawable.avatar_placeholder)  
                .circleCrop()  
                .into(holder.binding.authorImage);
        holder.binding.createdAt.setText(DateFormatter.formatDate(issue.getCreatedAt()));
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.showDetailsView(issue);
            }
        });
        if (position == getItemCount()-1) {
            callback.loadNextPage();
        }
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    public void setItems(List<IssueListItem> issues) {
        if (issues == null) {
            return;
        }
        this.issues = issues;
        notifyDataSetChanged();
    }

    public static class IssueViewHolder extends RecyclerView.ViewHolder {
        IssueItemBinding binding;

        public IssueViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = IssueItemBinding.bind(itemView);
        }
    }
}

