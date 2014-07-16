package it.cybion.geocoder;

import it.cybion.geocoder.serialization.ObjectMapperFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public abstract class GeocoderImplProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeocoderImplProvider.class);

    private CloseableHttpClient httpClient;

    protected GeocoderImpl geocoder;

    @BeforeMethod
    public void setUpGeocoder() throws Exception {

        this.httpClient = HttpClients.createDefault();
        this.geocoder = new GeocoderImpl("localhost", 5101,
                ObjectMapperFactory.INSTANCE.getObjectMapper(), httpClient);
    }

    @AfterMethod
    public void tearDownGeocoder() throws Exception {

        this.httpClient.close();

    }

}
