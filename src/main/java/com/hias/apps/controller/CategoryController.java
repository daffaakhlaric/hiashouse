package com.hias.apps.controller;

import com.hias.apps.domain.*;
import com.hias.apps.repository.SupMiniSubCategory;
import com.hias.apps.service.*;
import com.hias.apps.dto.PageDto;
import com.hias.apps.repository.DiscussionRepository;
import com.hias.apps.dto.ProductDiscussionDto;

import java.util.*;

import com.hias.apps.util.ErrorResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/")
@Api(value = "/product/", description = "Authentication", produces = "application/json")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("mainCategory")
    public ResponseEntity<?> hotItems() {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<MainCategory> listHotProduct = categoryService.getListMainCategoryAll();
        for(MainCategory objects : listHotProduct) {
            JSONObject json = new JSONObject();
            List<SubCategory> listHoProduct = categoryService.getListSubCategoryAll();
            json.put("id", objects.getId());
            json.put("mainCategoryName", objects.getCategoryname());
            json.put("description", objects.getDescription());
            json.put("imageUrl",objects.getImageUrl());
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

    @GetMapping("mainCategory/{id}")
    public ResponseEntity<?> getProductById(@PathVariable (value = "id") Long id) {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        Optional<MainCategory> listHotProduct = categoryService.getListMainCategoryId(id);
//        Product existingProduct = listHotProduct.get();
        if(listHotProduct != null){
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
            JSONObject json = new JSONObject();
            MainCategory existingProduct = listHotProduct.get();
            json.put("id", existingProduct.getId());
            json.put("mainCategoryName", existingProduct.getCategoryname());
            json.put("description", existingProduct.getDescription());
            json.put("imageUrl",existingProduct.getImageUrl());

            listSub.add(json);

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

    @GetMapping("subCategory")
    public ResponseEntity<?> listSubCategoryAll() {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<SubCategory> listHotProduct = categoryService.getListSubCategoryAll();
        for(SubCategory objects : listHotProduct) {
            JSONObject mainCategoryList = new JSONObject();
            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("subCategoryName", objects.getCategoryname());


            json.put("MainCategory", mainCategoryList);
            mainCategoryList.put("id", objects.getMainCategoryProperties().getId());
            mainCategoryList.put("mainCategoryName", objects.getMainCategoryProperties().getCategoryname());
            mainCategoryList.put("description", objects.getMainCategoryProperties().getDescription());
            mainCategoryList.put("imageUrl",objects.getMainCategoryProperties().getImageUrl());

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

    @GetMapping("subCategory/{id}")
    public ResponseEntity<?> getSubCategoryId(@PathVariable (value = "id") Long id) {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        Optional<SubCategory> listHotProduct = categoryService.getListSubCategoryId(id);
//        Product existingProduct = listHotProduct.get();
        if(listHotProduct != null){
            JSONObject mainCategoryList = new JSONObject();
            JSONObject json = new JSONObject();
            SubCategory existingProduct = listHotProduct.get();
            json.put("id", existingProduct.getId());
            json.put("mainCategoryName", existingProduct.getCategoryname());

            json.put("mainCategory", mainCategoryList);
            mainCategoryList.put("id", existingProduct.getMainCategoryProperties().getId());
            mainCategoryList.put("mainCategoryName", existingProduct.getMainCategoryProperties().getCategoryname());
            mainCategoryList.put("description", existingProduct.getMainCategoryProperties().getDescription());
            mainCategoryList.put("imageUrl",existingProduct.getMainCategoryProperties().getImageUrl());

            listSub.add(json);

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

    @GetMapping("subCategoryByMainCategoryId/{id}")
    public ResponseEntity<?> listSubCategoryByMainCategoryId(@PathVariable (value = "id") Long id) {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<SubCategory> listHotProduct = categoryService.getListSubCategoryByMainCategoryId(id);
        for(SubCategory objects : listHotProduct) {
            JSONObject mainCategoryList = new JSONObject();
            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("subCategoryName", objects.getCategoryname());


            json.put("mainCategory", mainCategoryList);
            mainCategoryList.put("id", objects.getMainCategoryProperties().getId());
            mainCategoryList.put("mainCategoryName", objects.getMainCategoryProperties().getCategoryname());
            mainCategoryList.put("description", objects.getMainCategoryProperties().getDescription());
            mainCategoryList.put("imageUrl",objects.getMainCategoryProperties().getImageUrl());

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


    @GetMapping("secondSubCategory")
    public ResponseEntity<?> listSecondSubCategory() {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<MiniSubCategory> listHotProduct = categoryService.getLisMiniSubCategoryAll();
        for(MiniSubCategory objects : listHotProduct) {
            JSONObject mainCategoryList = new JSONObject();
            JSONObject subCategoryList = new JSONObject();
            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("secondSubCategoryName", objects.getCategoryname());


            json.put("mainCategory", mainCategoryList);
            mainCategoryList.put("id", objects.getSubCategoryProperties().getMainCategoryProperties().getId());
            mainCategoryList.put("mainCategoryName", objects.getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
            mainCategoryList.put("description", objects.getSubCategoryProperties().getMainCategoryProperties().getDescription());
            mainCategoryList.put("imageUrl",objects.getSubCategoryProperties().getMainCategoryProperties().getImageUrl());

            json.put("subCategory", subCategoryList);
            subCategoryList.put("id", objects.getSubCategoryProperties().getId());
            subCategoryList.put("mainCategoryName", objects.getSubCategoryProperties().getCategoryname());

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

    @GetMapping("secondSubCategory/{id}")
    public ResponseEntity<?> getSecondSubCategoryId(@PathVariable (value = "id") Long id) {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        Optional<MiniSubCategory> listHotProduct = categoryService.getListMiniSubCategoryId(id);
//        Product existingProduct = listHotProduct.get();
        if(listHotProduct != null){
            JSONObject mainCategoryList = new JSONObject();
            JSONObject subCategoryList = new JSONObject();
            JSONObject json = new JSONObject();
            MiniSubCategory existingProduct = listHotProduct.get();
            json.put("id", existingProduct.getId());
            json.put("secondSubCategoryName", existingProduct.getCategoryname());

            json.put("mainCategory", mainCategoryList);
            mainCategoryList.put("id", existingProduct.getSubCategoryProperties().getMainCategoryProperties().getId());
            mainCategoryList.put("mainCategoryName", existingProduct.getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
            mainCategoryList.put("description", existingProduct.getSubCategoryProperties().getMainCategoryProperties().getDescription());
            mainCategoryList.put("imageUrl",existingProduct.getSubCategoryProperties().getMainCategoryProperties().getImageUrl());

            json.put("subCategory", subCategoryList);
            subCategoryList.put("id", existingProduct.getSubCategoryProperties().getId());
            subCategoryList.put("mainCategoryName", existingProduct.getSubCategoryProperties().getCategoryname());

            listSub.add(json);

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

    @GetMapping("secondSubCategoryBySubCategoryId/{id}")
    public ResponseEntity<?> listSecondSubCategoryByMainCategoryId(@PathVariable (value = "id") Long id) {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<MiniSubCategory> listHotProduct = categoryService.getListMiniSubCategorBySubCategory(id);
        for(MiniSubCategory objects : listHotProduct) {
            JSONObject mainCategoryList = new JSONObject();
            JSONObject subCategoryList = new JSONObject();
            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("secondSubCategoryName", objects.getCategoryname());


            json.put("mainCategory", mainCategoryList);
            mainCategoryList.put("id", objects.getSubCategoryProperties().getMainCategoryProperties().getId());
            mainCategoryList.put("mainCategoryName", objects.getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
            mainCategoryList.put("description", objects.getSubCategoryProperties().getMainCategoryProperties().getDescription());
            mainCategoryList.put("imageUrl",objects.getSubCategoryProperties().getMainCategoryProperties().getImageUrl());

            json.put("subCategory", subCategoryList);
            subCategoryList.put("id", objects.getSubCategoryProperties().getId());
            subCategoryList.put("mainCategoryName", objects.getSubCategoryProperties().getCategoryname());

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


    @GetMapping("thirdSubCategory")
    public ResponseEntity<?> listThirdSubCategory() {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<SupMiniCategory> listHotProduct = categoryService.getLisSupMiniSubCategoryAll();
        for(SupMiniCategory objects : listHotProduct) {
            JSONObject mainCategoryList = new JSONObject();
            JSONObject subCategoryList = new JSONObject();
            JSONObject miniSubCategoryList = new JSONObject();
            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("thirdSubCategoryName", objects.getCategoryname());


            json.put("mainCategory", mainCategoryList);
            mainCategoryList.put("id", objects.getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getId());
            mainCategoryList.put("mainCategoryName", objects.getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
            mainCategoryList.put("description", objects.getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getDescription());
            mainCategoryList.put("imageUrl",objects.getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getImageUrl());

            json.put("subCategory", subCategoryList);
            subCategoryList.put("id", objects.getMiniSubCategoryProperties().getSubCategoryProperties().getId());
            subCategoryList.put("mainCategoryName", objects.getMiniSubCategoryProperties().getSubCategoryProperties().getCategoryname());

            json.put("secondSubCategory", miniSubCategoryList);
            miniSubCategoryList.put("id", objects.getMiniSubCategoryProperties().getId());
            miniSubCategoryList.put("mainCategoryName", objects.getMiniSubCategoryProperties().getCategoryname());

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

    @GetMapping("thirdSubCategory/{id}")
    public ResponseEntity<?> getthirdSubCategoryId(@PathVariable (value = "id") Long id) {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        Optional<SupMiniCategory> listHotProduct = categoryService.getListSupMiniSubCategoryId(id);
//        Product existingProduct = listHotProduct.get();
        if(listHotProduct != null){
            JSONObject mainCategoryList = new JSONObject();
            JSONObject subCategoryList = new JSONObject();
            JSONObject miniSubCategoryList = new JSONObject();
            JSONObject json = new JSONObject();
            SupMiniCategory existingProduct = listHotProduct.get();
            json.put("id", existingProduct.getId());
            json.put("thirdSubCategoryName", existingProduct.getCategoryname());

            json.put("mainCategory", mainCategoryList);
            mainCategoryList.put("id", existingProduct.getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getId());
            mainCategoryList.put("mainCategoryName", existingProduct.getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
            mainCategoryList.put("description", existingProduct.getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getDescription());
            mainCategoryList.put("imageUrl",existingProduct.getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getImageUrl());

            json.put("subCategory", subCategoryList);
            subCategoryList.put("id", existingProduct.getMiniSubCategoryProperties().getSubCategoryProperties().getId());
            subCategoryList.put("mainCategoryName", existingProduct.getMiniSubCategoryProperties().getSubCategoryProperties().getCategoryname());

            json.put("secondSubCategory", miniSubCategoryList);
            miniSubCategoryList.put("id", existingProduct.getMiniSubCategoryProperties().getId());
            miniSubCategoryList.put("mainCategoryName", existingProduct.getMiniSubCategoryProperties().getCategoryname());

            listSub.add(json);

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

    @GetMapping("thirdSubCategoryBySecondMainCategoryId/{id}")
    public ResponseEntity<?> listThirdSubCategoryByMainCategoryId(@PathVariable (value = "id") Long id) {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<SupMiniCategory> listHotProduct = categoryService.getListSupMiniSubCategoryBySubCategory(id);
        for(SupMiniCategory objects : listHotProduct) {
            JSONObject mainCategoryList = new JSONObject();
            JSONObject subCategoryList = new JSONObject();
            JSONObject miniSubCategoryList = new JSONObject();
            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("thirdSubCategoryName", objects.getCategoryname());


            json.put("mainCategory", mainCategoryList);
            mainCategoryList.put("id", objects.getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getId());
            mainCategoryList.put("mainCategoryName", objects.getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
            mainCategoryList.put("description", objects.getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getDescription());
            mainCategoryList.put("imageUrl",objects.getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getImageUrl());

            json.put("subCategory", subCategoryList);
            subCategoryList.put("id", objects.getMiniSubCategoryProperties().getSubCategoryProperties().getId());
            subCategoryList.put("mainCategoryName", objects.getMiniSubCategoryProperties().getSubCategoryProperties().getCategoryname());

            json.put("secondSubCategory", miniSubCategoryList);
            miniSubCategoryList.put("id", objects.getMiniSubCategoryProperties().getId());
            miniSubCategoryList.put("mainCategoryName", objects.getMiniSubCategoryProperties().getCategoryname());


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

}
