package kr.co.kwan.eatgo.interfaces;

import kr.co.kwan.eatgo.application.CategoryService;
import kr.co.kwan.eatgo.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

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
