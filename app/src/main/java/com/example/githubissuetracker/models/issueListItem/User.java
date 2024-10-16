package com.example.githubissuetracker.models.issueListItem;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private long id;

    @SerializedName("node_id")
    private String nodeId;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("gravatar_id")
    private String gravatarId;

    @SerializedName("url")
    private String url;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("followers_url")
    private String followersUrl;

    @SerializedName("following_url")
    private String followingUrl;

    @SerializedName("gists_url")
    private String gistsUrl;

    @SerializedName("starred_url")
    private String starredUrl;

    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;

    @SerializedName("organizations_url")
    private String organizationsUrl;

    @SerializedName("repos_url")
    private String reposUrl;

    @SerializedName("events_url")
    private String eventsUrl;

    @SerializedName("received_events_url")
    private String receivedEventsUrl;

    @SerializedName("type")
    private String type;

    @SerializedName("site_admin")
    private boolean siteAdmin;

    public String getLogin() { return login; }
    public void setLogin(String value) { this.login = value; }

    public long getId() { return id; }
    public void setId(long value) { this.id = value; }

    public String getNodeId() { return nodeId; }
    public void setNodeId(String value) { this.nodeId = value; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String value) { this.avatarUrl = value; }

    public String getGravatarId() { return gravatarId; }
    public void setGravatarId(String value) { this.gravatarId = value; }

    public String getUrl() { return url; }
    public void setUrl(String value) { this.url = value; }

    public String getHtmlUrl() { return htmlUrl; }
    public void setHtmlUrl(String value) { this.htmlUrl = value; }

    public String getFollowersUrl() { return followersUrl; }
    public void setFollowersUrl(String value) { this.followersUrl = value; }

    public String getFollowingUrl() { return followingUrl; }
    public void setFollowingUrl(String value) { this.followingUrl = value; }

    public String getGistsUrl() { return gistsUrl; }
    public void setGistsUrl(String value) { this.gistsUrl = value; }

    public String getStarredUrl() { return starredUrl; }
    public void setStarredUrl(String value) { this.starredUrl = value; }

    public String getSubscriptionsUrl() { return subscriptionsUrl; }
    public void setSubscriptionsUrl(String value) { this.subscriptionsUrl = value; }

    public String getOrganizationsUrl() { return organizationsUrl; }
    public void setOrganizationsUrl(String value) { this.organizationsUrl = value; }

    public String getReposUrl() { return reposUrl; }
    public void setReposUrl(String value) { this.reposUrl = value; }

    public String getEventsUrl() { return eventsUrl; }
    public void setEventsUrl(String value) { this.eventsUrl = value; }

    public String getReceivedEventsUrl() { return receivedEventsUrl; }
    public void setReceivedEventsUrl(String value) { this.receivedEventsUrl = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public boolean getSiteAdmin() { return siteAdmin; }
    public void setSiteAdmin(boolean value) { this.siteAdmin = value; }
}