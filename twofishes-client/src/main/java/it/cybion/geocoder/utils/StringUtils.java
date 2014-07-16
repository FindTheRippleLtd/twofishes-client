package it.cybion.geocoder.utils;

import java.util.List;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class StringUtils {

    private static final String GEONAMEID_PREFIX = "geonameid:";

    public static <T> String asCsv(final List<T> items) {

        if (items == null || items.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        String separator = "";
        for (final T item : items) {

            sb.append(separator).append(item.toString());
            separator = ",";
        }

        return sb.toString();
    }

    public static String geonameIdToUrl(final String geonameId) {

        if (!geonameId.startsWith(GEONAMEID_PREFIX)) {
            return "";
        }

        return "http://www.geonames.org/" + geonameId.substring(GEONAMEID_PREFIX.length());

    }
}
