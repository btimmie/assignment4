package BookInventory.Services.Impl;

import BookInventory.Services.SupplierService;
import BookInventory.domain.Supplier;
import BookInventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/05/17.
 */
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private SupplierRepository repository;

    @Override
    public List<Supplier> getSuppliers() {
        List<Supplier> allSuppliers = new ArrayList<Supplier>();
        Iterable<Supplier> suppliers = repository.findAll();
        for(Supplier supplier:suppliers){
            allSuppliers.add(supplier);
        }
        return allSuppliers;
    }
}
