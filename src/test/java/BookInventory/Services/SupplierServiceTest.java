package BookInventory.Services;

import BookInventory.domain.Supplier;
import BookInventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by student on 2015/05/17.
 */
public class SupplierServiceTest {
    private Long id;

    @Autowired
    private SupplierService service;

    @Autowired
    private SupplierRepository repository;

    @Test
    public void create() throws Exception{
        Supplier supplier = new Supplier.Builder("ABC")
                .name("Book world").address("45 Lower Main Road Observatory").build();

        repository.save(supplier);
        id=supplier.getId();
        Assert.assertNotNull(supplier.getId());
    }

    @Test
    public void getAllSuppliers() throws Exception {
        List<Supplier> suppliers = service.getSuppliers();
        Assert.assertTrue(suppliers.size() ==0);
    }

}
