package com.hias.apps.controller;

import com.hias.apps.domain.Banner;
import com.hias.apps.domain.BannerImageWeb;
import com.hias.apps.domain.BannerWeb;
import com.hias.apps.service.BannerImageWebService;
import com.hias.apps.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import java.util.List;

import com.hias.apps.util.ErrorResponse;

@CrossOrigin
@RestController
public class BannerController {


    @Autowired
    private BannerImageWebService bannerImageWebService;


    @Autowired
    private BannerService bannerService;

//
//    @GetMapping("/bannerWeb/{id}")
//    public ResponseEntity<?> getBanner(@PathVariable(value = "id") Long Id) {
//
//        JSONObject listSub = new JSONObject();
//        JSONObject resp = new JSONObject();
//
//        List<JSONObject> listItem = new ArrayList<>();
//
//        List<BannerImageWeb> listHotProduct = bannerImageWebService.getByBannerId(Id);
//        JSONObject listUser = new JSONObject();
//        for(BannerImageWeb objects : listHotProduct) {
//            JSONObject json = new JSONObject();
//
//            listSub.put("imageBanner",listItem);
//
//            listSub.put("title",objects.getBannerWeb().getTitle());
//            listSub.put("subtitle",objects.getBannerWeb().getSubTitle());
//            listSub.put("description",objects.getBannerWeb().getDescription());
//            listSub.put("discount",objects.getBannerWeb().getDiscount());
//
//
//            json.put("id",objects.getId());
//            json.put("imageUrl",objects.getImageBannerUrl());
//
//            listItem.add(json);
//
//        }
//        if(listSub != null){
//            resp.put("data", listSub);
//            resp.put("success", true);
//            resp.put("error", null);
//        }
//        else {
//            resp.put("data", null);
//            resp.put("success", false);
//            resp.put("error",  new ErrorResponse("Product Doesnt Exist", 500));
//        }
//        return new ResponseEntity<>(resp, HttpStatus.OK);
//    }
//
//    @GetMapping("/getAllBanner")
//    public ResponseEntity<?> getAllBannerWeb() {
//
//        JSONObject listSub = new JSONObject();
//        JSONObject resp = new JSONObject();
//
//        List<JSONObject> listItem = new ArrayList<>();
//
//        List<Banner> listHotProduct = bannerService.getAllBanner();
//        JSONObject listUser = new JSONObject();
//        for(Banner objects : listHotProduct) {
//            JSONObject json = new JSONObject();
//
//            listSub.put("imageBanner",listItem);
//
//            json.put("title",objects.getTitle());
//            json.put("id",objects.getId());
//            json.put("subtitle",objects.getSubTitle());
//            json.put("description",objects.getDescription());
//            json.put("discount",objects.getDiscount());
//
//
////            json.put("id",objects.getId());
////            json.put("imageUrl",objects.getImageBannerUrl());
//
//            listItem.add(json);
//
//        }
//        if(listSub != null){
//            resp.put("data", listSub);
//            resp.put("success", true);
//            resp.put("error", null);
//        }
//        else {
//            resp.put("data", null);
//            resp.put("success", false);
//            resp.put("error",  new ErrorResponse("Product Doesnt Exist", 500));
//        }
//        return new ResponseEntity<>(resp, HttpStatus.OK);
//    }


    @GetMapping("/getAllBanner")
    public ResponseEntity<?> getAllBanner() {

        JSONObject listSub = new JSONObject();
        JSONObject resp = new JSONObject();

        List<JSONObject> listItem = new ArrayList<>();

        List<Banner> listHotProduct = bannerService.getAllBanner();
        JSONObject listUser = new JSONObject();
        for(Banner objects : listHotProduct) {
            JSONObject json = new JSONObject();

            listSub.put("banner",listItem);


            json.put("id",objects.getId());
            json.put("imageUrl",objects.getImageUrl());
            json.put("link",objects.getLink());

            listItem.add(json);

        }
        if(listSub != null){
            resp.put("data", listSub);
            resp.put("success", true);
            resp.put("error", null);
        }
        else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Banner Doesnt Exist", 500));
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}