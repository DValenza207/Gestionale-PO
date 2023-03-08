package demo_backend.restcontroller;

import com.querydsl.core.BooleanBuilder;
import demo_backend.entities.Article;
import demo_backend.entities.QArticle;
import demo_backend.model.in.BrowseArticle;
import demo_backend.model.out.ArticleDTO;
import demo_backend.repositories.ArticleRepository;
import demo_backend.repositories.CountersRepository;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CountersRepository countersRepository;

    @PostMapping(value = "admin/Articles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer saveArticle(@RequestBody ArticleDTO articleDTO) throws Exception {

        try {
            articleDTO.check();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        Article A = new Article();
        A.setId(articleDTO.getId());
        A.setName(articleDTO.getName());
        A.setDescription(articleDTO.getDescription());
        A.setPrezzo(articleDTO.getPrezzo());
        articleDTO.setNew(false);

        articleRepository.save(A);
        return A.getId();
    }

    @DeleteMapping(value = "admin/Articles/delete/{id}")
    public void deleteArticle(@PathVariable("id") Integer id) {
        articleRepository.deleteById(id);
    }

    @GetMapping("admin/Articles/get")
    public ArticleDTO getArticle(@RequestParam("id") Integer id) throws Exception {

        Optional<Article> aOpt = articleRepository.findById(id);
        if (!aOpt.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Articolo non trovato per id" + id);
        Article article = aOpt.get();
        ArticleDTO articleDTO1 = new ArticleDTO();
        BeanUtils.copyProperties(article, articleDTO1);
        return articleDTO1;
    }

    @PutMapping("admin/Articles/update")
    public Optional<Article> updateArticle(@RequestBody ArticleDTO articleDTO) {
        return articleRepository.findById(articleDTO.getId())
                .map(article -> {
                    article.setId(articleDTO.getId());
                    article.setName(articleDTO.getName());
                    article.setDescription(articleDTO.getDescription());
                    article.setPrezzo(articleDTO.getPrezzo());
                    return articleRepository.save(article);
                });
    }

    @PostMapping("admin/Articles/browse")
    public Page<Article> browseArticle(@RequestBody BrowseArticle browseA) {

        Sort sorting = null;
        if (StringUtils.isNotBlank(browseA.getSort())) {
            switch (browseA.getSort()) {
                case "id":
                    sorting = Sort.by("id");
                    break;
                case "name":
                    sorting = Sort.by("name");
                    break;
                case "description":
                    sorting = Sort.by("description");
                    break;
                case "prezzo":
                    sorting=Sort.by("prezzo");
                    break;
            }
            if ("desc".equalsIgnoreCase(browseA.getOrder())) {
                sorting = sorting.descending();
            } else {
                sorting = sorting.ascending();
            }
        }

        //Valori di default
        if (browseA.getSort() == null) {
            sorting = Sort.by("id");
        }
        if (browseA.getPage() == null) browseA.setPage(0);
        if (browseA.getSize() == null) browseA.setSize(20);


        PageRequest pageRequest = PageRequest.of(browseA.getPage(), browseA.getSize(), sorting);
        BooleanBuilder where = new BooleanBuilder();
        //Filtri

        if (browseA.getId() != null) {
            where.and(QArticle.article.id.eq(browseA.getId()));
        }
        if (browseA.getName() != null && !browseA.getName().isBlank()) {
            where.and(QArticle.article.name.eq(browseA.getName()));
        }
        if (browseA.getDescription() != null && !browseA.getDescription().isBlank()) {
            where.and(QArticle.article.description.eq(browseA.getDescription()));
        }
        if(browseA.getPrezzo()!=null){
            where.and(QArticle.article.prezzo.eq(browseA.getPrezzo()));
        }
        Page<Article> pArticles = articleRepository.findAll(where, pageRequest);
        return pArticles;
    }

    //Metodo che mostra il primo id disponibile per gli Articoli
    @Transactional(isolation= Isolation.REPEATABLE_READ)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @GetMapping("admin/Articles/getAId")
    public synchronized Integer firstAvaiableAId(){
        Integer value = countersRepository.findACounter();
        countersRepository.updateACounter();
        return value+1;
    }

}
