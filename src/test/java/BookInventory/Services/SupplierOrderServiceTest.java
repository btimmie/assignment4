package BookInventory.Services;

import BookInventory.App;
import BookInventory.config.factory.OrderFactory;
import BookInventory.config.factory.SupplierFactory;
import BookInventory.domain.Order;
import BookInventory.domain.Supplier;
import BookInventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by student on 2015/05/17.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class SupplierOrderServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private SupplierService service;

    private Long id;

    @Autowired
    private SupplierRepository repository;
    private List<Order> orders;


    @BeforeMethod
    public void setUp() throws Exception {
        orders = new ArrayList<Order>();

    }

    @Test
    public void create() throws Exception{
        Map<String,String> values = new HashMap<String,String>();
        values.put("1234","cool");


        Order order = OrderFactory
                .createOrder(5,values,null);

        orders.add(order);

        Supplier supplier = SupplierFactory
                .createSuplier(values,null,orders);

        repository.save(supplier);
        id=supplier.getId();
        Assert.assertNotNull(order.getId());
    }

    @Test
    public void testGetFacultyDepartmets() throws Exception {
        List<Supplier> order = service.getSuppliers();
        Assert.assertNotNull(orders);

    }
}
