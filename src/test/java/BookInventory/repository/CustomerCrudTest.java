package BookInventory.repository;

import BookInventory.App;
import BookInventory.config.factory.CustomerFactory;
import BookInventory.domain.Consumtion;
import BookInventory.domain.Customer;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by student on 2015/05/03.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class CustomerCrudTest extends AbstractTestNGSpringContextTests {
    private Long id;

    @Autowired
    private CustomerRepository repository;

    @Test
    public void create() throws Exception{
        List<Consumtion> consumtionList = new ArrayList<Consumtion>();
        Map<String,String> values = new HashMap<String,String>();

        values.put("code","98TYT");

        Customer customer = CustomerFactory
                .createCustomer(values, consumtionList);

        repository.save(customer);
        id=customer.getId();
        Assert.assertNotNull(customer);
    }

    @Test(dependsOnMethods = "create")
    public void read() throws Exception{
        Customer customer = repository.findOne(id);
        Assert.assertNotNull(customer);
    }

    @Test(dependsOnMethods = "read")
    public void update() throws Exception{
        List<Consumtion> consumtionList = new ArrayList<Consumtion>();
        Map<String, String> values = new HashMap<String, String>();

        values.put("code", "98TYT");
        values.put("firstName","Lexi");
        values.put("lastName","Parker");

        Customer customer = CustomerFactory
                .createCustomer(values, consumtionList);

        Customer newCustomer = new Customer
                .Builder(customer.getCode())
                .firstName("Audrey")
                .lastName("Parker")
                .build();

        Customer updateCustomer = repository.findOne(id);
        org.testng.Assert.assertEquals(updateCustomer.getLastName(),"Queen");
    }

    @Test(dependsOnMethods = "update")
    public void delete() throws Exception{
        Customer customer = repository.findOne(id);
        repository.delete(customer);
        Customer deletedCustomer = repository.findOne(id);
        org.testng.Assert.assertNull(deletedCustomer);
    }
}
