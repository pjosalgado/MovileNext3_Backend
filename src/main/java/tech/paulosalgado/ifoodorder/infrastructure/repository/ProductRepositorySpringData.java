package tech.paulosalgado.ifoodorder.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import tech.paulosalgado.ifoodorder.domain.product.Product;

import java.util.UUID;

public interface ProductRepositorySpringData extends CrudRepository<Product, UUID> {

}
