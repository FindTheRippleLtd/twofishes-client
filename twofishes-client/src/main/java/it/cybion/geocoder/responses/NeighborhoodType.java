package it.cybion.geocoder.responses;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public enum NeighborhoodType {

    MACRO(2),
    NEIGHBORHOOD(3),
    SUB(4);

    private final int value;

    private NeighborhoodType(int value) {

        this.value = value;
    }

    public int getValue() {

        return value;
    }
}
