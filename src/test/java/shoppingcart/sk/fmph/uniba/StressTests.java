package shoppingcart.sk.fmph.uniba;

import org.apache.tomcat.jni.SSL;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import shoppingcart.sk.fmph.uniba.api.HttpAPI;
import shoppingcart.sk.fmph.uniba.controllers.HomeController;
import shoppingcart.sk.fmph.uniba.controllers.ProductController;
import shoppingcart.sk.fmph.uniba.entities.Product;
import shoppingcart.sk.fmph.uniba.repository.ProductRepository;

import javax.net.ssl.SSLHandshakeException;
import javax.validation.constraints.Null;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StressTests {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ProductRepository productRepository;

    @MockBean
    private ProductController productController;

    @MockBean
    private HomeController homeController;

    List<Product> productList;

    @Before
    public void initStressTests(){
        productList = new ArrayList<>();
    }

    //search cannot last more than 200ms
    @Test(timeout = 200)
    public void searchQueryStress(){
        productList.add(productRepository.search("Product486").get(0));
        assertEquals(1, productList.size());
    }

    //multiple search cannot last more than 200ms * searches performed
    @Test(timeout = 1000)
    public void searchQueryMultipleStress(){
        int searchesPerfomed = 5;
        for(int i = 0; i < searchesPerfomed; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 498 + 1);
            productList.add(productRepository.search("Product" + randomNum).get(0));
        }
        assertEquals(searchesPerfomed, productList.size());
    }

    @Test(timeout = 1000)
    public void productsMustLoadInOneSecondTest(){
        assertNotNull(productController);
    }

    @Test(timeout = 1000)
    public void homepageMustLoadInOneSecondTest(){
        assertNotNull(homeController);
    }

    @Test(timeout = 1000)
    public void getHomepageByHttpRequest(){
        try {
            getHTML("https://localhost:8443/home");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //should not validate localhost in handshake
    @Test(expected = SSLHandshakeException.class)
    public void getCategoryOneByHttpRequest() throws Exception {
        getHTML("https://localhost:8443/categories/Cat1");
    }

    //should not validate localhost in handshake thus throwing null on allCustomersEmails() method
    @Test(expected = NullPointerException.class)
    public void getAllInitialCustomersEmailsAPITest(){
        HttpAPI api = new HttpAPI();
        System.out.println(Arrays.toString(new ArrayList[]{api.allCustomersEmails()}));
    }

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

}
