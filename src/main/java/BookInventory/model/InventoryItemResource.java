package BookInventory.model;


import BookInventory.domain.Consumtion;
import BookInventory.domain.Employee;
import BookInventory.domain.Return;
import BookInventory.domain.Supplier;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by student on 2015/05/24.
 */
public class InventoryItemResource extends ResourceSupport {

    private Long resid;

    private String code;
    private String name;
    private String description;

    private List<Consumtion> consumtionList;

    private List<Return> returnList;

    private Supplier supplier;
    private Employee employee;

    private InventoryItemResource(){
    }

    public InventoryItemResource(Builder builder){
        this.resid=builder.resid;
        this.supplier=builder.supplier;
        this.code=builder.code;
        this.name=builder.name;
        this.description=builder.description;
        this.consumtionList=builder.consumtionList;
        this.returnList=builder.returnList;
    }

    public Long getResid() {
        return resid;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Consumtion> getConsumtionList() {
        return consumtionList;
    }

    public List<Return> getReturnList(){
        return returnList;
    }

    public static class Builder{
        private Long resid;
        private Supplier supplier;
        private String code;
        private String name;
        private String description;
        private List<Consumtion> consumtionList;
        private List<Return> returnList;

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

        public Builder supplier(Supplier value){
            this.supplier=value;
            return this;
        }

        public Builder description(String value){
            this.description=value;
            return this;
        }

        public Builder consumtionList(List<Consumtion> value){
            this.consumtionList =value;
            return this;
        }

        public Builder returnList(List<Return> value){
            this.returnList =value;
            return this;
        }

        public Builder copy(InventoryItemResource value){
            this.supplier = value.getSupplier();
            this.code = value.getCode();
            this.name = value.getName();
            this.description = value.getDescription();
            this.consumtionList = value.getConsumtionList();
            return this;
        }

        public InventoryItemResource build(){
            return new InventoryItemResource(this);
        }
    }
}
