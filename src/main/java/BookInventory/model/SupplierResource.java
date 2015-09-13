package BookInventory.model;

import BookInventory.domain.InventoryItem;
import BookInventory.domain.Order;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by student on 2015/05/24.
 */
public class SupplierResource extends ResourceSupport {

    private Long resid;

    private String code;
    private String name;
    private String address;
    private List<InventoryItem> inventoryItemList;
    private List<Order> orderList;

    private SupplierResource(){
    }

    public SupplierResource(Builder builder){
        this.resid=builder.resid;
        this.code=builder.code;
        this.name=builder.name;
        this.address=builder.address;
        this.inventoryItemList=builder.inventoryItemList;
        this.orderList=builder.orderList;
    }

    public Long getResid() {
        return resid;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<InventoryItem> getInventoryItemList() {
        return inventoryItemList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public static class Builder{
        private long resid;
        private String code;
        private String name;
        private String address;
        private List<InventoryItem> inventoryItemList;
        private List<Order> orderList;

        public Builder(String code){
            this.code=code;
        }

        public Builder id(Long value){
            this.resid=value;
            return this;
        }

        public Builder name(String value){
            this.name=value;
            return this;
        }

        public Builder address(String value){
            this.address=value;
            return this;
        }

        public Builder inventoryItemList(List<InventoryItem> value){
            this.inventoryItemList=value;
            return this;
        }

        public Builder orderlist(List<Order> value){
            this.orderList=value;
            return this;
        }

        public Builder copy(SupplierResource value){
            this.resid=value.resid;
            this.code=value.code;
            this.name=value.name;
            this.address=value.address;
            this.inventoryItemList=value.inventoryItemList;
            this.orderList=value.orderList;
            return this;
        }

        public SupplierResource build(){
            return new SupplierResource(this);
        }
    }
}
