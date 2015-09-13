package BookInventory.Services.Impl;

import BookInventory.Services.SupplierOrderService;
import BookInventory.domain.Order;
import BookInventory.domain.Supplier;
import BookInventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/05/17.
 */
public class SupplierOrderServiceImpl implements SupplierOrderService {

    @Autowired
    private SupplierRepository repository;

    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        Iterable<Supplier> values = repository.findAll();
        for (Supplier value : values) {
            suppliers.add(value);
        }
        return suppliers;
    }

    @Override
    public List<Order> getOrders(Long id) {
        return repository.findOne(id).getOrderList();
    }
}
