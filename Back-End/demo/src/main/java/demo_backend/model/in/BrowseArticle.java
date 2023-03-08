package demo_backend.model.in;

import java.util.Objects;

public class BrowseArticle {

    private Integer id;

    private String name;

    private String description;

    private Integer prezzo;

    private String sort;

    private String order;

    private Integer page;

    private Integer size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrezzo() { return prezzo; }

    public void setPrezzo(Integer prezzo) { this.prezzo = prezzo; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrowseArticle that = (BrowseArticle) o;
        return id.equals(that.id) && name.equals(that.name) && description.equals(that.description) && prezzo.equals(that.prezzo) && sort.equals(that.sort) && order.equals(that.order) && page.equals(that.page) && size.equals(that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, prezzo, sort, order, page, size);
    }
}
