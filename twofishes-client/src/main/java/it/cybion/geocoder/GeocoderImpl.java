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

        if (query != null) {
            http.setParameter("query", query);
        }
        if (cc != null) {
            http.setParameter("cc", cc);
        }
        if (lang != null) {
            http.setParameter("lang", lang);
        }
        if (commaSeparatedLatLng != null) {
            http.setParameter("ll", commaSeparatedLatLng);
        }
        if (debug != null && !debug.equals("0")) {
            http.setParameter("debug", debug);
        }
        if (maxInterpretations != null) {
            http.setParameter("maxInterpretations", maxInterpretations);
        }
        if (woeHint != null) {
            http.setParameter("woeHint", woeHint);
        }
        if (woeRestrict != null) {
            http.setParameter("woeRestrict", woeRestrict);
        }
        if (responseIncludes != null) {
            http.setParameter("responseIncludes", responseIncludes);
        }
        if (radiusAsString != null) {
            http.setParameter("radius", radiusAsString);
        }

        if (autocompleteAsString != null) {
            http.setParameter("autocomplete", autocompleteAsString);
        }

        if (autocompleteBiasAsString != null) {
            http.setParameter("autocompleteBias", autocompleteBiasAsString);
        }

        if (isStrict != null) {
            http.setParameter("strict", isStrict);
        }

        if (slug != null) {
            http.setParameter("slug", slug);
        }
        if (allowedSources != null) {
            http.setParameter("allowedSources", allowedSources);
        }

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

    private GeocodeResponse deserialize(final String responseAsJson) throws IOException {

        final GeocodeResponse geocodeResponse = this.objectMapper.readValue(responseAsJson,
                GeocodeResponse.class);

        return geocodeResponse;
    }
}
