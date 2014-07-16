package it.cybion.geocoder.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class StringUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

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
}
