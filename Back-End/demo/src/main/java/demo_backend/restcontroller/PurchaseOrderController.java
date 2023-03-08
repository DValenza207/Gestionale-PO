package demo_backend.restcontroller;

import com.querydsl.core.BooleanBuilder;
import demo_backend.entities.*;
import demo_backend.model.in.BrowsePurchaseOrder;
import demo_backend.model.out.ArticleDTO;
import demo_backend.model.out.POArticleDTO;
import demo_backend.model.out.PurchaseOrderDTO;
import demo_backend.repositories.ArticleRepository;
import demo_backend.repositories.CountersRepository;
import demo_backend.repositories.POArticleRepository;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import demo_backend.repositories.PurchaseOrderRepository;


import javax.persistence.LockModeType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200") //Per consentire il collegamento tra la porta 4200 e 8080
public class PurchaseOrderController {

    private PurchaseOrderRepository purchaseOrderRepository;
    private POArticleRepository poArticleRepository;
    private ArticleRepository articleRepository;

    private CountersRepository countersRepository;


    @Autowired
    public PurchaseOrderController(PurchaseOrderRepository purchaseOrderRepository, POArticleRepository poArticleRepository, ArticleRepository articleRepository, CountersRepository countersRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.poArticleRepository = poArticleRepository;
        this.articleRepository = articleRepository;
        this.countersRepository= countersRepository;
    }

    //Nella http request viene comunque richiesto l'inserimento di un id nonostante il campo dello stesso possegga una proprietà di autoincremento
    @PostMapping(value = "admin/Purchase_Orders/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Integer savePO(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {

        //Processo Contrario alla Get
        PurchaseOrder PO = new PurchaseOrder();
        BeanUtils.copyProperties(purchaseOrderDTO, PO);
        purchaseOrderRepository.save(PO); //Inserisce nel Database il PO
        //Controllo su purchaseOrderDTO.id se è valorizzato è una update e quindi elimino i POArticle
        if (!purchaseOrderDTO.isNew()) {
            poArticleRepository.deleteAllByIdPo(purchaseOrderDTO.getId());
            updatePO(purchaseOrderDTO);
        }
        //Altrimenti sara' una save a tutti gli effetti
        if (!purchaseOrderDTO.getPoArticleDTOS().isEmpty()) {
            for (POArticleDTO poArticleDTO : purchaseOrderDTO.getPoArticleDTOS()) {
                POArticle poArticle = new POArticle();
                poArticle.setIdPo(PO.getId());
                poArticle.setIdArticolo(poArticleDTO.getaDTO().getId());
                BeanUtils.copyProperties(poArticleDTO, poArticle);
                Optional<Article> aOpt = articleRepository.findById(poArticle.getIdArticolo());//lo utilizzo per trovarlo nel db
                if (!aOpt.isPresent())
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Articolo non trovato per id" + poArticle.getIdArticolo());
                poArticleRepository.save(poArticle);
            }
        }

        return PO.getId();
    }

    //Nella http request inserire direttamente il numero dell'id da cancellare
    @DeleteMapping(value = "admin/Purchase_Orders/delete")
    @Transactional
    public void deletePO(@RequestParam("id") Integer id) {
        if (id != null) {
            purchaseOrderRepository.deleteById(id);
            poArticleRepository.deleteAllByIdPo(id);
        }
    }

    //Nella http request inserire direttamente '?' seguito da 'id = numero id'
    @GetMapping("admin/Purchase_Orders/get")
    public PurchaseOrderDTO getPO(@RequestParam("id") Integer id) throws Exception {

        Optional<PurchaseOrder> poOpt = purchaseOrderRepository.findById(id);
        if (!poOpt.isPresent())
            return null;
        PurchaseOrder purchaseOrder = poOpt.get();
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        BeanUtils.copyProperties(purchaseOrder, purchaseOrderDTO);
        List<POArticle> poArticles = poArticleRepository.findAllByIdPo(id);
        if (!poArticles.isEmpty()) {
            for (POArticle PoA : poArticles) {
                POArticleDTO dto = new POArticleDTO();
                Integer idA = PoA.getIdArticolo();//ottengo l'id dell'articolo
                Optional<Article> aOpt = articleRepository.findById(idA);//lo utilizzo per trovarlo nel db
                if (!aOpt.isPresent())
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Articolo non trovato per id" + idA);
                Article article = aOpt.get();
                ArticleDTO articleDTO = new ArticleDTO();
                BeanUtils.copyProperties(article, articleDTO);//converto l'articolo(con l'id ottenuto) in un ArticleDTO
                dto.setaDTO(articleDTO);
                BeanUtils.copyProperties(PoA, dto);
                purchaseOrderDTO.getPoArticleDTOS().add(dto);
            }
        }
        return purchaseOrderDTO;
    }

    @PutMapping("admin/Purchase_Orders/update")
    public Optional<PurchaseOrder> updatePO(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        return purchaseOrderRepository.findById(purchaseOrderDTO.getId())
                .map(purchaseOrder -> {
                    purchaseOrder.setId(purchaseOrderDTO.getId());
                    purchaseOrder.setCustomerName(purchaseOrderDTO.getCustomerName());
                    purchaseOrder.setCreationDate(purchaseOrderDTO.getCreationDate());
                    purchaseOrder.setDescription(purchaseOrderDTO.getDescription());
                    purchaseOrder.setSupplierName(purchaseOrderDTO.getSupplierName());
                    purchaseOrder.setType(purchaseOrderDTO.getType());
                    purchaseOrder.setPriority(purchaseOrderDTO.getPriority());
                    purchaseOrder.setBudgetCode(purchaseOrderDTO.getBudgetCode());
                    return purchaseOrderRepository.save(purchaseOrder);
                });

    }

    @PostMapping("admin/Purchase_Orders/browse")
    public Page<PurchaseOrder> browsePO(@RequestBody BrowsePurchaseOrder browsePurchaseOrder) {

        Sort sorting = null;
        if (StringUtils.isNotBlank(browsePurchaseOrder.getSort())) {
            switch (browsePurchaseOrder.getSort()) {
                case "id":
                    sorting = Sort.by("id");
                    break;
                case "customerName":
                    sorting = Sort.by("customerName");
                    break;
                case "creationDate":
                    sorting = Sort.by("creationDate");
                    break;
                case "description":
                    sorting = Sort.by("description");
                    break;
                case "supplierName":
                    sorting = Sort.by("supplierName");
                    break;
                case "type":
                    sorting = Sort.by("type");
                    break;
                case "priority":
                    sorting = Sort.by("priority");
                    break;
                case "budgetCode":
                    sorting = Sort.by("budgetCode");
                    break;
            }
            if ("desc".equalsIgnoreCase(browsePurchaseOrder.getOrder())) {
                sorting = sorting.descending();
            } else {
                sorting = sorting.ascending();
            }
        }

        //Valori di default
        if (browsePurchaseOrder.getSort() == null) {
            sorting = Sort.by("id");
        }
        if (browsePurchaseOrder.getPage() == null) browsePurchaseOrder.setPage(0);
        if (browsePurchaseOrder.getSize() == null) browsePurchaseOrder.setSize(20);


        PageRequest pageRequest = PageRequest.of(browsePurchaseOrder.getPage(), browsePurchaseOrder.getSize(), sorting);

        BooleanBuilder where = new BooleanBuilder();
        //Filtri

        if (StringUtils.isNotBlank(browsePurchaseOrder.getType())) {
            where.and(QPurchaseOrder.purchaseOrder.type.eq(browsePurchaseOrder.getType()));
        }

        if (StringUtils.isNotBlank(browsePurchaseOrder.getDescription())) {
            where.and(QPurchaseOrder.purchaseOrder.description.eq(browsePurchaseOrder.getDescription()));
        }

        if (browsePurchaseOrder.getCreationDateFrom() != null) {
            where.andAnyOf(QPurchaseOrder.purchaseOrder.creationDate.eq(browsePurchaseOrder.getCreationDateFrom()), QPurchaseOrder.purchaseOrder.creationDate.after(browsePurchaseOrder.getCreationDateFrom()));
        }

        if (browsePurchaseOrder.getCreationDateTo() != null) {
            where.andAnyOf(QPurchaseOrder.purchaseOrder.creationDate.eq(browsePurchaseOrder.getCreationDateTo()), QPurchaseOrder.purchaseOrder.creationDate.before(browsePurchaseOrder.getCreationDateTo()));
        }

        Page<PurchaseOrder> pPurchaseOrders = purchaseOrderRepository.findAll(where, pageRequest);
        return pPurchaseOrders;
    }

    //Metodo che mostra il primo id disponibile per i PurchaseOrders
    @Transactional(isolation= Isolation.REPEATABLE_READ)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @GetMapping("admin/Purchase_Orders/getId")
    public synchronized Integer firstAvaiableId(){
        Integer value= countersRepository.findPOCounter();
        countersRepository.updatePOCounter();
        return value +1;
    }

}
