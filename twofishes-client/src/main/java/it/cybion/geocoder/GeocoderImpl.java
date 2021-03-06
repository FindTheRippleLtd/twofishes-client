package it.cybion.geocoder;

import it.cybion.geocoder.exceptions.GeocoderException;
import it.cybion.geocoder.requests.AutocompleteBias;
import it.cybion.geocoder.requests.GeocodeRequest;
import it.cybion.geocoder.responses.GeocodeResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import static it.cybion.geocoder.utils.StringUtils.asCsv;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocoderImpl implements Geocoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeocoderImpl.class);

    private String host;

    private int port;

    private ObjectMapper objectMapper;

    private final CloseableHttpClient httpclient;

    public GeocoderImpl(final String host, final int port, final ObjectMapper objectMapper,
            final CloseableHttpClient aDefault) {

        this.host = host;
        this.port = port;
        this.objectMapper = objectMapper;
        this.httpclient = aDefault;

    }

    @Override
    public GeocodeResponse geocode(final GeocodeRequest request) {

        if (request == null) { // || request.getQuery().startsWith("http")) {
            throw new IllegalArgumentException("request can't be null");
        }

        GeocodeResponse geocodeResponse = GeocodeResponse.NULL;

        //parse parameters from requests
        //https://github.com/foursquare/twofishes/blob/master/docs/twofishes_requests.md

        final GeocodePoint ll = request.getLl();
        String commaSeparatedLatLng = null;
        if (ll != null) {
            commaSeparatedLatLng = ll.getLat() + "," + ll.getLng();
        }

        final String debug = request.getDebug() + "";
        final Boolean autocomplete = request.isAutocomplete();
        String autocompleteAsString = null;
        if (autocomplete != null) {
            autocompleteAsString = Boolean.toString(autocomplete);
        }
        //TODO bounds are not considered yet
        request.getBounds();

        final Integer radius = request.getRadius();
        String radiusAsString = null;
        if (radius != null) {
            radiusAsString = radius.toString();
        }

        final Boolean strict = request.isStrict();
        String isStrict = null;
        if (strict != null) {
            isStrict = Boolean.toString(strict);
        }

        final AutocompleteBias autocompleteBias = request.getAutocompleteBias();
        String autocompleteBiasAsString = null;

        if (autocompleteBias != null) {
            autocompleteBiasAsString = autocompleteBias.getValue() + "";
        }

        final URIBuilder http = new URIBuilder().setScheme("http").setHost(this.host).setPort(
                this.port).setPath("/");

        setParamIfNotNullValue(http, "query", request.getQuery());
        setParamIfNotNullValue(http, "cc", request.getCc());
        setParamIfNotNullValue(http, "lang", request.getLang());
        setParamIfNotNullValue(http, "ll", commaSeparatedLatLng);

        if (debug != null && !debug.equals("0")) {
            http.setParameter("debug", debug);
        }
        setParamIfNotNullValue(http, "maxInterpretations",
                request.getMaxInterpretations().toString());
        setParamIfNotNullValue(http, "woeHint", asCsv(request.getWoeHint()));
        setParamIfNotNullValue(http, "woeRestrict", asCsv(request.getWoeRestrict()));
        setParamIfNotNullValue(http, "responseIncludes", asCsv(request.getResponseIncludes()));
        setParamIfNotNullValue(http, "radius", radiusAsString);
        setParamIfNotNullValue(http, "autocomplete", autocompleteAsString);
        setParamIfNotNullValue(http, "autocompleteBias", autocompleteBiasAsString);
        setParamIfNotNullValue(http, "strict", isStrict);
        setParamIfNotNullValue(http, "slug", request.getSlug());
        setParamIfNotNullValue(http, "allowedSources", asCsv(request.getAllowedSources()));

        URI requestUri = null;

        try {
            requestUri = http.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException("can't build uri", e);
        }

        LOGGER.debug(requestUri + "");

        final HttpGet httpGet = new HttpGet(requestUri);

        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpGet);
        } catch (IOException e) {
            throw new GeocoderException("failed http get", e);
        }

        if (response != null) {

            //consume http
            String responseAsJson = null;
            final int statusCode = response.getStatusLine().getStatusCode();

            try {
                final HttpEntity entity = response.getEntity();
                responseAsJson = EntityUtils.toString(entity, Charset.forName("UTF-8"));
                EntityUtils.consume(entity);
            } catch (IOException e) {
                throw new GeocoderException("failed http entity consume", e);
            } finally {
                closeQuietly(response);
            }

            if (statusCode != 200) {
                throw new GeocoderException("received http status code '" + statusCode + "'");
            }
            //deserialize to json

            try {
                geocodeResponse = deserialize(responseAsJson);
            } catch (IOException e) {
                throw new GeocoderException(
                        "failed deserialising from json: '" + responseAsJson + "'", e);
            }
        }

        return geocodeResponse;
    }

    private static void closeQuietly(CloseableHttpResponse response1) {
        try {
            if (response1 != null) {
                response1.close();
            }
        } catch (IOException e) {
            LOGGER.info("failed closing http response", e);
        }
    }

    private static void setParamIfNotNullValue(final URIBuilder http, final String parameter,
            final String value) {

        if (value != null) {
            http.setParameter(parameter, value);
        }

    }

    private GeocodeResponse deserialize(final String responseAsJson) throws IOException {

        final GeocodeResponse geocodeResponse = this.objectMapper.readValue(responseAsJson,
                GeocodeResponse.class);

        return geocodeResponse;
    }
}
