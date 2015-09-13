package BookInventory.repository;

import BookInventory.App;
import BookInventory.config.factory.ConsumtionFactory;
import BookInventory.domain.Consumtion;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by student on 2015/05/03.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class ComsumtionCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;

    @Autowired
    private ConsumtionRepository repository;

    @Test
    public void create() throws Exception {
        Map<String,String> values = new HashMap<String,String>();

        values.put("code","123456");

        Consumtion consumtion = ConsumtionFactory
                .createConsumtion(5, values);

        repository.save(consumtion);
        id=consumtion.getId();
        Assert.assertNotNull(consumtion);
    }

    @Test(dependsOnMethods = "create")
    public void read() throws Exception{
        Consumtion consumtion = repository.findOne(id);
        Assert.assertNotNull(consumtion);
    }

    @Test(dependsOnMethods = "read")
    public void update() throws Exception{
        Map<String,String> values = new HashMap<String,String>();

        values.put("code","123456");

        Consumtion consumtion = ConsumtionFactory
                .createConsumtion(5,values);

        Consumtion newConsumtion = new Consumtion
                .Builder(consumtion.getCode())
                .copy(consumtion)
                .quantity(6)
                .build();
        Consumtion updateConsumtionr = repository.findOne(id);
        org.testng.Assert.assertEquals(updateConsumtionr.getQuantity(),6);
    }

    @Test(dependsOnMethods = "update")
    public void delete() throws Exception{
        Consumtion consumtion = repository.findOne(id);
        repository.delete(consumtion);
        Consumtion deletedConsumtion = repository.findOne(id);
        org.testng.Assert.assertNull(deletedConsumtion);
    }
}
