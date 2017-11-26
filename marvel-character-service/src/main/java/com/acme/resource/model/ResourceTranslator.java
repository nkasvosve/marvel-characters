package com.acme.resource.model;

import java.util.List;
import java.util.Optional;

/**
 * @author nickk
 */
public class ResourceTranslator {

    public static Optional<ResourceLink> fromResourceLinkModel(com.acme.model.ResourceLink source) {
        if (source == null) {
            return Optional.empty();
        }

        ResourceLink target = new ResourceLink();
        target.setType(source.getType());
        target.setUrl(source.getUrl());
        return Optional.of(target);
    }

    public static Optional<Thumbnail> fromThumbnailModel(com.acme.model.Thumbnail source) {
        if (source == null) {
            return Optional.empty();
        }

        Thumbnail target = new Thumbnail();
        target.setPath(source.getPath());
        target.setExtension(source.getExtension());
        return Optional.of(target);
    }

    public static Optional<MarvelCharacter> fromCharacterModel(com.acme.model.MarvelCharacter source) {
        if (source == null) {
            return Optional.empty();
        }

        MarvelCharacter target = new MarvelCharacter();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        Optional<Thumbnail> thumbnail = fromThumbnailModel(source.getThumbnail());
        if (thumbnail.isPresent()) {
            target.setThumbnail(thumbnail.get());
        }

        List<com.acme.model.ResourceLink> links = source.getResourceLinks();
        for (com.acme.model.ResourceLink link : links) {
            Optional<ResourceLink> resourceLink = fromResourceLinkModel(link);
            if (resourceLink.isPresent()) {
                target.getUrls().add(resourceLink.get());
            }
        }
        return Optional.of(target);
    }
}
