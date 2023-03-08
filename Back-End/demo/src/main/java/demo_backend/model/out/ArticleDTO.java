package demo_backend.model.out;

import com.sun.istack.NotNull;

import java.util.Objects;

public class ArticleDTO {
    @NotNull
    private Integer id;
    @NotNull
    private String name;

    private String description;

    @NotNull
    private Integer prezzo;

    private boolean isNew;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDTO that = (ArticleDTO) o;
        return id.equals(that.id) && name.equals(that.name) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isNew);
    }

    public ArticleDTO(Integer id, String name, String description, Integer prezzo, boolean isNew){

        this.id=id;
        this.name=name;
        this.description=description;
        this.prezzo=prezzo;
        this.isNew= isNew;
    }

    public ArticleDTO() {

    }

    @NotNull
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
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

    public Integer getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Integer prezzo) {
        this.prezzo = prezzo;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    //Validazione e controllo

    public void check() throws Exception{
            if(id==null) throw new Exception("L'identificativo non può essere nullo!");
            if(name.isBlank()) throw new Exception("Il nome dell'articolo non può essere vuoto!");
            if(name.equals("_")|| name.equals("_"+ name) ) throw new Exception("non è consentito l'utilizzo del carattere _!");
            if(description.equals("_")||description.equals("_"+description))throw new Exception("non è consentito l'utilizzo del carattere _!");
            if(prezzo==null) throw  new Exception("Il prezzo non può essere nullo!");
    }
}
