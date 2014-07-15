package it.cybion.geocoder.requests;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public enum AutocompleteBias {

    // no bias
    // mix local and globally relevant results
    NONE(0),

    BALANCED(1),

    // prefer locally relevant results
    LOCAL(2),

    // prefer globally relevant results
    GLOBAL(3);

    private final int value;

    private AutocompleteBias(int value) {

        this.value = value;
    }

    public int getValue() {

        return value;
    }
}
