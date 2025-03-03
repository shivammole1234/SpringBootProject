package ecom.shivam.ecom_project.controller;

import ecom.shivam.ecom_project.model.Product;
import ecom.shivam.ecom_project.service.ProductService;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin   // to sove the CROSS error
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>>get_all_product(){

        return new ResponseEntity<>( productService.getAllProducts(), HttpStatus.OK);
    }


    @GetMapping("/product/{id}")
    public ResponseEntity< Product > getProduct(@PathVariable int id){

        Product product = productService.getProductById(id);

        if(product != null)
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct( @RequestPart Product product,
                                         @RequestPart MultipartFile  imageFile){
       try{
           Product product1=productService.addProduct(product,imageFile);
           return new ResponseEntity<>(product1,HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }

    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId
            (@PathVariable int productId){

        Product product=productService.getProductById(productId);
        byte[] imageFile=product.getImageData();

        return  ResponseEntity.ok().contentType(MediaType.
                valueOf(product.getImageType())).body(imageFile);

    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct
            (@PathVariable int id,
             @RequestPart Product product,
             @RequestPart MultipartFile imageFile, ServletRequest servletRequest) throws IOException {

        Product product1=productService.updateProduct(id,product,imageFile);
        if(product1 != null)
            return new ResponseEntity<>("updated",HttpStatus.OK);
        else
            return new ResponseEntity<>("failed to update",HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable  int id){
        Product product = productService.getProductById(id);
        if(product != null)
        {
            productService.deleteProduct(id);
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("failed to delete",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<List<Product>> searchProduct
            (@RequestParam String keyword){
        System.out.println("searching with the keyword :_ "+keyword);
      List<Product> products=productService.searchProduct(keyword);
      return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
