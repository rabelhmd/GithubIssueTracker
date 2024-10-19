package com.rabelhmd.githubissuetracker.models.issueListItem;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Reactions implements Serializable {
    @SerializedName("url")
    private String url;

    @SerializedName("total_count")
    private long totalCount;

    @SerializedName("+1")
    private long the1;

    @SerializedName("-1")
    private long reactions1;

    @SerializedName("laugh")
    private long laugh;

    @SerializedName("hooray")
    private long hooray;

    @SerializedName("confused")
    private long confused;

    @SerializedName("heart")
    private long heart;

    @SerializedName("rocket")
    private long rocket;

    @SerializedName("eyes")
    private long eyes;

    // Getters and setters
    public String getUrl() { return url; }
    public void setUrl(String value) { this.url = value; }

    public long getTotalCount() { return totalCount; }
    public void setTotalCount(long value) { this.totalCount = value; }

    public long getThe1() { return the1; }
    public void setThe1(long value) { this.the1 = value; }

    public long getReactions1() { return reactions1; }
    public void setReactions1(long value) { this.reactions1 = value; }

    public long getLaugh() { return laugh; }
    public void setLaugh(long value) { this.laugh = value; }

    public long getHooray() { return hooray; }
    public void setHooray(long value) { this.hooray = value; }

    public long getConfused() { return confused; }
    public void setConfused(long value) { this.confused = value; }

    public long getHeart() { return heart; }
    public void setHeart(long value) { this.heart = value; }

    public long getRocket() { return rocket; }
    public void setRocket(long value) { this.rocket = value; }

    public long getEyes() { return eyes; }
    public void setEyes(long value) { this.eyes = value; }
}