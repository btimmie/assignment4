package BookInventory.Services;

import BookInventory.domain.Order;
import BookInventory.domain.Supplier;

import java.util.List;

/**
 * Created by student on 2015/05/17.
 */
public interface SupplierOrderService {
    List<Supplier> getAllSuppliers();
    List<Order> getOrders(Long id);
}
