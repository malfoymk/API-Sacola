package sacola.api.sacola.service;

import org.springframework.stereotype.Service;
import sacola.api.sacola.model.ProductDTO;
import sacola.api.sacola.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAvailableProducts() {
        return productRepository.findByDisponivelTrue()
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> findProductsByLoja(Long lojaId) {
        return productRepository.findByLojaIdAndDisponivelTrue(lojaId)
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }
}