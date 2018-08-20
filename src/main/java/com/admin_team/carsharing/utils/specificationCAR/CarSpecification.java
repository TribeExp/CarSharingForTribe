package com.admin_team.carsharing.utils.specificationCAR;

import com.admin_team.carsharing.entity.CarEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CarSpecification implements Specification<CarEntity> {

    private SearchCriteriaCar criteria;

    public CarSpecification(SearchCriteriaCar searchCriteriaCar) {
        this.criteria = searchCriteriaCar;
    }

    @Override
    public Specification<CarEntity> and(Specification<CarEntity> other) {
        return null;
    }

    @Override
    public Specification<CarEntity> or(Specification<CarEntity> other) {
        return null;
    }

    /**
     * Build predicate.
     * @param root - A root type in the from clause,
     * @param query - defines functionality that is specific to top-level queries.
     * @param builder - Used to construct criteria queries, expressions, predicates.
     * @return  Predicate
     */
    @Override
    public Predicate toPredicate(Root<CarEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
