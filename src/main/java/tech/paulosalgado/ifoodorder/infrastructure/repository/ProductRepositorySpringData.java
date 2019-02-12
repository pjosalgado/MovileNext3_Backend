package tech.paulosalgado.ifoodorder.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import tech.paulosalgado.ifoodorder.domain.Product;

public interface ProductRepositorySpringData extends CrudRepository<Product, String> {

}
