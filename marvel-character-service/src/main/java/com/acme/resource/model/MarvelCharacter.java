package com.acme.resource.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nickk
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarvelCharacter extends ResourceSupport {

    private Long id;
    private String name;
    private String description;
    private Thumbnail thumbnail;

    private List<ResourceLink> urls = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getResourceId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public List<ResourceLink> getUrls() {
        return urls;
    }

    public void setUrls(List<ResourceLink> urls) {
        this.urls = urls;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarvelCharacter character = (MarvelCharacter) o;

        if (id != null ? !id.equals(character.id) : character.id != null) return false;
        if (name != null ? !name.equals(character.name) : character.name != null) return false;
        if (description != null ? !description.equals(character.description) : character.description != null)
            return false;
        return thumbnail != null ? thumbnail.equals(character.thumbnail) : character.thumbnail == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MarvelCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail=" + thumbnail +
                '}';
    }
}