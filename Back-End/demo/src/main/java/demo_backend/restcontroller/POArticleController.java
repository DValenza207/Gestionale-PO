package demo_backend.restcontroller;

import demo_backend.entities.Article;
import demo_backend.entities.POArticle;
import demo_backend.model.out.ArticleDTO;
import demo_backend.model.out.POArticleDTO;
import demo_backend.repositories.ArticleRepository;
import demo_backend.repositories.POArticleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200") //Per consentire il collegamento tra la porta 4200 e 8080
public class POArticleController {

    @Autowired
    private POArticleRepository poArticleRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping(value = "admin/POArticles/save",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer savePOArticle(@RequestBody POArticleDTO poArticleDTO) throws Exception{

        try{
            poArticleDTO.check();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        POArticle PoA = new POArticle();
        PoA.setIdArticolo(poArticleDTO.getaDTO().getId());
        PoA.setQuantity(poArticleDTO.getQuantity());

        poArticleRepository.save(PoA); //Errore: la colonna id_purchase_order(presente in POArticle e non in POArticleDTO) non può essere null
        return PoA.getId();
    }

    @DeleteMapping(value = "admin/POArticles/delete/{id}")
    public void deletePOArticle(@PathVariable("id") Integer id){
        poArticleRepository.deleteById(id);
    }

    @GetMapping("admin/POArticles/get")
    public POArticleDTO getPOArticle(@RequestParam("id") Integer id) throws Exception{

        Optional<POArticle> poaOpt = poArticleRepository.findById(id);
        if(!poaOpt.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase Order dell'Articolo non trovato per id" + id);
        POArticle poArticle=poaOpt.get();
        POArticleDTO poArticleDTO1=new POArticleDTO();
        Optional<Article> A= articleRepository.findById(poaOpt.get().getIdArticolo());
        if(!A.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Articolo non trovato per id"+poaOpt.get().getIdArticolo());
        Article article= A.get();
        ArticleDTO articleDTO= new ArticleDTO();
        BeanUtils.copyProperties(article,articleDTO);
        poArticleDTO1.setaDTO(articleDTO);
        BeanUtils.copyProperties(poArticle,poArticleDTO1);
        return  poArticleDTO1;
    }

    @PutMapping("admin/POArticles/update")
    public Optional<POArticle> updatePOArticle(@RequestBody POArticleDTO poArticleDTO){
        return poArticleRepository.findById(poArticleDTO.getaDTO().getId())
                .map(poArticle -> {
                    poArticle.setIdArticolo(poArticleDTO.getaDTO().getId());
                    poArticle.setQuantity(poArticleDTO.getQuantity());
                    return poArticleRepository.save(poArticle);
                });
    }

    @PostMapping("admin/POArticles/browse")
    public List<POArticle> browsePOArticle(@RequestBody POArticle poArticle){

        if((poArticle.getId()==null)&&(poArticle.getIdPo()==null)&&(poArticle.getIdArticolo()==null)&&(poArticle.getQuantity()==null)){
            return (List<POArticle>) poArticleRepository.findAll();
        }

        else if((poArticle.getQuantity()==null)){
            return poArticleRepository.findAllByIdAndIdPoAndIdArticolo(poArticle.getId(),poArticle.getIdPo(),poArticle.getIdArticolo());
        }  else if((poArticle.getId()==null)){
            return  poArticleRepository.findAllByIdPoAndIdArticoloAndQuantityBefore(poArticle.getIdPo(),poArticle.getIdArticolo(),poArticle.getQuantity());
        }  else if((poArticle.getIdPo()==null)){
            return poArticleRepository.findAllByIdAndIdArticoloAndQuantityBefore(poArticle.getId(),poArticle.getIdArticolo(),poArticle.getQuantity());
        }  else if((poArticle.getIdArticolo()==null)){
            return poArticleRepository.findAllByIdAndIdPoAndQuantityBefore(poArticle.getId(),poArticle.getIdPo(),poArticle.getQuantity());
        }

        else if((poArticle.getId()==null)&&(poArticle.getIdPo()==null)&&(poArticle.getIdArticolo()==null)){
            return poArticleRepository.findAllByQuantityBefore(poArticle.getQuantity());
        } else if((poArticle.getId()==null)&&(poArticle.getQuantity()==null)){
            return poArticleRepository.findAllByIdPoAndIdArticolo(poArticle.getIdPo(),poArticle.getIdArticolo());
        } else if((poArticle.getIdPo()==null)&&(poArticle.getQuantity()==null)){
            return poArticleRepository.findAllByIdAndIdArticolo(poArticle.getId(),poArticle.getIdArticolo());
        } else if((poArticle.getIdArticolo()==null)&&(poArticle.getQuantity()==null)){
            return poArticleRepository.findAllByIdAndIdPo(poArticle.getId(),poArticle.getIdPo());
        } else if((poArticle.getId()==null)&&(poArticle.getIdPo()==null)&&(poArticle.getQuantity()==null)){
            return poArticleRepository.findAllByIdArticolo(poArticle.getIdArticolo());
        } else if((poArticle.getId()==null)&&(poArticle.getIdPo()==null)){
            return poArticleRepository.findAllByIdArticoloAndQuantityBefore(poArticle.getIdArticolo(),poArticle.getQuantity());
        } else if((poArticle.getId()==null)&&(poArticle.getIdArticolo()==null)&&(poArticle.getQuantity()==null)){
            return poArticleRepository.findAllByIdPo(poArticle.getIdPo());
        } else if((poArticle.getId()==null)&&(poArticle.getIdArticolo()==null)){
            return poArticleRepository.findAllByIdPoAndQuantityBefore(poArticle.getIdPo(),poArticle.getQuantity());
        } else if((poArticle.getIdPo()==null)&&(poArticle.getIdArticolo()==null)&&(poArticle.getQuantity()==null)){
            return poArticleRepository.findAllById(poArticle.getId());
        } else if((poArticle.getIdPo()==null)&&(poArticle.getIdArticolo()==null)){
            return poArticleRepository.findAllByIdAndQuantityBefore(poArticle.getId(),poArticle.getQuantity());
        }

        return poArticleRepository.findAllByIdAndIdPoAndIdArticoloAndQuantityBefore(poArticle.getId(),poArticle.getIdPo(),poArticle.getIdArticolo(),poArticle.getQuantity());

    }



}
