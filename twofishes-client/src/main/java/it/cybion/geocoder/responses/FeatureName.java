package it.cybion.geocoder.responses;

import java.util.List;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class FeatureName {

    private String name;

    private String lang;

    private List<FeatureNameFlag> flags;

    private FeatureName() {

    }

    public String getName() {

        return name;
    }

    public String getLang() {

        return lang;
    }

    public List<FeatureNameFlag> getFlags() {

        return flags;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeatureName featureName1 = (FeatureName) o;

        if (flags != null ? !flags.equals(featureName1.flags) : featureName1.flags != null) {
            return false;
        }
        if (lang != null ? !lang.equals(featureName1.lang) : featureName1.lang != null) {
            return false;
        }
        if (name != null ? !name.equals(featureName1.name) : featureName1.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lang != null ? lang.hashCode() : 0);
        result = 31 * result + (flags != null ? flags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "Name{" +
               "name='" + name + '\'' +
               ", lang='" + lang + '\'' +
               ", flags=" + flags +
               '}';
    }
}
