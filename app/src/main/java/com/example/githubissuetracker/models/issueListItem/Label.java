package com.example.githubissuetracker.models.issueListItem;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Label implements Serializable {
    @SerializedName("id")
    private long id;

    @SerializedName("node_id")
    private String nodeId;

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    @SerializedName("color")
    private String color;

    @SerializedName("default")
    private boolean labelDefault;

    @SerializedName("description")
    private String description;

    // Getters and setters
    public long getId() { return id; }
    public void setId(long value) { this.id = value; }

    public String getNodeId() { return nodeId; }
    public void setNodeId(String value) { this.nodeId = value; }

    public String getUrl() { return url; }
    public void setUrl(String value) { this.url = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getColor() { return color; }
    public void setColor(String value) { this.color = value; }

    public boolean getLabelDefault() { return labelDefault; }
    public void setLabelDefault(boolean value) { this.labelDefault = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }
}
