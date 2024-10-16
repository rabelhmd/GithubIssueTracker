package com.example.githubissuetracker.ui.markdown;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.githubissuetracker.R;
import com.example.githubissuetracker.databinding.ActivityMarkdownViewerBinding;
import com.example.githubissuetracker.models.issueListItem.IssueListItem;
import io.noties.markwon.Markwon;


public class IssueDetailsViewer extends AppCompatActivity {
    private ActivityMarkdownViewerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMarkdownViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(getIntent().getExtras() != null) {
            IssueListItem item = parseIntentItem();
            if (item.getTitle() != null) {
                setTitle(item.getTitle());
            }
            if (item.getBody() != null) {
                setDetails(item.getBody());
            }
        }
    }

    private void setTitle(String title) {
        binding.toolbar.setTitle(title);
    }

    private void setDetails(String details) {
        Markwon.create(this).setMarkdown(binding.markdownTextView, details);
    }

    private IssueListItem parseIntentItem() {
        return (IssueListItem) getIntent().getSerializableExtra("issue_details_info");
    }
}