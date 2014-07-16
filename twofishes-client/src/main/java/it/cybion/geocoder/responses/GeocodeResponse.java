package it.cybion.geocoder.responses;

import java.util.List;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocodeResponse {

    private List<Interpretation> interpretations;

    private GeocodeResponse() {

    }

    public List<Interpretation> getInterpretations() {

        return interpretations;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeocodeResponse that = (GeocodeResponse) o;

        if (interpretations != null ? !interpretations.equals(that.interpretations) :
                that.interpretations != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        return interpretations != null ? interpretations.hashCode() : 0;
    }

    @Override
    public String toString() {

        return "GeocodeResponse{" +
               "interpretations=" + interpretations +
               '}';
    }
}
