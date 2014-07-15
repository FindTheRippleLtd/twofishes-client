package it.cybion.geocoder;

import it.cybion.geocoder.requests.GeocodeRequest;
import it.cybion.geocoder.responses.GeocodeResponse;
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

        GeocodeResponse geocodeResponse = null;

        //TODO parse parameters from requests
        final String query = "nyc";

        URI requestUri = null;
        try {
            requestUri = new URIBuilder().setScheme("http")
                    .setHost(this.host)
                    .setPort(this.port)
                    .setPath("/")
                    .setParameter("query", query)
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

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
                e.printStackTrace();
            } finally {
                try {
                    response1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //deserialize to json

            geocodeResponse = deserialize(responseAsJson);
        }

        return geocodeResponse;
    }

    private GeocodeResponse deserialize(final String responseAsJson) {

        GeocodeResponse geocodeResponse = null;

        try {
            return this.objectMapper.readValue(responseAsJson, GeocodeResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return geocodeResponse;
    }
}
