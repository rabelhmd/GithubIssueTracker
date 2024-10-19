package com.rabelhmd.githubissuetracker.ui.markdown;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.rabelhmd.githubissuetracker.R;
import com.rabelhmd.githubissuetracker.databinding.ActivityMarkdownViewerBinding;
import com.rabelhmd.githubissuetracker.models.issueListItem.IssueListItem;
import com.rabelhmd.githubissuetracker.utilities.DateFormatter;

import io.noties.markwon.Markwon;


public class IssueDetailsViewer extends AppCompatActivity {
    private ActivityMarkdownViewerBinding binding;
    private Markwon markwon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        markwon = Markwon.create(this);
        binding = ActivityMarkdownViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.toolbar.setTitle(getString(R.string.issue_details));
        if(getIntent().getExtras() != null) {
            IssueListItem issue = parseIntentItem();
            setTitle(issue.getTitle());
            setDetails(issue.getBody());
            binding.issueDetailsContainer.tvIssueNumber.
                    setText(getString(R.string.issue_number, issue.getId()));
            setState(issue.getState());
            setIssueInfo(issue.getUser().getLogin(), issue.getCreatedAt());
        }
    }

    private void setTitle(@Nullable String title) {
        if(title == null) return;
        binding.issueDetailsContainer.tvTitle.setText(title);
    }
    
    private void setDetails(@Nullable String details) {
        if(details == null) return;
        markwon.setMarkdown(binding.issueDetailsContainer.tvIssueDescription, details);
    }

    private IssueListItem parseIntentItem() {
        return (IssueListItem) getIntent().getSerializableExtra("issue_details_info");
    }

    private void setState(@Nullable String state) {
        if(state == null) return;
        if(state.toLowerCase().equals("open")) {
            showOpenState();
        }
        else if(state.toLowerCase().equals("closed")) {
            showClosedState();
        }
    }

    private void setIssueInfo(String userHandle, String createdAt) {
        binding.issueDetailsContainer.tvIssueInfo.setText(getString(R.string.issue_info_author_and_date, userHandle, DateFormatter.formatDate(createdAt)));
    }

    private void showOpenState() {
        Glide.with(binding.issueDetailsContainer.ivStateIcon)
                .load(R.drawable.ic_dot_in_circle_16)
                .into(binding.issueDetailsContainer.ivStateIcon);
        binding.issueDetailsContainer.tvState.setText(getString(R.string.open));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.issueDetailsContainer.cvStateChip.setCardBackgroundColor(getColor(R.color.state_open));
        }
    }

    private void showClosedState() {
        Glide.with(binding.issueDetailsContainer.ivStateIcon)
                .load(R.drawable.ic_tick_in_circle_16)
                .into(binding.issueDetailsContainer.ivStateIcon);

        binding.issueDetailsContainer.tvState.setText(getString(R.string.closed));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.issueDetailsContainer.cvStateChip.setCardBackgroundColor(getColor(R.color.state_closed));
        }
    }
}