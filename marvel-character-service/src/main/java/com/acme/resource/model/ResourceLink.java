package com.acme.resource.model;

import java.io.Serializable;

/**
 * @author nickk
 */

public class ResourceLink implements Serializable {

    private static final long serialVersionUID = 9154938577901454878L;

    private String url;
    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceLink that = (ResourceLink) o;

        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResourceLink{" +
                "url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}