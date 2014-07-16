package it.cybion.geocoder.responses;

import it.cybion.geocoder.requests.YahooWoeType;

import java.util.List;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocodeFeature {

    private String cc;

    private FeatureGeometry geometry;

    private String name;

    private String displayName;

    private YahooWoeType woeType;

    private List<FeatureId> ids;

    private List<FeatureName> names;

    private List<String> attribution;

    private List<String> timezones;

    private String highlightedName;

    private String matchedName;

    private String slug;

    private String id;

    private GeocodeFeatureAttributes attributes;

    private Long longId;

    private List<Long> longIds;

    private List<Long> parentIds;

    private YahooWoeType role;

    private GeocodeFeature() {

        this.woeType = YahooWoeType.UNKNOWN;
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

    public List<String> getAttribution() {

        return attribution;
    }

    public List<String> getTimezones() {

        return timezones;
    }

    public String getHighlightedName() {

        return highlightedName;
    }

    public String getMatchedName() {

        return matchedName;
    }

    public String getSlug() {

        return slug;
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

    public YahooWoeType getRole() {

        return role;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeocodeFeature that = (GeocodeFeature) o;

        if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null) {
            return false;
        }
        if (attribution != null ? !attribution.equals(that.attribution) :
                that.attribution != null) {
            return false;
        }
        if (cc != null ? !cc.equals(that.cc) : that.cc != null) {
            return false;
        }
        if (displayName != null ? !displayName.equals(that.displayName) :
                that.displayName != null) {
            return false;
        }
        if (geometry != null ? !geometry.equals(that.geometry) : that.geometry != null) {
            return false;
        }
        if (highlightedName != null ? !highlightedName.equals(that.highlightedName) :
                that.highlightedName != null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (ids != null ? !ids.equals(that.ids) : that.ids != null) {
            return false;
        }
        if (longId != null ? !longId.equals(that.longId) : that.longId != null) {
            return false;
        }
        if (longIds != null ? !longIds.equals(that.longIds) : that.longIds != null) {
            return false;
        }
        if (matchedName != null ? !matchedName.equals(that.matchedName) :
                that.matchedName != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (names != null ? !names.equals(that.names) : that.names != null) {
            return false;
        }
        if (parentIds != null ? !parentIds.equals(that.parentIds) : that.parentIds != null) {
            return false;
        }
        if (role != that.role) {
            return false;
        }
        if (slug != null ? !slug.equals(that.slug) : that.slug != null) {
            return false;
        }
        if (timezones != null ? !timezones.equals(that.timezones) : that.timezones != null) {
            return false;
        }
        if (woeType != that.woeType) {
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
        result = 31 * result + (attribution != null ? attribution.hashCode() : 0);
        result = 31 * result + (timezones != null ? timezones.hashCode() : 0);
        result = 31 * result + (highlightedName != null ? highlightedName.hashCode() : 0);
        result = 31 * result + (matchedName != null ? matchedName.hashCode() : 0);
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (longId != null ? longId.hashCode() : 0);
        result = 31 * result + (longIds != null ? longIds.hashCode() : 0);
        result = 31 * result + (parentIds != null ? parentIds.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "GeocodeFeature{" +
               "cc='" + cc + '\'' +
               ", geometry=" + geometry +
               ", name='" + name + '\'' +
               ", displayName='" + displayName + '\'' +
               ", woeType=" + woeType +
               ", ids=" + ids +
               ", names=" + names +
               ", attribution=" + attribution +
               ", timezones=" + timezones +
               ", highlightedName='" + highlightedName + '\'' +
               ", matchedName='" + matchedName + '\'' +
               ", slug='" + slug + '\'' +
               ", id='" + id + '\'' +
               ", attributes=" + attributes +
               ", longId=" + longId +
               ", longIds=" + longIds +
               ", parentIds=" + parentIds +
               ", role=" + role +
               '}';
    }
}
