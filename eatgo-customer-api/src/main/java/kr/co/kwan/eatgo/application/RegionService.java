package kr.co.kwan.eatgo.application;

import kr.co.kwan.eatgo.domain.Region;
import kr.co.kwan.eatgo.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private CategoryRepository regionRepository;

    @Autowired
    public RegionService(CategoryRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getRegions() {
        List<Region> regions = regionRepository.findAll();

        return regions;
    }

}
