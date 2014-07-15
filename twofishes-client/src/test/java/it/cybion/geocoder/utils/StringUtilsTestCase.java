package it.cybion.geocoder.utils;

import it.cybion.geocoder.requests.ResponseIncludes;
import it.cybion.geocoder.requests.YahooWoeType;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class StringUtilsTestCase {

    @Test
    public void testYahooWoe() throws Exception {

        final List<YahooWoeType> types = Arrays.asList(YahooWoeType.ADMIN1, YahooWoeType.COLLOQUIAL,
                YahooWoeType.AIRPORT);
        assertEquals(StringUtils.asCsv(types), "8,24,14");
    }

    @Test
    public void testResponseIncludes() throws Exception {

        final List<ResponseIncludes> types = Arrays.asList(ResponseIncludes.ALL_NAMES,
                ResponseIncludes.DISPLAY_NAME, ResponseIncludes.PARENT_ALL_NAMES);
        assertEquals(StringUtils.asCsv(types), "ALL_NAMES,DISPLAY_NAME,PARENT_ALL_NAMES");
    }

    @Test
    public void testString() throws Exception {

        final List<String> strings = Arrays.asList("a", "b", "c");
        assertEquals(StringUtils.asCsv(strings), "a,b,c");

    }

}
