package com.example.demo.Spesification;

 import com.example.demo.Entities.Projet;
 import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ProjetSpecifications {

    public static Specification<Projet> dummy() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // Dummy specification
    }

    public static Specification<Projet> hassectionCategorie(String commentaire) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("commentaire"), "%" + commentaire + "%");
    }


    public static Specification<Projet> betweenDoubleValues(Double minValue, Double maxValue) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("section"), minValue, maxValue);
    }

    public static Specification<Projet> hasUserId(Long userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("myUser").get("id"), userId);
    }

}

