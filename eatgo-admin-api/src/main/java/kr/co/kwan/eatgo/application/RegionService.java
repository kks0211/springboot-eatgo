package kr.co.kwan.eatgo.application;

import kr.co.kwan.eatgo.domain.Region;
import kr.co.kwan.eatgo.domain.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public List<Region> getRegions() {
        List<Region> regions = regionRepository.findAll();

        return regions;
    }

    public Region addRegion(String name) {
        Region region = Region.builder().name(name).build();

        regionRepository.save(region);

        return region;
    }

}
