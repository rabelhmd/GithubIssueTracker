package com.example.githubissuetracker.models.issueListItem;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PullRequest implements Serializable {
    @SerializedName("url")
    private String url;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("diff_url")
    private String diffUrl;

    @SerializedName("patch_url")
    private String patchUrl;

    @SerializedName("merged_at")
    private Object mergedAt;

    // Getters and setters
    public String getUrl() { return url; }
    public void setUrl(String value) { this.url = value; }

    public String getHtmlUrl() { return htmlUrl; }
    public void setHtmlUrl(String value) { this.htmlUrl = value; }

    public String getDiffUrl() { return diffUrl; }
    public void setDiffUrl(String value) { this.diffUrl = value; }

    public String getPatchUrl() { return patchUrl; }
    public void setPatchUrl(String value) { this.patchUrl = value; }

    public Object getMergedAt() { return mergedAt; }
    public void setMergedAt(Object value) { this.mergedAt = value; }
}