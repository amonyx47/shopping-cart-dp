package shoppingcart.sk.fmph.uniba.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import shoppingcart.sk.fmph.uniba.entities.Category;
import shoppingcart.sk.fmph.uniba.entities.Customer;
import shoppingcart.sk.fmph.uniba.entities.Product;
import shoppingcart.sk.fmph.uniba.service.CatalogService;
import shoppingcart.sk.fmph.uniba.service.CustomerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HttpAPI {

    @Autowired
    CatalogService catalogService;

    @Autowired
    CustomerService customerService;


    @RequestMapping(value = "/products/{pCode}", method = RequestMethod.GET)
    public String product(@PathVariable String pCode)
    {
        Product product = catalogService.getProductByPCode(pCode);
        return product.toString();
    }

    @RequestMapping(value = "/allCategoriesNames", method = RequestMethod.GET)
    public ArrayList<String> allCategoriesNames()
    {
        List<Category> categories = catalogService.getAllCategories();
        ArrayList<String> result = new ArrayList<>();
        for (Category category : categories) {
            result.add(category.getName());
        }
        return result;
    }

    @RequestMapping(value = "/allProductsNames", method = RequestMethod.GET)
    public ArrayList<String> allProductsNames()
    {
        List<Product> products = catalogService.getAllProducts();
        ArrayList<String> result = new ArrayList<>();
        for (Product product : products) {
            result.add(product.getName());
        }
        return result;
    }

    @RequestMapping(value = "/allProductsCodes", method = RequestMethod.GET)
    public ArrayList<String> allProductsCodes()
    {
        List<Product> products = catalogService.getAllProducts();
        ArrayList<String> result = new ArrayList<>();
        for (Product product : products) {
            result.add(product.getCod());
        }
        return result;
    }

    @RequestMapping(value = "/allProductsNamesAndPrices", method = RequestMethod.GET)
    public HashMap<String, String> allProductsNamesAndPrices()
    {
        List<Product> products = catalogService.getAllProducts();
        HashMap<String, String> result = new HashMap<>();
        for (Product product : products) {
            result.put(product.getName(), String.valueOf(product.getPrice()));
        }
        return result;
    }

    @RequestMapping(value = "/allCustomersEmails", method = RequestMethod.GET)
    public ArrayList<String> allCustomersEmails()
    {
        List<Customer> customers = customerService.getAllCustomers();
        ArrayList<String> result = new ArrayList<>();
        for (Customer customer : customers) {
            result.add(customer.getEmail());
        }
        return result;
    }

}
