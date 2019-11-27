package com.hias.apps.controller;

import com.hias.apps.domain.DBFile;
import com.hias.apps.service.DBFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.json.simple.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;
import com.hias.apps.util.ErrorResponse;

@CrossOrigin
@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private DBFileStorageService DBFileStorageService;


    @PostMapping("/uploadFile/{id}")
    public ResponseEntity<?>  uploadFile(@PathVariable(value = "id") Long productId,@RequestParam("file") MultipartFile file) {
        DBFile dbFile = DBFileStorageService.storeFile(file,productId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dbFile.getId()).toUri();

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        if(dbFile != null) {
            result.put("id", dbFile.getId());
            result.put("fileName", dbFile.getFileName());
            result.put("filetype", dbFile.getFileType());
            result.put("size",file.getSize());
            result.put("contentType", file.getContentType());
            result.put("imageUrl", fileDownloadUri);
            resp.put("data", result);
            resp.put("success", true);
            resp.put("error", null);
        }else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Profile Doesnt Exist", 500));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
    }

//    @GetMapping("/file/{id}")
//    public ResponseEntity<?>  getfileProductId(@PathVariable(value = "id") Long productId) {
////        DBFile dbFile = DBFileStorageService.storeFile(file,productId);
////
////        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
////                .buildAndExpand(dbFile.getId()).toUri();
////
////        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
////                .path("/downloadFile/")
////                .path(dbFile.getId())
////                .toUriString();
//        JSONObject resp = new JSONObject();
//        JSONObject result = new JSONObject();
//        List<DBFile> listHotProduct = DBFileStorageService.getMediaByProductId(productId);
//        for(DBFile objects : listHotProduct) {
//            result.put("id", objects.getId());
//            result.put("fileName", objects.getFileName());
//            result.put("filetype", objects.getFileType());
////            result.put("imageUrl", fileDownloadUri);
//            resp.put("data", result);
//            resp.put("success", true);
//            resp.put("error", null);
//        }
//
//        if(result != null){
//            resp.put("data", result);
//            resp.put("success", true);
//            resp.put("error", null);
//        }
//        else {
//            resp.put("data", null);
//            resp.put("success", false);
//            resp.put("error",  new ErrorResponse("Product Doesnt Exist", 500));
//        }
//        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
//    }

    @GetMapping("/imageProduct/{id}")
    public ResponseEntity<?> listThirdSubCategoryByMainCategoryId(@PathVariable (value = "id") Long id) {



        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<DBFile> listHotProduct = DBFileStorageService.getMediaByProductId(id);
        for(DBFile objects : listHotProduct) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(objects.getId()).toUri();
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(objects.getId())
                    .toUriString();
            JSONObject mainCategoryList = new JSONObject();
            JSONObject subCategoryList = new JSONObject();
            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("fileName", objects.getFileName());
            json.put("imageUrl", fileDownloadUri);
            json.put("data", objects.getFileType());
            json.put("fileType", objects.getFileType());

//            json.put("product", mainCategoryList);

            listSub.add(json);

        }
        if(listSub != null){
            resp.put("data", listSub);
            resp.put("success", true);
            resp.put("error", null);
        }
        else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Product Doesnt Exist", 500));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
    }
//
//    @PostMapping("/uploadMultipleFiles/{id}")
//    public List<ResponseEntity<?>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@PathVariable(value = "id") Long productId) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        DBFile dbFile = DBFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}