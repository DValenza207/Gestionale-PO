package demo_backend.model.out;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PurchaseOrderDTO {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderDTO that = (PurchaseOrderDTO) o;
        return id.equals(that.id) && customerName.equals(that.customerName) && creationDate.equals(that.creationDate) && description.equals(that.description) && supplierName.equals(that.supplierName) && type.equals(that.type) && priority.equals(that.priority) && budgetCode.equals(that.budgetCode) && poArticleDTOS.equals(that.poArticleDTOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, creationDate, description, supplierName, type, priority, budgetCode, poArticleDTOS, isNew);
    }

    private Integer id;

    private String customerName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private Timestamp creationDate;

    private String description;

    private String supplierName;

    private String type;

    private String priority;

    private Integer budgetCode;

    private List<POArticleDTO> poArticleDTOS = new ArrayList<>();

    private boolean isNew;

    public PurchaseOrderDTO(Integer id, String customerName, Timestamp creationDate, String description, String supplierName, String type, String priority, Integer budgetCode, List<POArticleDTO> poArticleDTOS, boolean isNew) {

        this.id = id;
        this.customerName = customerName;
        this.creationDate = creationDate;
        this.description = description;
        this.supplierName = supplierName;
        this.type = type;
        this.priority = priority;
        this.budgetCode = budgetCode;
        this.poArticleDTOS = poArticleDTOS;
        this.isNew= isNew;
    }

    public PurchaseOrderDTO() {

    }

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

    public List<POArticleDTO> getPoArticleDTOS() {
        return poArticleDTOS;
    }

    public void setPoArticleDTOS(List<POArticleDTO> poArticleDTOS) {
        this.poArticleDTOS = poArticleDTOS;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    //Validazione e controllo

    public void check() throws Exception {
        if (id == null) throw new Exception("l'identificativo non può essere nullo!");
        if (customerName.isBlank()) throw new Exception("il nome del cliente non può essere vuoto!");
        if (creationDate.equals(null)) throw new Exception("la data di creazione dell'ordine non può essere vuota!");
        if (supplierName.isBlank()) throw new Exception("il nome del fornitore non può essere vuoto!");
        if (type.isBlank()) throw new Exception("il tipo di ordine è obbligatorio!");
        if (priority.isBlank()) throw new Exception("la priorità dell'ordine è obbligatoria!");
        if (budgetCode == null) throw new Exception("il butget code non può essere nullo!");

        if (customerName.equals("_") || customerName.equals("_" + customerName)) throw new Exception("non è consentito l'utilizzo del carattere _!");
        if (description.equals("_") || description.equals("_" + description)) throw new Exception("non è consentito l'utilizzo del carattere _!");
        if (supplierName.equals("_") || supplierName.equals("_" + supplierName)) throw new Exception("non è consentito l'utilizzo del carattere _!");
        if (type.equals("_") || type.equals("_" + type)) throw new Exception("non è consentito l'utilizzo del carattere _!");
        if (priority.equals("_") || priority.equals("_" + priority)) throw new Exception("non è consentito l'utilizzo del carattere _!");

    }
}
