package com.hias.apps.service;

import com.hias.apps.domain.Product;
import com.hias.apps.exception.FileStorageException;
import com.hias.apps.exception.MyFileNotFoundException;
import com.hias.apps.domain.DBFile;
import com.hias.apps.repository.DBFileRepository;
import com.hias.apps.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.net.URI;
import java.util.Optional;
import java.util.List;
@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    @Autowired
    ProductRepository productRepository;

    public DBFile storeFile(MultipartFile file, Long productId) {
        // Normalize file name
        Optional<Product> product = productRepository.findById(productId);

//        if (!user.isPresent()) {
//            String msg = String.format("Upload not found with with id %s!", userId);
//            return new ResponseEntity(new ResponseError("ERR_NOT_FOUND", msg),
//                    HttpStatus.NOT_FOUND);
//        }


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
            dbFile.setProduct(product.get());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dbFile.getId()).toUri();

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

    public List<DBFile> getMediaByProductId(Long id){
        List<DBFile> result = dbFileRepository.findByProductId(id);

        return result;
    }
}