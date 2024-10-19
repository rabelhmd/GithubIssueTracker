package com.rabelhmd.githubissuetracker.models.issueListItem;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

public class IssueListItem implements Serializable {
    @SerializedName("url")
    private String url;

    @SerializedName("repository_url")
    private String repositoryUrl;

    @SerializedName("labels_url")
    private String labelsUrl;

    @SerializedName("comments_url")
    private String commentsUrl;

    @SerializedName("events_url")
    private String eventsUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("id")
    private long id;

    @SerializedName("node_id")
    private String nodeId;

    @SerializedName("number")
    private long number;

    @SerializedName("title")
    private String title;

    @SerializedName("user")
    private User user;

    @SerializedName("labels")
    private List<Label> labels;

    @SerializedName("state")
    private String state;

    @SerializedName("locked")
    private boolean locked;

    @SerializedName("assignee")
    private User assignee;

    @SerializedName("assignees")
    private List<User> assignees;

    @SerializedName("milestone")
    private Object milestone;

    @SerializedName("comments")
    private long comments;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("closed_at")
    private Object closedAt;

    @SerializedName("author_association")
    private String authorAssociation;

    @SerializedName("active_lock_reason")
    private Object activeLockReason;

    @SerializedName("body")
    private String body;

    @SerializedName("closed_by")
    private Object closedBy;

    @SerializedName("reactions")
    private Reactions reactions;

    @SerializedName("timeline_url")
    private String timelineUrl;

    @SerializedName("performed_via_github_app")
    private Object performedViaGithubApp;

    @SerializedName("state_reason")
    private Object stateReason;

    @SerializedName("draft")
    private Boolean draft;

    @SerializedName("pull_request")
    private PullRequest pullRequest;

    // Getters and setters
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getRepositoryUrl() { return repositoryUrl; }
    public void setRepositoryUrl(String repositoryUrl) { this.repositoryUrl = repositoryUrl; }

    public String getLabelsUrl() { return labelsUrl; }
    public void setLabelsUrl(String labelsUrl) { this.labelsUrl = labelsUrl; }

    public String getCommentsUrl() { return commentsUrl; }
    public void setCommentsUrl(String commentsUrl) { this.commentsUrl = commentsUrl; }

    public String getEventsUrl() { return eventsUrl; }
    public void setEventsUrl(String eventsUrl) { this.eventsUrl = eventsUrl; }

    public String getHtmlUrl() { return htmlUrl; }
    public void setHtmlUrl(String htmlUrl) { this.htmlUrl = htmlUrl; }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNodeId() { return nodeId; }
    public void setNodeId(String nodeId) { this.nodeId = nodeId; }

    public long getNumber() { return number; }
    public void setNumber(long number) { this.number = number; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Label> getLabels() { return labels; }
    public void setLabels(List<Label> labels) { this.labels = labels; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public boolean getLocked() { return locked; }
    public void setLocked(boolean locked) { this.locked = locked; }

    public User getAssignee() { return assignee; }
    public void setAssignee(User assignee) { this.assignee = assignee; }

    public List<User> getAssignees() { return assignees; }
    public void setAssignees(List<User> assignees) { this.assignees = assignees; }

    public Object getMilestone() { return milestone; }
    public void setMilestone(Object milestone) { this.milestone = milestone; }

    public long getComments() { return comments; }
    public void setComments(long comments) { this.comments = comments; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    public Object getClosedAt() { return closedAt; }
    public void setClosedAt(Object closedAt) { this.closedAt = closedAt; }

    public String getAuthorAssociation() { return authorAssociation; }
    public void setAuthorAssociation(String authorAssociation) { this.authorAssociation = authorAssociation; }

    public Object getActiveLockReason() { return activeLockReason; }
    public void setActiveLockReason(Object activeLockReason) { this.activeLockReason = activeLockReason; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public Object getClosedBy() { return closedBy; }
    public void setClosedBy(Object closedBy) { this.closedBy = closedBy; }

    public Reactions getReactions() { return reactions; }
    public void setReactions(Reactions reactions) { this.reactions = reactions; }

    public String getTimelineUrl() { return timelineUrl; }
    public void setTimelineUrl(String timelineUrl) { this.timelineUrl = timelineUrl; }

    public Object getPerformedViaGithubApp() { return performedViaGithubApp; }
    public void setPerformedViaGithubApp(Object performedViaGithubApp) { this.performedViaGithubApp = performedViaGithubApp; }

    public Object getStateReason() { return stateReason; }
    public void setStateReason(Object stateReason) { this.stateReason = stateReason; }

    public Boolean getDraft() { return draft; }
    public void setDraft(Boolean draft) { this.draft = draft; }

    public PullRequest getPullRequest() { return pullRequest; }
    public void setPullRequest(PullRequest pullRequest) { this.pullRequest = pullRequest; }
}


