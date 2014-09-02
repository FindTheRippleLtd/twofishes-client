package it.cybion.geocoder.responses;

import it.cybion.geocoder.requests.YahooWoeType;

import java.util.List;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class Feature {

    private String cc;

    private FeatureGeometry geometry;

    private String name;

    private String displayName;

    private YahooWoeType woeType;

    private List<FeatureId> ids;

    private List<FeatureName> names;

    private String highlightedName;

    private String matchedName;

    private String id;

    private GeocodeFeatureAttributes attributes;

    private Long longId;

    private List<Long> longIds;

    private List<Long> parentIds;

    private Feature() {

    }

    public Feature(String cc, FeatureGeometry geometry, String name, String displayName,
            YahooWoeType woeType, List<FeatureId> ids, List<FeatureName> names,
            String highlightedName, String matchedName, String id,
            GeocodeFeatureAttributes attributes, Long longId, List<Long> longIds,
            List<Long> parentIds) {

        this.cc = cc;
        this.geometry = geometry;
        this.name = name;
        this.displayName = displayName;
        this.woeType = woeType;
        this.ids = ids;
        this.names = names;
        this.highlightedName = highlightedName;
        this.matchedName = matchedName;
        this.id = id;
        this.attributes = attributes;
        this.longId = longId;
        this.longIds = longIds;
        this.parentIds = parentIds;
    }

    public String getCc() {

        return cc;
    }

    public FeatureGeometry getGeometry() {

        return geometry;
    }

    public String getName() {

        return name;
    }

    public String getDisplayName() {

        return displayName;
    }

    public YahooWoeType getWoeType() {

        return woeType;
    }

    public List<FeatureId> getIds() {

        return ids;
    }

    public List<FeatureName> getNames() {

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

    public GeocodeFeatureAttributes getAttributes() {

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
        if (woeType != feature.woeType) {
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
        result = 31 * result + (woeType != null ? woeType.hashCode() : 0);
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

    @Override
    public String toString() {

        return "Feature{" +
               "cc='" + cc + '\'' +
               ", geometry=" + geometry +
               ", name='" + name + '\'' +
               ", displayName='" + displayName + '\'' +
               ", woeType=" + woeType +
               ", ids=" + ids +
               ", names=" + names +
               ", highlightedName='" + highlightedName + '\'' +
               ", matchedName='" + matchedName + '\'' +
               ", id='" + id + '\'' +
               ", attributes=" + attributes +
               ", longId=" + longId +
               ", longIds=" + longIds +
               ", parentIds=" + parentIds +
               '}';
    }
}
