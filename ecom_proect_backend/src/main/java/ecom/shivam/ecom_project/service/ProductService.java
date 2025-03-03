package ecom.shivam.ecom_project.service;

import ecom.shivam.ecom_project.model.Product;
import ecom.shivam.ecom_project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
        }


    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }


    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        productRepo.save(product);
        return product;
    }

    public Product updateProduct(int id,Product product, MultipartFile imageFile) throws IOException {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        productRepo.save(product);
        return product;
    }

    public void deleteProduct(int id)
    {
        productRepo.deleteById(id);
    }

    public List<Product> searchProduct(String keyword){
        return productRepo.searchProduct(keyword);
    }
}

