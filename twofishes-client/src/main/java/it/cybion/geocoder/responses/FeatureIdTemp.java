package it.cybion.geocoder.responses;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class FeatureIdTemp {

    private String id;

    private String source;

    private FeatureIdTemp() {

    }

    public FeatureIdTemp(String id, String source) {

        this.id = id;
        this.source = source;
    }

    public String getSource() {

        return source;
    }

    public String getId() {

        return id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeatureIdTemp featureId1 = (FeatureIdTemp) o;

        if (id != null ? !id.equals(featureId1.id) : featureId1.id != null) {
            return false;
        }
        if (source != null ? !source.equals(featureId1.source) : featureId1.source != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "Id{" +
               "id='" + id + '\'' +
               ", source='" + source + '\'' +
               '}';
    }
}
