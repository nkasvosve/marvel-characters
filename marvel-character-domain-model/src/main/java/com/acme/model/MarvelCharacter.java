package com.acme.model;

import com.querydsl.core.annotations.QueryEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nickk
 */

@QueryEntity
@Entity
public class MarvelCharacter extends BaseEntity{

    @Id
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column(length = 1024)
    private String description;

    @Column(length = 2048)
    public String powers;

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Thumbnail thumbnail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarvelCharacter that = (MarvelCharacter) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (powers != null ? !powers.equals(that.powers) : that.powers != null) return false;
        if (thumbnail != null ? !thumbnail.equals(that.thumbnail) : that.thumbnail != null) return false;
        return resourceLinks != null ? resourceLinks.equals(that.resourceLinks) : that.resourceLinks == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (powers != null ? powers.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        result = 31 * result + (resourceLinks != null ? resourceLinks.hashCode() : 0);
        return result;
    }

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ResourceLink> resourceLinks = new ArrayList<>();

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<ResourceLink> getResourceLinks() {
        return resourceLinks;
    }

    public void setResourceLinks(List<ResourceLink> resourceLinks) {
        this.resourceLinks = resourceLinks;
    }

    @Override
    public String toString() {
        return "MarvelCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", powers='" + powers + '\'' +
                ", thumbnail=" + thumbnail +
                ", resourceLinks=" + resourceLinks +
                '}';
    }
}