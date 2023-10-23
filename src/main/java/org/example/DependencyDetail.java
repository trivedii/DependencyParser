package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.w3c.dom.Node;

import java.util.Objects;

public class DependencyDetail {
    private String artifactId;
    private String groupId;
    private String version;

    public DependencyDetail(String artifactId, String groupId, String version) {
        this.artifactId = artifactId;
        this.groupId = groupId;
        this.version = version;
    }

    public DependencyDetail(Node artifactIdNode, Node groupIdNode, Node versionNode) {
        this.artifactId = Objects.nonNull(artifactIdNode) ? artifactIdNode.getTextContent() : "";
        this.groupId =    Objects.nonNull(groupIdNode) ? groupIdNode.getTextContent() : "";
        this.version =    Objects.nonNull(versionNode) ? versionNode.getTextContent() : "";
    }

    @Override
    public String toString(){
        try {
            return JsonUtil.convertToJsonString(this);
        } catch (JsonProcessingException e) {
            return "artifactId: " + this.artifactId
                    + "groupId: " + this.groupId
                    + "version: " + this.version;
        }
    }
    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
