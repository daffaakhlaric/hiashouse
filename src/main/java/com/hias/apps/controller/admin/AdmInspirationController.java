package com.hias.apps.controller.admin;

import com.hias.apps.domain.Inspiration;
import com.hias.apps.domain.InspirationProduct;
import com.hias.apps.dto.BannerDescDto;
import com.hias.apps.dto.InspirationDto;
import com.hias.apps.dto.InspirationRelatedDto;
import com.hias.apps.dto.ProductDto;
import com.hias.apps.service.InspirationService;
import com.hias.apps.util.ErrorResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class AdmInspirationController {


    @Autowired
    private InspirationService inspirationService;


    @PostMapping("addInspiration")
    public  ResponseEntity<?> insertInspiration(@RequestBody InspirationDto inspirationDto, String title, String description, String image){
        JSONObject resp = new JSONObject();
    JSONObject result = new JSONObject();
    JSONObject listArtis = new JSONObject();



        inspirationService.insertInspiration(inspirationDto.getTitle(title),inspirationDto.getDescription(description),inspirationDto.getImageBanner(image));
            if(inspirationService != null) {
        resp.put("success", true);
        resp.put("error", null);
    } else {
        resp.put("data", null);
        resp.put("success", false);
        resp.put("error",  new ErrorResponse("Cart Doesnt Exist", 204));
    }
        return new ResponseEntity<>(resp, HttpStatus.OK);

}

    @PostMapping("addInspirationRelated")
    public  ResponseEntity<?> insertInspirationRelated(@RequestBody InspirationRelatedDto inspirationRelatedDto, Long inspirationId, Long productId){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();



        inspirationService.insertInspirationRelated(inspirationRelatedDto.getInspirationId(inspirationId),inspirationRelatedDto.getProductId(productId));
        if(inspirationService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Cart Doesnt Exist", 204));
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }

    @DeleteMapping("{id}/deleteInspiration")
    public  ResponseEntity<?> deleteBanner(@PathVariable(value = "id") Long id){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();


        inspirationService.deleteInspiration(id);
        if(inspirationService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Banner Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }

    @PostMapping("{id}/updateInspiration")
    public  ResponseEntity<?> updateBanner(@RequestBody InspirationDto inspirationDto, @PathVariable(value = "id") Long id, String title, String description, String image){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();


        inspirationService.updateInspiration(inspirationDto.getTitle(title),inspirationDto.getDescription(description),inspirationDto.getImageBanner(image),id);
        if(inspirationService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Banner Item Qty Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }


}
