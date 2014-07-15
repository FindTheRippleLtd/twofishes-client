package it.cybion.geocoder;

import it.cybion.geocoder.requests.ResponseIncludes;
import it.cybion.geocoder.requests.YahooWoeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * //TODO
 *
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class EnumUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnumUtils.class);

    public static String asCsv(List<YahooWoeType> woeHint) {
        //TODO return null if list is empty or null

        return null;

    }

    public static String asCsvRi(List<ResponseIncludes> responseIncludes) {

        return null;
    }

    public static String asCsvAs(List<String> allowedSources) {

        return null;
    }
}
