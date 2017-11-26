package com.acme.resource.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author nickk
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Thumbnail extends ResourceSupport {

    private Long id;

    private String path;

    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getResourceId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Thumbnail thumbnail = (Thumbnail) o;

        if (id != null ? !id.equals(thumbnail.id) : thumbnail.id != null) return false;
        if (path != null ? !path.equals(thumbnail.path) : thumbnail.path != null) return false;
        return extension != null ? extension.equals(thumbnail.extension) : thumbnail.extension == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Thumbnail{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
