package demo_backend.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="articoli_purchase_order")
public class POArticle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull @Column(name="id_purchase_order")
    private Integer idPo;

    @NotNull @Column(name = "id_articolo")
    private Integer idArticolo;

    @NotNull
    private Integer quantity;


    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull @Column(name = "id_purchase_order")
    public Integer getIdPo() {
        return idPo;
    }

    public void setIdPo(Integer id_Po) {
        this.idPo = id_Po;
    }

    @NotNull @Column(name = "id_articolo")
    public Integer getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(Integer id_Articolo) {
        this.idArticolo = id_Articolo;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
