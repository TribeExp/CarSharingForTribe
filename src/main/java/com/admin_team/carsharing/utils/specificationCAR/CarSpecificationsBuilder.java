package com.admin_team.carsharing.utils.specificationCAR;

import com.admin_team.carsharing.entity.CarEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

public class CarSpecificationsBuilder {private final List<SearchCriteriaCar> params;

    /**
     * Constructor - create new ArrayList<SearchCriteriaCar>
     */
    public CarSpecificationsBuilder() {
        params = new ArrayList<SearchCriteriaCar>();
    }

    /**
     * Accepts parameters and adds them to array list.
     * @return  {@link CarSpecificationsBuilder}
     */
    public CarSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteriaCar(key, operation, value));
        return this;
    }

    /**
     * Processing parameters, for the subsequent formation of a search query.
     * @return {@link Specification<CarEntity>}
     */
    public Specification<CarEntity> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<CarEntity>> specs = new ArrayList<Specification<CarEntity>>();
        for (SearchCriteriaCar param : params) {
            specs.add(new CarSpecification(param));
        }

        Specification<CarEntity> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
}