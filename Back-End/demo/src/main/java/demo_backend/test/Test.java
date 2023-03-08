package demo_backend.test;

import demo_backend.entities.Article;
import demo_backend.entities.PurchaseOrder;
import demo_backend.model.in.BrowseArticle;
import demo_backend.model.in.BrowsePurchaseOrder;
import demo_backend.model.out.ArticleDTO;
import demo_backend.model.out.POArticleDTO;
import demo_backend.model.out.PurchaseOrderDTO;
import demo_backend.repositories.ArticleRepository;
import demo_backend.repositories.POArticleRepository;
import demo_backend.repositories.PurchaseOrderRepository;
import demo_backend.restcontroller.ArticleController;
import demo_backend.restcontroller.PurchaseOrderController;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class Test {

    private PurchaseOrderRepository purchaseOrderRepository;
    private POArticleRepository poArticleRepository;
    private ArticleRepository articleRepository;
    private ArticleController articleController;
    private PurchaseOrderController purchaseOrderController;

    @Autowired
    public Test(PurchaseOrderRepository purchaseOrderRepository, POArticleRepository poArticleRepository, ArticleRepository articleRepository, ArticleController articleController, PurchaseOrderController purchaseOrderController) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.poArticleRepository = poArticleRepository;
        this.articleRepository = articleRepository;
        this.articleController = articleController;
        this.purchaseOrderController = purchaseOrderController;
    }


    @org.junit.jupiter.api.Test
    public void AssertArticleDTOFieldsNotNull() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(1);
        articleDTO.setName("Asse Di Legno");
        assertAll("heading",
                () -> Assert.notNull(articleDTO.getId(), () -> "L'identificativo dell'articolo non può essere null"),
                () -> Assert.notNull(articleDTO.getName(), () -> "Il nome dell'articolo non può essere null!")
        );
    }

    @org.junit.jupiter.api.Test
    public void AssertPurchaseOrderDTOFieldsNotNull() {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setId(3);
        purchaseOrderDTO.setCustomerName("Priano Marchelli");
        purchaseOrderDTO.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        purchaseOrderDTO.setDescription("Estintore");
        purchaseOrderDTO.setSupplierName("MV CORAL PRINCESS");
        purchaseOrderDTO.setType("componente");
        purchaseOrderDTO.setPriority("urgente");
        purchaseOrderDTO.setBudgetCode(101);
        assertAll("heading",
                () -> Assert.notNull(purchaseOrderDTO.getId(), () -> "L'identificativo del Purchase Order non può essere null!"),
                () -> Assert.notNull(purchaseOrderDTO.getCustomerName(), () -> "il nome del cliente non può essere null!"),
                () -> Assert.notNull(purchaseOrderDTO.getCreationDate(), () -> "La data di creazione del Purchase Order non può essere null!"),
                () -> Assert.notNull(purchaseOrderDTO.getSupplierName(), () -> "Il nome del fornitore non può essere null!"),
                () -> Assert.notNull(purchaseOrderDTO.getType(), () -> "La tipologia del Purchase Order non può essere null!"),
                () -> Assert.notNull(purchaseOrderDTO.getPriority(), () -> "La priorità del PO non può essere null!"),
                () -> Assert.notNull(purchaseOrderDTO.getBudgetCode(), () -> "il budget code del Purchase Order non può essere null!")
        );
    }

    @org.junit.jupiter.api.Test
    public void AssertCheckOnPurchaseOrderDTOidThrowsException() throws Exception {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setId(null);
        Exception exception = assertThrows(Exception.class, () -> {
            purchaseOrderDTO.check();
        });
        String expectedMessage = "l'identificativo non può essere nullo!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    public void AssertCheckOnPurchaseOrderDTOcustomerNameThrowsException() throws Exception {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setCustomerName(" ");
        Exception exception = assertThrows(Exception.class, () -> {
            purchaseOrderDTO.check();
        });
    }

    @org.junit.jupiter.api.Test
    public void AssertCheckOnPurchaseOrderDTOcreationDateThrowsException() throws Exception {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setCreationDate(null);
        Exception exception = assertThrows(Exception.class, () -> {
            purchaseOrderDTO.check();
        });
    }

    @org.junit.jupiter.api.Test
    public void AssertCheckOnPurchaseOrderDTOsupplierNameThrowsException() throws Exception {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setSupplierName(" ");
        Exception exception = assertThrows(Exception.class, () -> {
            purchaseOrderDTO.check();
        });
    }

    @org.junit.jupiter.api.Test
    public void AssertCheckOnPurchaseOrderDTOtypeThrowsException() throws Exception {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setType(" ");
        Exception exception = assertThrows(Exception.class, () -> {
            purchaseOrderDTO.check();
        });
    }

    @org.junit.jupiter.api.Test
    public void AssertCheckOnPurchaseOrderDTOpriorityThrowsException() throws Exception {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setPriority(" ");
        Exception exception = assertThrows(Exception.class, () -> {
            purchaseOrderDTO.check();
        });
    }

    @org.junit.jupiter.api.Test
    public void AssertCheckOnPurchaseOrderDTObudgetCodeThrowsException() throws Exception {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setBudgetCode(null);
        Exception exception = assertThrows(Exception.class, () -> {
            purchaseOrderDTO.check();
        });
    }

    @org.junit.jupiter.api.Test
    public void AssertCheckOnArticleDTOidThrowsException() throws Exception {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(null);
        Exception exception = assertThrows(Exception.class, () -> {
            articleDTO.check();
        });

        String expectedMessage = "L'identificativo non può essere nullo!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    public void AssertCheckOnArticleDTOnameThrowsException() throws Exception {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setName(" ");
        Exception exception = assertThrows(Exception.class, () -> {
            articleDTO.check();
        });
    }


    //Test sulla validazione
    @org.junit.jupiter.api.Test
    public void UnderscoreOnArticleDTOFieldsThrowsException() throws Exception {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setName("_Asse Di Legno");
        articleDTO.setDescription("_Asse Di Legno 150x30x5");
        assertThrows(Exception.class, () -> {
            articleDTO.check();
        });
    }

    @org.junit.jupiter.api.Test
    public void UnderscoreOnPurchaseOrderDTOFieldsThrowsException() throws Exception {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setCustomerName("_Priano Marchelli");
        purchaseOrderDTO.setDescription("_Estintore");
        purchaseOrderDTO.setSupplierName("_MV CORAL PRINCESS");
        purchaseOrderDTO.setType("_componente");
        purchaseOrderDTO.setPriority("_urgente");
        assertThrows(Exception.class, () -> {
            purchaseOrderDTO.check();
        });
    }


    //Tests sul DB
    @org.junit.jupiter.api.Test
    void findArticleByNameTest() {
        Pageable sortedByName=PageRequest.of(0,20,Sort.by("name").ascending());
        List<Article> articles = articleRepository.findAllByName("Rubinetto", sortedByName);
        Assertions.assertNotNull(articles);
        Assertions.assertEquals("Rubinetto", articles.stream().findAny().get().getName());
    }

    @org.junit.jupiter.api.Test
    void findPurchaseOrderByTypeTest() {
        Pageable sortedByType= PageRequest.of(0,20, Sort.by("type").ascending());
        List<PurchaseOrder> POs = purchaseOrderRepository.findAllByType("componente", sortedByType);
        Assertions.assertNotNull(POs);
        Assertions.assertEquals("componente", POs.stream().findAny().get().getType());
    }


    //Test per effettuare la richiesta http e verifica sul db

    //Test per la save farlocchi
    @org.junit.jupiter.api.Test
    public void saveArticleTest() {
        Article article = new Article();
        article.setName("test article");
        article.setDescription("test description");
        Article savedArticle = articleRepository.save(article);
        Assertions.assertEquals(article, savedArticle);
    }

    @org.junit.jupiter.api.Test
    public void savePurchaseOrderTest() {
        PurchaseOrder po = new PurchaseOrder();
        PurchaseOrderDTO poDTO = new PurchaseOrderDTO();
        poDTO.setCustomerName("test customer");
        poDTO.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        poDTO.setDescription("test description");
        poDTO.setSupplierName("test supplier");
        poDTO.setType("test type");
        poDTO.setPriority("test priority");
        poDTO.setBudgetCode(1);
        poDTO.setPoArticleDTOS(Collections.singletonList(new POArticleDTO(new ArticleDTO(11, "test name", "test description",14,false), 10)));
        BeanUtils.copyProperties(poDTO, po);
        PurchaseOrder savedPO = purchaseOrderRepository.save(po);
        Assertions.assertEquals(po, savedPO);
    }


    //Tests per le save prese dai controllers
    @org.junit.jupiter.api.Test
    public void controllerSaveArticle() throws Exception {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(13);
        articleDTO.setName("test name2");
        articleDTO.setDescription("test description2");
        Integer savedADTO = articleController.saveArticle(articleDTO);
        Assertions.assertEquals(articleDTO.getId(), savedADTO);
    }

    //Test dell'update del PurchaseOrder
    @org.junit.jupiter.api.Test
    public void controllerSaveUpdatePO() throws Exception {
        PurchaseOrderDTO poDTO = new PurchaseOrderDTO();
        poDTO.setId(23);
        poDTO.setCustomerName("test customer");
        poDTO.setCreationDate(Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
        poDTO.setDescription("test description");
        poDTO.setSupplierName("test supplier");
        poDTO.setType("test type");
        poDTO.setPriority("test priority");
        poDTO.setBudgetCode(1);
        poDTO.setPoArticleDTOS(Collections.singletonList(new POArticleDTO(new ArticleDTO(11, "test article", "test description", 14, false), 10)));
        Integer savedPurchaseOrderDTO = purchaseOrderController.savePO(poDTO);
        Assertions.assertEquals(poDTO.getId(), savedPurchaseOrderDTO);
        //utilizzo savedPurchaseOrderDTO per usare la get e vedere se il PoDTO è quello sul db
        PurchaseOrderDTO gotPoDTO = purchaseOrderController.getPO(savedPurchaseOrderDTO);
        Assertions.assertEquals(gotPoDTO, poDTO);
    }
    //Verificare che non venga aggiornato(Update) un PO che non esiste
    @org.junit.jupiter.api.Test
    public void controllerUpdatePOThatNotExists(){
        //Per essere sicuri utilizzare la delete(by id) ed utilizzare un id improbabile(999...)
        PurchaseOrderDTO poDTO = new PurchaseOrderDTO();
        poDTO.setId(24);
        poDTO.setCustomerName("test customer");
        poDTO.setCreationDate(Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
        poDTO.setDescription("test description");
        poDTO.setSupplierName("test supplier");
        poDTO.setType("test type");
        poDTO.setPriority("test priority");
        poDTO.setBudgetCode(1);
        poDTO.setPoArticleDTOS(Collections.singletonList(new POArticleDTO(new ArticleDTO(11, "test article", "test description", 14,false), 10)));
        Optional<PurchaseOrder> poOpt= purchaseOrderController.updatePO(poDTO);
        Assertions.assertFalse(poOpt.isPresent());//Controllo che ciò che viene restituito dalla updatePO non sia presente

    }



    // Test per la save del nuovo po (id = null) con 3 articoli, prendere id ritornato, aggiornare id sul po,
    // togliere dal po 1 articolo, fare la save(con id impostato, quindi una update),
    // fare la get(id) del po e controllare che gli articoli siano 2
    @org.junit.jupiter.api.Test
    public void controllerSavePOwith3Articles() throws Exception {
        PurchaseOrderDTO poDTO = new PurchaseOrderDTO();
        poDTO.setCustomerName("test customer");
        poDTO.setCreationDate(Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
        poDTO.setDescription("test description");
        poDTO.setSupplierName("test supplier");
        poDTO.setType("test type");
        poDTO.setPriority("test priority");
        poDTO.setBudgetCode(1);
        poDTO.getPoArticleDTOS().add(new POArticleDTO(new ArticleDTO(11,"test article2","test description2",14, false),14));
        poDTO.getPoArticleDTOS().add(new POArticleDTO(new ArticleDTO(12,"test article3","test description3",11, false),15));
        poDTO.getPoArticleDTOS().add(new POArticleDTO(new ArticleDTO(13,"test article2","test description2",22, false),16));
        Integer savedPurchaseOrderDTO = purchaseOrderController.savePO(poDTO);
        poDTO.setId(savedPurchaseOrderDTO); //qui aggiorno l'id sul po
        poDTO.getPoArticleDTOS().remove(0);
        purchaseOrderController.savePO(poDTO);
        PurchaseOrderDTO gotPoDTOupdated= purchaseOrderController.getPO(poDTO.getId());
        assertEquals(2, (long) gotPoDTOupdated.getPoArticleDTOS().size());
    }

    //Test per la save nuovo po(id = null), prendere id ritornato,
    //fare la delete(id), fare la get(id) e controllare che la get non torni nulla (cioe' il po e' stato effettivamente cancellato)

    @org.junit.jupiter.api.Test
    public void controllerSavePOWithIdDeleted() throws Exception {
        //servirà una assertfalse sul risultato della get
        PurchaseOrderDTO poDTO = new PurchaseOrderDTO();
        poDTO.setCustomerName("test customer");
        poDTO.setCreationDate(Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
        poDTO.setDescription("test description");
        poDTO.setSupplierName("test supplier");
        poDTO.setType("test type");
        poDTO.setPriority("test priority");
        poDTO.setBudgetCode(1);
        poDTO.getPoArticleDTOS().add(new POArticleDTO(new ArticleDTO(10,"test article2","test description2",33,false),15));
        Integer savedPurchaseOrderDTO = purchaseOrderController.savePO(poDTO);
        purchaseOrderController.deletePO(savedPurchaseOrderDTO);
        PurchaseOrderDTO gotPoDTO = purchaseOrderController.getPO(savedPurchaseOrderDTO);
        Assertions.assertNull(gotPoDTO);
        //Controllo anche che non esistano POArticleDTO associati al PO cancellato
        Assertions.assertTrue(poArticleRepository.findAllByIdArticolo(10).isEmpty());
    }

    //Test per la save del po(con id=null poichè autoincrementato)
    @org.junit.jupiter.api.Test
    public void controllerSavePO() throws Exception {
        PurchaseOrderDTO poDTO = new PurchaseOrderDTO();
        poDTO.setCustomerName("test customer");
        poDTO.setCreationDate(Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
        poDTO.setDescription("test description");
        poDTO.setSupplierName("test supplier");
        poDTO.setType("test type");
        poDTO.setPriority("test priority");
        poDTO.setBudgetCode(1);
        poDTO.setPoArticleDTOS(Collections.singletonList(new POArticleDTO(new ArticleDTO(11, "test article", "test description",14, false), 10)));
        Integer savedPurchaseOrderDTO = purchaseOrderController.savePO(poDTO);
        //utilizzo savedPurchaseOrderDTO per usare la get e vedere se il PoDTO è quello sul db
        PurchaseOrderDTO gotPoDTO = purchaseOrderController.getPO(savedPurchaseOrderDTO);
        Assertions.assertNotNull(gotPoDTO.getId()); //Interviene l'autoincrement
        Assertions.assertAll("heading",
                ()->assertEquals(savedPurchaseOrderDTO,gotPoDTO.getId()),
                ()->assertEquals(gotPoDTO.getCustomerName(),poDTO.getCustomerName()),
                ()->assertEquals(gotPoDTO.getCreationDate(),poDTO.getCreationDate()),
                ()->assertEquals(gotPoDTO.getDescription(),poDTO.getDescription()),
                ()->assertEquals(gotPoDTO.getSupplierName(),poDTO.getSupplierName()),
                ()->assertEquals(gotPoDTO.getType(),poDTO.getType()),
                ()->assertEquals(gotPoDTO.getPriority(),poDTO.getPriority()),
                ()->assertEquals(gotPoDTO.getBudgetCode(),poDTO.getBudgetCode()),
                ()->assertEquals(gotPoDTO.getPoArticleDTOS(),poDTO.getPoArticleDTOS())
        );
    }
    //Verificare che non venga creato un po contenente uno o più po articoli in cui uno o più articoli non esistono sul db
    @org.junit.jupiter.api.Test
    public void controllerSavePOThatHasPOArticleWithArticleThatNotExists(){
        PurchaseOrderDTO poDTO = new PurchaseOrderDTO();
        poDTO.setCustomerName("test customer");
        poDTO.setCreationDate(Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
        poDTO.setDescription("test description");
        poDTO.setSupplierName("test supplier");
        poDTO.setType("test type");
        poDTO.setPriority("test priority");
        poDTO.setBudgetCode(1);
        poDTO.getPoArticleDTOS().add(new POArticleDTO(new ArticleDTO(14,"test article2","test description2",8, false),14));
        poDTO.getPoArticleDTOS().add(new POArticleDTO(new ArticleDTO(15,"test article3","test description3",7, false),15));
        poDTO.getPoArticleDTOS().add(new POArticleDTO(new ArticleDTO(16,"test article2","test description2",6, false),16));
        Assertions.assertThrows(Exception.class, ()->{
            purchaseOrderController.savePO(poDTO); //Mi aspetto che lanci un'eccezione dato che gli articoli non vengono trovati per id
        });
    }




    //Test per l'update dell'Articolo
    @org.junit.jupiter.api.Test
    public void controllerUpdateArticle() {
        ArticleDTO articleDTO = new ArticleDTO();
        Article a = new Article();
        articleDTO.setId(13);
        articleDTO.setName("test nameUpdate");
        articleDTO.setDescription("test descriptionUpdate");
        Optional<Article> aOPT = articleController.updateArticle(articleDTO);
        BeanUtils.copyProperties(articleDTO, a);
        Assertions.assertEquals(aOPT.get(), a); //Utilizzo get() poichè Optional
    }


    //Test per le Browse
   /* @org.junit.jupiter.api.Test
    public void controllerBrowseArticle() {
        BrowseArticle a = new BrowseArticle();
        a.setName("test article");
        a.setDescription("test description");
        a.setPrezzo(99);
        a.setPage(0);
        a.setSize(10);
        a.setSort("name");
        a.setOrder("asc");

        Page<Article> aPage = articleController.browseArticle(g);
        Assertions.assertNotNull(aPage);
        Assertions.assertTrue(aPage.getContent().get(0) instanceof Article);//controllo che la lista sia proprio una lista di Article
    }*/

    @org.junit.jupiter.api.Test
    public void controllerBrowsePO() {
        BrowsePurchaseOrder bPO = new BrowsePurchaseOrder();
        bPO.setId(23);
        bPO.setCreationDateFrom(Timestamp.valueOf(LocalDateTime.of(2022,07,20,10,04)));
        bPO.setCreationDateTo(Timestamp.valueOf(LocalDateTime.of(2022,10,30,13,15)));
        bPO.setType("test type");
        bPO.setDescription("test description");
        bPO.setPage(0);
        bPO.setSize(10);
        bPO.setSort("type");
        bPO.setOrder("asc");


        Page<PurchaseOrder> poPage = purchaseOrderController.browsePO(bPO);
        Assertions.assertNotNull(poPage);
        Assertions.assertTrue(poPage.getContent().get(0) instanceof PurchaseOrder);//controllo che la lista sia proprio una lista di PurchaseOrder
    }
}
