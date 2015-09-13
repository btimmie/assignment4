package BookInventory.repository;


import BookInventory.App;
import BookInventory.config.factory.OrderFactory;
import BookInventory.domain.InventoryItem;
import BookInventory.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
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
public class OrderCrudTest extends AbstractTestNGSpringContextTests {
    private Long id;

    @Autowired
    private OrderRepository repository;
    @Test
    public void create() throws Exception{
        List<InventoryItem> inventoryItemList = new ArrayList<InventoryItem>();
        Map<String,String> values = new HashMap<String, String>();

        values.put("code","007123");
        values.put("description","Urgent order for a vey special client");

        Order order = OrderFactory
                .createOrder(20, values, null);

        repository.save(order);
        id=order.getId();
        Assert.assertNotNull(order);
    }

    @Test(dependsOnMethods = "create")
    public void read() throws Exception{
        Order order = repository.findOne(id);
        Assert.assertEquals("007123",order.getCode());
    }

    @Test(dependsOnMethods = "read")
    public void update() throws Exception{
        List<InventoryItem> inventoryItemList = new ArrayList<InventoryItem>();
        Map<String,String> values = new HashMap<String, String>();

        values.put("code","007123");
        values.put("description","Urgent order for a vey special client");

        Order order = OrderFactory
                .createOrder(20,values,inventoryItemList);

        Order newOrder = new Order
                .Builder(order.getCode())
                .copy(order)
                .description("Urgent order for a vey special client")
                .quantity(21)
                .build();
        org.junit.Assert.assertEquals("007123", newOrder.getCode());
        org.junit.Assert.assertEquals("Urgent order for a vey special client", newOrder.getDescription());
        org.junit.Assert.assertEquals(21, newOrder.getQuantity());
        org.junit.Assert.assertEquals(20, order.getQuantity());
    }

    @Test(dependsOnMethods = "update")
    public void delete() throws Exception{
        Order order = repository.findOne(id);
        repository.delete(order);
        Order newOrder = repository.findOne(id);
        Assert.assertNull(newOrder);
    }

}
