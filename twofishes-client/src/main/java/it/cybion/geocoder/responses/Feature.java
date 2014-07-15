package it.cybion.geocoder.responses;

import java.util.List;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class Feature {

    private String cc;

    private Geometry geometry;

    private String name;

    private String displayName;

    private int woeType;

    private List<Id> ids;

    private List<Name> names;

    private String highlightedName;

    private String matchedName;

    private String id;

    private Attributes attributes;

    private Long longId;

    private List<Long> longIds;

    private List<Long> parentIds;

    private Feature() {

    }

    public String getCc() {

        return cc;
    }

    public Geometry getGeometry() {

        return geometry;
    }

    public String getName() {

        return name;
    }

    public String getDisplayName() {

        return displayName;
    }

    public int getWoeType() {

        return woeType;
    }

    public List<Id> getIds() {

        return ids;
    }

    public List<Name> getNames() {

        return names;
    }

    public String getHighlightedName() {

        return highlightedName;
    }

    public String getMatchedName() {

        return matchedName;
    }

    public String getId() {

        return id;
    }

    public Attributes getAttributes() {

        return attributes;
    }

    public Long getLongId() {

        return longId;
    }

    public List<Long> getLongIds() {

        return longIds;
    }

    public List<Long> getParentIds() {

        return parentIds;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Feature feature = (Feature) o;

        if (woeType != feature.woeType) {
            return false;
        }
        if (attributes != null ? !attributes.equals(feature.attributes) :
                feature.attributes != null) {
            return false;
        }
        if (cc != null ? !cc.equals(feature.cc) : feature.cc != null) {
            return false;
        }
        if (displayName != null ? !displayName.equals(feature.displayName) :
                feature.displayName != null) {
            return false;
        }
        if (geometry != null ? !geometry.equals(feature.geometry) : feature.geometry != null) {
            return false;
        }
        if (highlightedName != null ? !highlightedName.equals(feature.highlightedName) :
                feature.highlightedName != null) {
            return false;
        }
        if (id != null ? !id.equals(feature.id) : feature.id != null) {
            return false;
        }
        if (ids != null ? !ids.equals(feature.ids) : feature.ids != null) {
            return false;
        }
        if (longId != null ? !longId.equals(feature.longId) : feature.longId != null) {
            return false;
        }
        if (longIds != null ? !longIds.equals(feature.longIds) : feature.longIds != null) {
            return false;
        }
        if (matchedName != null ? !matchedName.equals(feature.matchedName) :
                feature.matchedName != null) {
            return false;
        }
        if (name != null ? !name.equals(feature.name) : feature.name != null) {
            return false;
        }
        if (names != null ? !names.equals(feature.names) : feature.names != null) {
            return false;
        }
        if (parentIds != null ? !parentIds.equals(feature.parentIds) : feature.parentIds != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = cc != null ? cc.hashCode() : 0;
        result = 31 * result + (geometry != null ? geometry.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + woeType;
        result = 31 * result + (ids != null ? ids.hashCode() : 0);
        result = 31 * result + (names != null ? names.hashCode() : 0);
        result = 31 * result + (highlightedName != null ? highlightedName.hashCode() : 0);
        result = 31 * result + (matchedName != null ? matchedName.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (longId != null ? longId.hashCode() : 0);
        result = 31 * result + (longIds != null ? longIds.hashCode() : 0);
        result = 31 * result + (parentIds != null ? parentIds.hashCode() : 0);
        return result;
    }
}
