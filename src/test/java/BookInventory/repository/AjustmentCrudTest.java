package BookInventory.repository;

import BookInventory.App;
import BookInventory.config.factory.AjustmentFactory;
import BookInventory.domain.Ajustment;
import BookInventory.domain.Employee;
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
public class AjustmentCrudTest extends AbstractTestNGSpringContextTests {
    private Long id;

    @Autowired
    private AjustmentRepository repository;

    @Test
    public void create() throws Exception{
        List<Employee> employeeList = new ArrayList<Employee>();
        Map<String,String> values = new HashMap<String,String>();
        values.put("itemName","Lord of the rings");
        values.put("code","123abc");
        Ajustment ajustment = AjustmentFactory
                .createAjustment(15, values, employeeList);
        repository.save(ajustment);
        id=ajustment.getId();
        Assert.assertNotNull(ajustment);
    }
    @Test(dependsOnMethods = "create")
    public void read() throws Exception{
        Ajustment ajustment = repository.findOne(id);
        Assert.assertNotNull(ajustment);
    }
    @Test(dependsOnMethods = "read")
    private void update() throws Exception{
        List<Employee> employeeList = new ArrayList<Employee>();
        Map<String, String> values = new HashMap<String, String>();
        values.put("itemName","Lord of the rings");
        values.put("code","123abc");
        Ajustment ajustment = AjustmentFactory
                .createAjustment(15, values, employeeList);
        Ajustment newAjustment = new Ajustment
                .Builder(ajustment.getItemName())
                .copy(ajustment)
                .amount(10)
                .build();
        Ajustment updateAjustment = repository.findOne(id);
        org.testng.Assert.assertEquals(updateAjustment.getAmount(),10);
    }
}