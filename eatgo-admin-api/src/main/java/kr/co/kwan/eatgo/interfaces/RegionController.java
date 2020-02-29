package kr.co.kwan.eatgo.interfaces;

import kr.co.kwan.eatgo.application.RegionService;
import kr.co.kwan.eatgo.domain.Region;
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
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list() {
        List<Region> regions = regionService.getRegions();
        return regions;
    }

    @PostMapping("/regions")
    public ResponseEntity<?> created(@RequestBody Region resource) throws URISyntaxException {
        String name = resource.getName();
        Region region = regionService.addRegion(name);

        String url = "/regions/" + region.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
