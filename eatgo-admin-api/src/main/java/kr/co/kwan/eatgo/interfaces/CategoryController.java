package kr.co.kwan.eatgo.interfaces;

import kr.co.kwan.eatgo.application.CategoryService;
import kr.co.kwan.eatgo.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/categories")
    public List<Category> list() {
        List<Category> category = categoryService.getCategories();
        return category;
    }

    @PostMapping("/categories")
    public ResponseEntity<?> created(@RequestBody Category resource) throws URISyntaxException {
        String name = resource.getName();
        Category category = categoryService.addCategory(name);

        String url = "/categories/" + category.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
