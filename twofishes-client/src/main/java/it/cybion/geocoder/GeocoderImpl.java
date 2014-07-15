package it.cybion.geocoder;

import it.cybion.geocoder.requests.AutocompleteBias;
import it.cybion.geocoder.requests.GeocodeRequest;
import it.cybion.geocoder.responses.GeocodeResponse;
import it.cybion.geocoder.utils.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocoderImpl implements Geocoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeocoderImpl.class);

    private String host;

    private int port;
    private ObjectMapper objectMapper;

    private final CloseableHttpClient httpclient;

    public GeocoderImpl(final String host, int port, ObjectMapper objectMapper) {

        this.host = host;
        this.port = port;
        this.objectMapper = objectMapper;
        this.httpclient = HttpClients.createDefault();

    }

    @Override
    public GeocodeResponse geocode(final GeocodeRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("request can't be null");
        }

        GeocodeResponse geocodeResponse = null;

        //parse parameters from requests
        //https://github.com/foursquare/twofishes/blob/master/docs/twofishes_requests.md
        final String query = request.getQuery();
        final String cc = request.getCc();
        final String lang = request.getLang();
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
        final String woeHint = StringUtils.asCsv(request.getWoeHint());
        final String woeRestrict = StringUtils.asCsv(request.getWoeRestrict());
        //TODO bounds are not considered
        request.getBounds();
        final String slug = request.getSlug();
        final Integer radius = request.getRadius();
        String radiusAsString = null;
        if (radius != null) {
            radiusAsString = radius.toString();
        }
        final String maxInterpretations = request.getMaxInterpretations().toString();
        final String allowedSources = StringUtils.asCsv(request.getAllowedSources());
        final String responseIncludes = StringUtils.asCsv(request.getResponseIncludes());
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

        setParamIfNotNull(http, "query", query);
        setParamIfNotNull(http, "cc", cc);
        setParamIfNotNull(http, "lang", lang);
        setParamIfNotNull(http, "ll", commaSeparatedLatLng);

        if (debug != null && !debug.equals("0")) {
            http.setParameter("debug", debug);
        }
        setParamIfNotNull(http, "maxInterpretations", maxInterpretations);
        setParamIfNotNull(http, "woeHint", woeHint);
        setParamIfNotNull(http, "woeRestrict", woeRestrict);
        setParamIfNotNull(http, "responseIncludes", responseIncludes);
        setParamIfNotNull(http, "radius", radiusAsString);
        setParamIfNotNull(http, "autocomplete", autocompleteAsString);
        setParamIfNotNull(http, "autocompleteBias", autocompleteBiasAsString);
        setParamIfNotNull(http, "strict", isStrict);
        setParamIfNotNull(http, "slug", slug);
        setParamIfNotNull(http, "allowedSources", allowedSources);

        URI requestUri = null;

        try {
            requestUri = http.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException("can't build uri", e);
        }

        LOGGER.debug(requestUri.toString());

        final HttpGet httpGet = new HttpGet(requestUri);

        CloseableHttpResponse response1 = null;

        try {
            response1 = httpclient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response1 != null) {

            //consume http
            String responseAsJson = null;

            try {
                final StatusLine statusLine = response1.getStatusLine();
                final HttpEntity entity1 = response1.getEntity();
                responseAsJson = EntityUtils.toString(entity1);
                EntityUtils.consume(entity1);
            } catch (IOException e) {
                //TODO
                e.printStackTrace();
            } finally {
                try {
                    response1.close();
                } catch (IOException e) {
                    //TODO
                    e.printStackTrace();
                }
            }
            //deserialize to json

            try {
                geocodeResponse = deserialize(responseAsJson);
            } catch (IOException e) {
                //TODO
                e.printStackTrace();
            }
        }

        return geocodeResponse;
    }

    private static void setParamIfNotNull(URIBuilder http, String parameter, String value) {

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
