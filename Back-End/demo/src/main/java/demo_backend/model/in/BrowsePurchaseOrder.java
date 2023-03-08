package demo_backend.model.in;

import java.sql.Timestamp;
import java.util.Objects;

public class BrowsePurchaseOrder {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrowsePurchaseOrder that = (BrowsePurchaseOrder) o;
        return Id.equals(that.Id) && creationDateFrom.equals(that.creationDateFrom) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, creationDateFrom, type);
    }

    private Integer Id;//si può rimuovere

    private Timestamp creationDateFrom;

    private Timestamp creationDateTo;

    private String type;

    private String description;

    private Integer page;

    private Integer size;

    private String sort;

    private String order;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Timestamp getCreationDateFrom() {
        return creationDateFrom;
    }

    public void setCreationDateFrom(Timestamp creationDateFrom) {
        this.creationDateFrom = creationDateFrom;
    }

    public Timestamp getCreationDateTo() {
        return creationDateTo;
    }

    public void setCreationDateTo(Timestamp creationDateTo) {
        this.creationDateTo = creationDateTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
