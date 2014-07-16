package it.cybion.geocoder.responses;

import java.util.List;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class Interpretation {

    private String what;

    private String where;

    private Feature feature;

    private List<GeocodeFeature> parents;

    private InterpretationScoringFeatures scores;

    private InterpretationDebugInfo debugInfo;

    private List<Long> parentLongIds;

    private Interpretation() {

    }

    public String getWhat() {

        return what;
    }

    public String getWhere() {

        return where;
    }

    public Feature getFeature() {

        return feature;
    }

    public List<GeocodeFeature> getParents() {

        return parents;
    }

    public InterpretationScoringFeatures getScores() {

        return scores;
    }

    public InterpretationDebugInfo getDebugInfo() {

        return debugInfo;
    }

    public List<Long> getParentLongIds() {

        return parentLongIds;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Interpretation that = (Interpretation) o;

        if (debugInfo != null ? !debugInfo.equals(that.debugInfo) : that.debugInfo != null) {
            return false;
        }
        if (feature != null ? !feature.equals(that.feature) : that.feature != null) {
            return false;
        }
        if (parentLongIds != null ? !parentLongIds.equals(that.parentLongIds) :
                that.parentLongIds != null) {
            return false;
        }
        if (parents != null ? !parents.equals(that.parents) : that.parents != null) {
            return false;
        }
        if (scores != null ? !scores.equals(that.scores) : that.scores != null) {
            return false;
        }
        if (what != null ? !what.equals(that.what) : that.what != null) {
            return false;
        }
        if (where != null ? !where.equals(that.where) : that.where != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = what != null ? what.hashCode() : 0;
        result = 31 * result + (where != null ? where.hashCode() : 0);
        result = 31 * result + (feature != null ? feature.hashCode() : 0);
        result = 31 * result + (parents != null ? parents.hashCode() : 0);
        result = 31 * result + (scores != null ? scores.hashCode() : 0);
        result = 31 * result + (debugInfo != null ? debugInfo.hashCode() : 0);
        result = 31 * result + (parentLongIds != null ? parentLongIds.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "Interpretation{" +
               "what='" + what + '\'' +
               ", where='" + where + '\'' +
               ", feature=" + feature +
               ", parents=" + parents +
               ", scores=" + scores +
               ", debugInfo=" + debugInfo +
               ", parentLongIds=" + parentLongIds +
               '}';
    }
}
