package com.wolf.hookahshopee.product.specification;

import com.wolf.hookahshopee.product.dto.ProductListRequestDTO;
import com.wolf.hookahshopee.product.model.Product;
import com.wolf.hookahshopee.product.model.ProductItem;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class ProductListSpecification {

    private Specification<Product> priceInRange(Long startPrice, Long endPrice) {
        return (root, query, cb) -> {
            if (startPrice == null) {
                return null;
            }

            if (endPrice == null) {
                return null;
            }

            return cb.between(root.get("finalPrice"), startPrice, endPrice);
        };
    }

    private Specification<Product> manufacturerIn(List<UUID> manufacturers) {
        return (root, query, cb) -> {
            if (manufacturers == null || manufacturers.isEmpty()) {
                return null;
            }

            return root.get("manufacturer").get("uuid").in(manufacturers);
        };
    }

    private Specification<Product> isOnDiscount(Boolean isOnDiscount) {
        return (root, query, cb) -> {
            if (isOnDiscount == null) {
                return null;
            }

            if (isOnDiscount) {
                return cb.greaterThan(root.get("discount"), 0);
            } else {
                return cb.equal(root.get("discount"), 0);
            }
        };
    }

    private Specification<Product> isInStock(UUID cityUUID) {
        return (root, query, cb) -> {
            if (cityUUID == null) {
                return null;
            }

            Join<Product, ProductItem> productItemJoin = root.join("productItems");
            return cb.and(cb.equal(productItemJoin.get("seller").get("city").get("uuid"), cityUUID), cb.greaterThan(productItemJoin.get("quantity"), 0));
        };
    }

    public Specification<Product> getFilter(ProductListRequestDTO request) {
        return (root, query, cb) -> {
            query.distinct(true);
            return where(priceInRange(request.getStartPrice(), request.getEndPrice()))
                    .and(isOnDiscount(request.getIsOnDiscount()))
                    .and(manufacturerIn(request.getManufacturers()))
                    .and(isInStock(request.getCityUUID()))
                    .toPredicate(root, query, cb);
        };
    }
}
