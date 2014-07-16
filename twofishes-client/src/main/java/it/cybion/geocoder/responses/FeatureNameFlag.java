package it.cybion.geocoder.responses;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public enum FeatureNameFlag {
    // A preferred name is one that is most often referred to a place
    PREFERRED(1),
    ABBREVIATION(2),
    // a brain-dead deaccenting of a name with diacritics, rendered down to ascii
    DEACCENT(4),
    // names which were not on the original feature, but got there through indexer
    // transforms via rewrites.txt and other hacks
    ALIAS(8),

    // is this name in one of the local languages of this country
    LOCAL_LANG(16),

    // Names coming from aliases.txt
    ALT_NAME(32),

    COLLOQUIAL(64),

    SHORT_NAME(128),

    NEVER_DISPLAY(256),

    LOW_QUALITY(512),

    HISTORIC(1024);

    private final int value;

    private FeatureNameFlag(int value) {

        this.value = value;

    }

    public int getValue() {

        return value;
    }

    @Override
    public String toString() {

        return value + "";
    }

    public static FeatureNameFlag fromTypeCode(int valueAsInt) {

        switch (valueAsInt) {
            case 1:
                return PREFERRED;
            case 2:
                return ABBREVIATION;
            case 4:
                return DEACCENT;
            case 8:
                return ALIAS;
            case 16:
                return LOCAL_LANG;
            case 32:
                return ALT_NAME;
            case 64:
                return COLLOQUIAL;
            case 128:
                return SHORT_NAME;
            case 256:
                return NEVER_DISPLAY;
            case 512:
                return LOW_QUALITY;
            case 1024:
                return HISTORIC;
        }
        throw new IllegalArgumentException("Invalid Flag type code: " + valueAsInt);

    }
}
