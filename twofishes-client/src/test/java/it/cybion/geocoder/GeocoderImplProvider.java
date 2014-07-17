package it.cybion.geocoder;

import it.cybion.geocoder.serialization.ObjectMapperFactory;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public abstract class GeocoderImplProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeocoderImplProvider.class);

    private CloseableHttpClient httpClient;

    protected GeocoderImpl geocoder;

    @BeforeClass
    public void setUpGeocoder() throws Exception {

        final HttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        this.httpClient = HttpClients.createMinimal(connManager);
        this.geocoder = new GeocoderImpl("localhost", 5101,
                ObjectMapperFactory.INSTANCE.getObjectMapper(), httpClient);
    }

    @AfterClass
    public void tearDownGeocoder() throws Exception {

        this.httpClient.close();

    }

}
