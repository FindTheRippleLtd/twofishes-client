package it.cybion.geocoder;

import it.cybion.geocoder.requests.GeocodeRequest;
import it.cybion.geocoder.requests.ResponseIncludes;
import it.cybion.geocoder.requests.YahooWoeType;
import it.cybion.geocoder.responses.GeocodeResponse;
import it.cybion.geocoder.responses.Interpretation;
import it.cybion.geocoder.utils.GeocodedProvinceName;
import it.cybion.geocoder.utils.ProvinceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocoderPerf extends GeocoderImplProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            GeocoderPerf.class);

    private List<String> myDict;

    @BeforeClass
    public void setUpDataset() throws Exception {

        final InputStream resourceAsStream = this.getClass().getResourceAsStream(
                "/100-locations-mixed.txt");

        final InputStreamReader in = new InputStreamReader(resourceAsStream);
        final BufferedReader r = new BufferedReader(in);

        myDict = new ArrayList<String>();

        String line;

        while ((line = r.readLine()) != null) {
            myDict.add(line);
        }

        resourceAsStream.close();
        assertEquals(myDict.size(), 100);

    }

    @AfterClass
    public void tearDownDataSet() throws Exception {

        this.myDict.clear();
        this.myDict = null;

    }

    @Test(enabled = true)
    public void givenDatasetShouldTestPerformances() throws Exception {

        int missedProvinceNames = 0;
        int missedCountryNames = 0;

        for (final String location : this.myDict) {

            final GeocodeRequest geocodeRequest = new GeocodeRequest.GeocodeRequestBuilder().query(
                    location).addWoeHint(YahooWoeType.ADMIN2).addResponseInclude(
                    ResponseIncludes.PARENTS).lang("en").build();

            final GeocodeResponse geocode = this.geocoder.geocode(geocodeRequest);
            final List<Interpretation> interpretations = geocode.getInterpretations();

            final ProvinceCalculator provinceCalculator = new ProvinceCalculator(interpretations);

            final GeocodedProvinceName geocodedProvinceName =
                    provinceCalculator.calculateProvinceNameAndLatLon();
            final String s = provinceCalculator.calculateCountryName();

            if (geocodedProvinceName.equals(GeocodedProvinceName.NULL)) {
                missedProvinceNames++;
                LOGGER.info("can't geocode province from '" + location + "'");
            }

            if (s == null) {
                missedCountryNames++;
                LOGGER.info("can't geocode country from '" + location + "'");
            }

        }

        LOGGER.info("missed provinces " + missedProvinceNames);
        LOGGER.info("missed country names " + missedCountryNames);

    }

    @Test (enabled = true)
    public void loadTest() throws Exception {

        final ExecutorService executorService = Executors.newFixedThreadPool(4);

        final int clientAmount = 4;
        for (int j = 0; j < clientAmount; j++) {
            executorService.submit(new TwoFishesClient(this.geocoder, 5));
        }

        executorService.awaitTermination(1, TimeUnit.SECONDS);

    }

    private static class TwoFishesClient implements Runnable {

        private final Geocoder geocoder;

        private int queriesPerClient;

        public TwoFishesClient(final Geocoder geocoder, final int queriesPerClient) {

            this.geocoder = geocoder;
            this.queriesPerClient = queriesPerClient;
        }

        @Override
        public void run() {

            for (int i = 0; i < queriesPerClient; i++) {

                final GeocodeRequest request = new GeocodeRequest.GeocodeRequestBuilder().query(
                        "location " + i).addWoeHint(YahooWoeType.ADMIN2).addResponseInclude(
                        ResponseIncludes.PARENTS).lang("en").build();
                long startTime = System.nanoTime();
                this.geocoder.geocode(request);
                final long duration = System.nanoTime() - startTime;
                LOGGER.info("" + duration + " nanosecs");
            }
        }
    }
}
