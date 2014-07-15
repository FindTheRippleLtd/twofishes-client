package it.cybion.geocoder.responses;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class Attributes {

    private Integer adm0cap;
    private Integer scalerank;
    private Integer labelrank;
    private Integer natscale;
    private Integer population;

    private Attributes() {

    }

    public Integer getAdm0cap() {

        return adm0cap;
    }

    public Integer getScalerank() {

        return scalerank;
    }

    public Integer getLabelrank() {

        return labelrank;
    }

    public Integer getNatscale() {

        return natscale;
    }

    public Integer getPopulation() {

        return population;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Attributes that = (Attributes) o;

        if (adm0cap != null ? !adm0cap.equals(that.adm0cap) : that.adm0cap != null) {
            return false;
        }
        if (labelrank != null ? !labelrank.equals(that.labelrank) : that.labelrank != null) {
            return false;
        }
        if (natscale != null ? !natscale.equals(that.natscale) : that.natscale != null) {
            return false;
        }
        if (population != null ? !population.equals(that.population) : that.population != null) {
            return false;
        }
        if (scalerank != null ? !scalerank.equals(that.scalerank) : that.scalerank != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = adm0cap != null ? adm0cap.hashCode() : 0;
        result = 31 * result + (scalerank != null ? scalerank.hashCode() : 0);
        result = 31 * result + (labelrank != null ? labelrank.hashCode() : 0);
        result = 31 * result + (natscale != null ? natscale.hashCode() : 0);
        result = 31 * result + (population != null ? population.hashCode() : 0);
        return result;
    }
}