package demo_backend.model.out;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.Objects;

public class POArticleDTO {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        POArticleDTO that = (POArticleDTO) o;
        return aDTO.equals(that.aDTO) && quantity.equals(that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aDTO, quantity);
    }

    private ArticleDTO aDTO;

    private Integer quantity;


    public POArticleDTO(ArticleDTO aDTO, Integer quantity){

        this.aDTO=aDTO;
        this.quantity=quantity;

    }

    public POArticleDTO() {

    }


    public ArticleDTO getaDTO() {
        return aDTO;
    }

    public void setaDTO(ArticleDTO aDTO) {
        this.aDTO = aDTO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    //Validazione
    public boolean validate(Object target, Errors errors){
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"id_articolo","field required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"quantity","field required");

        if(aDTO.equals("_")|| aDTO.equals("_" + aDTO)) return false;
        if(quantity.equals("_")||quantity.equals("_"+quantity)) return false;
        else return true;
    }

    public void check() throws Exception{
        if(aDTO==null) throw new Exception("L'identificativo dell'Articolo non può essere nullo!");
        if(quantity == null) throw new Exception("La quantita' non può essere nulla!");
    }
}
