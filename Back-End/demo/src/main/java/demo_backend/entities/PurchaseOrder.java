package demo_backend.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="purchase_orders")
public class PurchaseOrder {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrder that = (PurchaseOrder) o;
        return id.equals(that.id) && customerName.equals(that.customerName) && creationDate.equals(that.creationDate) && description.equals(that.description) && supplierName.equals(that.supplierName) && type.equals(that.type) && priority.equals(that.priority) && budgetCode.equals(that.budgetCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, creationDate, description, supplierName, type, priority, budgetCode);
    }

    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY) -> causa problemi se viene tolto l'autoincrement
    @NotNull
    @Column(name="id")
    private Integer id;

    @NotNull @Column(name="customer_name")
    private String customerName;

    @NotNull @Column(name="creation_date")
    private Timestamp creationDate;

    @Column(name="description")
    private String description;


    @NotNull @Column(name="supplier_name")
    private String supplierName;

    @NotNull @Column(name = "type")
    private String type;

    @NotNull @Column(name = "priority")
    private String priority;

    @NotNull
    @Column(name = "budget_code")
    private Integer budgetCode;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Integer getBudgetCode() {
        return budgetCode;
    }

    public void setBudgetCode(Integer budgetCode) {
        this.budgetCode = budgetCode;
    }



}
