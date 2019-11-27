package com.hias.apps.controller;

import com.hias.apps.domain.*;
import com.hias.apps.dto.*;
import com.hias.apps.repository.*;
import com.hias.apps.service.*;
import com.hias.apps.service.AmazonClient;

import java.io.IOException;
import java.net.URI;
import java.util.*;

import com.hias.apps.util.ErrorResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/product/")
@Api(value = "/product/", description = "Authentication", produces = "application/json")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ArticelService articelService;

    @Autowired
    private VariantService variantService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private  ShoppingCartService shoppingCartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RefundService refundService;

    @Autowired
    private AddToCartService addToCartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private CartService cartService;

    @Autowired
    private NewsLatterService newsLatterService;

    @Autowired
    private ProductDiscussionService productDiscussionService;

    @Autowired
    private RelatedProductService relatedProductService;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    private ProductRatingService productRatingService;

    @Autowired
    private ProductWishlistService productWishlistService;
    private Object Product;

    private float grandTotal;

    private String jsonArrayString;
//    private float subTotal;

    private AmazonClient amazonClient;
    private float Total;

    private float TotalAfterDiscount ;

    @GetMapping("hotItems")
    public ResponseEntity<?> hotItems() {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        float totalPrice;
        JSONObject result = new JSONObject();
        List<Product> listHotProduct = productService.getListHoItemsProduct();
        for(Product objects : listHotProduct) {
            JSONObject json = new JSONObject();
            TotalAfterDiscount = objects.getPrice()*objects.getDiscount()/100;
            totalPrice = objects.getPrice()-TotalAfterDiscount;



            JSONObject mainCategory = new JSONObject();

            json.put("productId", objects.getId());
            json.put("productName", objects.getName());
            json.put("discount", objects.getDiscount());
            json.put("price", objects.getPrice());
            json.put("priceAfterDiscount", TotalAfterDiscount);
            json.put("thumbnail",objects.getThumNailUrl());

            json.put("category",mainCategory);
            mainCategory.put("id",objects.getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getMiniSubCategoryProperties().getCategoryname());


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

    @GetMapping("banner")
    public ResponseEntity<?> banner() {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Product> listHotProduct = productService.getListProductBanner();
        for(Product objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
//            JSONObject json = new JSONObject();
            json.put("productId", objects.getId());
            json.put("productName", objects.getName());
            json.put("price", objects.getPrice());
            json.put("thumbnail",objects.getThumNailUrl());

            json.put("category",mainCategory);
            mainCategory.put("id",objects.getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getMiniSubCategoryProperties().getCategoryname());


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

    @GetMapping("bestSeller")
    public ResponseEntity<?> bestSeller() {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Product> listHotProduct = productService.getListBestSeller();
        for(Product objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
//            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("productName", objects.getName());
            json.put("price", objects.getPrice());
            json.put("thumbnail",objects.getThumNailUrl());
            json.put("categoryName",objects.getMiniSubCategoryProperties().getCategoryname());

            json.put("category",mainCategory);
            mainCategory.put("id",objects.getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getMiniSubCategoryProperties().getCategoryname());
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

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable (value = "id") Long Id) {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        List<JSONObject> pictures = new ArrayList<>();
        List<JSONObject> couriers = new ArrayList<>();
        List<JSONObject> variants = new ArrayList<>();
        JSONObject result = new JSONObject();
        JSONObject picture = new JSONObject();
        JSONObject courier = new JSONObject();

        Optional<Product> listHotProduct = productService.getProductById(Id);
//        Product existingProduct = listHotProduct.get();
        if(listHotProduct != null){
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
            JSONObject category = new JSONObject();
            JSONObject json = new JSONObject();
            JSONObject variant = new JSONObject();
            JSONObject variant1 = new JSONObject();



            Product existingProduct = listHotProduct.get();
            json.put("productId", existingProduct.getId());
            json.put("productName", existingProduct.getName());
            json.put("productCode", existingProduct.getProductCode());
            json.put("color", existingProduct.getColor());
            json.put("description", existingProduct.getDescription());
//            json.put("courier", existingProduct.getCourier());
            json.put("overview", existingProduct.getOverview());
            json.put("rating", "20");
            json.put("price", existingProduct.getPrice());
            json.put("weight", existingProduct.getWeight());
            json.put("hashTag", existingProduct.getHash_tag());
            json.put("dimensions", existingProduct.getDimensions());
            json.put("thumbnail",existingProduct.getThumNailUrl());

            json.put("picture",pictures);
            picture.put("picture1",existingProduct.getPicture_1());
            picture.put("picture2",existingProduct.getPicture_2());
            picture.put("picture3",existingProduct.getPicture_3());
            picture.put("picture4",existingProduct.getPicture_4());
            picture.put("picture5",existingProduct.getPicture_5());
            picture.put("picture6",existingProduct.getPicture_6());

            json.put("courier",couriers);
            courier.put("courier1",existingProduct.getCourier_1());
            courier.put("courier2",existingProduct.getCourier_2());
            courier.put("courier3",existingProduct.getCourier_3());
            courier.put("courier4",existingProduct.getCourier_4());

            json.put("variant",variants);
//            variant.put("productId",existingProduct.getProductVariant1().getId());
//            variant.put("productName", existingProduct.getProductVariant1().getName());
//            variant.put("price", existingProduct.getProductVariant1().getPrice());
//            variant.put("thumbnail",existingProduct.getProductVariant1().getThumNailUrl());

            variant.put("category",category);
//            category.put("id",existingProduct.getProductVariant2().getMiniSubCategoryProperties().getId());
//            category.put("name",existingProduct.getProductVariant2().getMiniSubCategoryProperties().getCategoryname());
//
//            variant1.put("productId",existingProduct.getProductVariant2().getId());
//            variant1.put("productName", existingProduct.getProductVariant2().getName());
//            variant1.put("price", existingProduct.getProductVariant2().getPrice());
//            variant1.put("thumbnail",existingProduct.getProductVariant2().getThumNailUrl());

            variant1.put("category",category);
//            category.put("id",existingProduct.getProductVariant2().getMiniSubCategoryProperties().getId());
//            category.put("name",existingProduct.getProductVariant2().getMiniSubCategoryProperties().getCategoryname());

//
//            json.put("mainCategory",mainCategory);
//            mainCategory.put("id",existingProduct.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getId());
//            mainCategory.put("name",existingProduct.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
//            mainCategory.put("description",existingProduct.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getDescription());
//
//            mainCategory.put("subCategory", listSubCategory);
//            listSubCategory.put("id",existingProduct.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getId());
//            listSubCategory.put("name",existingProduct.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getCategoryname());
//
//            mainCategory.put("secondSubCategory", listMiniSubCategory);
//            listMiniSubCategory.put("id",existingProduct.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getId());
//            listMiniSubCategory.put("name",existingProduct.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getCategoryname());

            json.put("category",mainCategory);
            mainCategory.put("id",existingProduct.getMiniSubCategoryProperties().getId());
            mainCategory.put("name",existingProduct.getMiniSubCategoryProperties().getCategoryname());

            listSub.add(json);
            pictures.add(picture);
            couriers.add(courier);
            variants.add(variant);
            variants.add(variant1);

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

    @GetMapping("{id}/aToz")
    public ResponseEntity<?> filterAtoZ(@PathVariable (value = "id") Long Id,@RequestBody PageDto dto) {

        JSONObject response = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Product> listHotProduct = productService.getLisAtoZProduct(Id);
        for(Product objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
//            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("productName", objects.getName());
            json.put("price", objects.getPrice());
            json.put("thumbnail",objects.getThumNailUrl());
            json.put("categoryName",objects.getMiniSubCategoryProperties().getCategoryname());

//            json.put("mainCategory",mainCategory);
//            mainCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getId());
//            mainCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
//            mainCategory.put("description",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getDescription());
//
//            mainCategory.put("subCategory", listSubCategory);
//            listSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getId());
//            listSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getCategoryname());
//
//            mainCategory.put("secondSubCategory", listMiniSubCategory);
//            listMiniSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getId());
//            listMiniSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getCategoryname());

            json.put("category",mainCategory);
            mainCategory.put("id",objects.getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getMiniSubCategoryProperties().getCategoryname());
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

    @PostMapping("searchName/{name}")
    public ResponseEntity<?> seacrchName(@PathVariable (value = "name") String name) {


        JSONObject response = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Product> listHotProduct = productService.getListSearchProduct(name);
        for(Product objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
//            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("productName", objects.getName());
            json.put("price", objects.getPrice());
            json.put("thumbnail",objects.getThumNailUrl());
            json.put("categoryName",objects.getMiniSubCategoryProperties().getCategoryname());

//            json.put("mainCategory",mainCategory);
//            mainCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getId());
//            mainCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
//            mainCategory.put("description",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getDescription());
//
//            mainCategory.put("subCategory", listSubCategory);
//            listSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getId());
//            listSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getCategoryname());
//
//            mainCategory.put("secondSubCategory", listMiniSubCategory);
//            listMiniSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getId());
//            listMiniSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getCategoryname());


            json.put("category",mainCategory);
            mainCategory.put("id",objects.getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getMiniSubCategoryProperties().getCategoryname());
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

    @GetMapping("categoryId/{id}")
    public ResponseEntity<?> productByCategoryId(@PathVariable (value = "id") Long id) {

        JSONObject response = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Product> listHotProduct = productService.getProductBySubCategoryId(id);
        for(Product objects : listHotProduct) {
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("productName", objects.getName());
            json.put("productCode", objects.getProductCode());
            json.put("price", objects.getPrice());
            json.put("thumbnail",objects.getThumNailUrl());
            json.put("categoryName",objects.getMiniSubCategoryProperties().getCategoryname());
//            json.put("mainCategory",mainCategory);
//            mainCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getId());
//            mainCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
//            mainCategory.put("description",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getDescription());
//
//            mainCategory.put("subCategory", listSubCategory);
//            listSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getId());
//            listSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getCategoryname());
//
//            mainCategory.put("secondSubCategory", listMiniSubCategory);
//            listMiniSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getId());
//            listMiniSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getCategoryname());


            json.put("category",mainCategory);
            mainCategory.put("id",objects.getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getMiniSubCategoryProperties().getCategoryname());
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

    @GetMapping("{id}/zToa")
    public ResponseEntity<?> filterZToA(@PathVariable (value = "id") Long Id) {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Product> listHotProduct = productService.getLisZtoAProduct(Id);
        for(Product objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
//            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("productName", objects.getName());
            json.put("price", objects.getPrice());
            json.put("thumbnail",objects.getThumNailUrl());
            json.put("categoryName",objects.getMiniSubCategoryProperties().getCategoryname());

//            json.put("mainCategory",mainCategory);
//            mainCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getId());
//            mainCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
//            mainCategory.put("description",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getDescription());
//
//            mainCategory.put("subCategory", listSubCategory);
//            listSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getId());
//            listSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getCategoryname());
//
//            mainCategory.put("secondSubCategory", listMiniSubCategory);
//            listMiniSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getId());
//            listMiniSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getCategoryname());


            json.put("category",mainCategory);
            mainCategory.put("id",objects.getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getMiniSubCategoryProperties().getCategoryname());
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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @GetMapping("{id}/last")
    public ResponseEntity<?> filterNew(@PathVariable (value = "id") Long Id) {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Product> listHotProduct = productService.getListNewestProduct(Id);
        for(Product objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
//            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("productName", objects.getName());
            json.put("price", objects.getPrice());
            json.put("thumbnail",objects.getThumNailUrl());
            json.put("categoryName",objects.getMiniSubCategoryProperties().getCategoryname());

//            json.put("mainCategory",mainCategory);
//            mainCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getId());
//            mainCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
//            mainCategory.put("description",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getDescription());
//
//            mainCategory.put("subCategory", listSubCategory);
//            listSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getId());
//            listSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getCategoryname());
//
//            mainCategory.put("secondSubCategory", listMiniSubCategory);
//            listMiniSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getId());
//            listMiniSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getCategoryname());


            json.put("category",mainCategory);
            mainCategory.put("id",objects.getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getMiniSubCategoryProperties().getCategoryname());
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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("{id}/newest")
    public ResponseEntity<?> filterLastlest(@PathVariable (value = "id") Long Id) {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Product> listHotProduct = productService.getListLatestProduct(Id);
        for(Product objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
//            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("productName", objects.getName());
            json.put("price", objects.getPrice());
            json.put("thumbnail",objects.getThumNailUrl());
            json.put("categoryName",objects.getMiniSubCategoryProperties().getCategoryname());

//            json.put("mainCategory",mainCategory);
//            mainCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getId());
//            mainCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
//            mainCategory.put("description",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getDescription());
//
//            mainCategory.put("subCategory", listSubCategory);
//            listSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getId());
//            listSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getCategoryname());
//
//            mainCategory.put("secondSubCategory", listMiniSubCategory);
//            listMiniSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getId());
//            listMiniSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getCategoryname());

            json.put("category",mainCategory);
            mainCategory.put("id",objects.getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getMiniSubCategoryProperties().getCategoryname());
            listSub.add(json);

        }
        Set<Object> setResponse = new HashSet<>();
        Map<String, Object> mapResult = new HashMap<>();
        Map<String, Object> mapPage = new HashMap<>();
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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("{id}/priceLow")
    public ResponseEntity<?> filterPrice(@PathVariable (value = "id") Long Id) {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Product> listHotProduct = productService.getListLastPriceProduct(Id);
        for(Product objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
//            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("productName", objects.getName());
            json.put("price", objects.getPrice());
            json.put("thumbnail",objects.getThumNailUrl());
            json.put("categoryName",objects.getMiniSubCategoryProperties().getCategoryname());

//            json.put("mainCategory",mainCategory);
//            mainCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getId());
//            mainCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
//            mainCategory.put("description",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getDescription());
//
//            mainCategory.put("subCategory", listSubCategory);
//            listSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getId());
//            listSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getCategoryname());
//
//            mainCategory.put("secondSubCategory", listMiniSubCategory);
//            listMiniSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getId());
//            listMiniSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getCategoryname());


            json.put("category",mainCategory);
            mainCategory.put("id",objects.getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getMiniSubCategoryProperties().getCategoryname());
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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("{id}/priceHigh")
    public ResponseEntity<?> filterPriceLow(@PathVariable (value = "id") Long Id) {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Product> listHotProduct = productService.getListNewestPriceProduct(Id);
        for(Product objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
//            JSONObject json = new JSONObject();
            json.put("id", objects.getId());
            json.put("productName", objects.getName());
            json.put("price", objects.getPrice());
            json.put("thumbnail",objects.getThumNailUrl());
            json.put("categoryName",objects.getMiniSubCategoryProperties().getCategoryname());

//            json.put("mainCategory",mainCategory);
//            mainCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getId());
//            mainCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getCategoryname());
//            mainCategory.put("description",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getMainCategoryProperties().getDescription());
//
//            mainCategory.put("subCategory", listSubCategory);
//            listSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getId());
//            listSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getSubCategoryProperties().getCategoryname());
//
//            mainCategory.put("secondSubCategory", listMiniSubCategory);
//            listMiniSubCategory.put("id",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getId());
//            listMiniSubCategory.put("name",objects.getMiniSubCategoryProperties().getMiniSubCategoryProperties().getCategoryname());


            json.put("category",mainCategory);
            mainCategory.put("id",objects.getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getMiniSubCategoryProperties().getCategoryname());
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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }



    @PostMapping("discussion")
    public  ResponseEntity<?> changeIsLive(@RequestBody ProductDiscussionDto productDiscussionDto, Long productId, Long userId , String discussion){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();


        productDiscussionService.insertProductDiscussion(productDiscussionDto.getUserId(userId),productDiscussionDto.getProductId(productId),productDiscussionDto.getDiscussion(discussion));
        if(productDiscussionService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Live Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }

    @GetMapping("discussion/{id}")
    public ResponseEntity<?> getDiscussinByProductId(@PathVariable(value = "id") Long Id) {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<ProductDiscussion> listHotProduct = productDiscussionService.getListDiscussionByProductId(Id);
        for(ProductDiscussion objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listUser = new JSONObject();
            json.put("id", objects.getId());
            json.put("discussion", objects.getDiscussion());
//            json.put("date", objects.getCreatedAt());
            json.put("user", listUser);

            listUser.put("id", objects.getUser().getId());
            listUser.put("fullName",objects.getUser().getFullname());
            listUser.put("imageProfil",objects.getUser().getImageProfile());
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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("rating")
    public  ResponseEntity<?> insertRating(@RequestBody ProductRatingDto productRatingDto, Long productId, Long userId, Long rating){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();


        productRatingService.insertProductRating(productRatingDto.getUserId(userId),productRatingDto.getProductId(productId),productRatingDto.getRating(rating));
        if(productRatingService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Wishlist Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }

    @DeleteMapping("rating/{id}")
    public  ResponseEntity<?> deleteRating(@PathVariable(value = "id") Long id){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();


        productRatingService.deleteProductRating(id);
        if(productRatingService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Wishlist Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }

    @GetMapping("rating/{id}")
    public ResponseEntity<?> getByRatingId(@PathVariable(value = "id") Long Id) {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<ProductRating> listHotProduct = productRatingService.getListRatingByProductId(Id);
        for(ProductRating objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listUser = new JSONObject();
            json.put("id", objects.getId());
            json.put("rating", objects.getRating());
//            json.put("date", objects.getCreatedAt());
            json.put("user", listUser);

            listUser.put("id", objects.getUser().getId());
            listUser.put("fullName",objects.getUser().getFullname());
            listUser.put("imageProfil",objects.getUser().getImageProfile());
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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("wishList")
    public  ResponseEntity<?> insertWishlist(@RequestBody WishlistDto wishlistDto, Long productId, Long userId){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();


        productWishlistService.insertProductWishlist(wishlistDto.getUserId(userId),wishlistDto.getProductId(productId));
        if(productWishlistService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Wishlist Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }


    @DeleteMapping("wishList/{id}")
    public  ResponseEntity<?> deleteWishlist(@PathVariable(value = "id") Long id){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();


        productWishlistService.deleteProductWishlist(id);
        if(productWishlistService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Wishlist Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }

    @GetMapping("wishList/{id}")
    public ResponseEntity<?> getByWishlistId(@PathVariable(value = "id") Long Id) {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Wishlist> listHotProduct = productWishlistService.getProductWishlistByUsertId(Id);
        for(Wishlist objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listUser = new JSONObject();
            json.put("id", objects.getId());
            json.put("productName", objects.getProduct().getName());
            json.put("price", objects.getProduct().getPrice());
            json.put("thumbnail",objects.getProduct().getThumNailUrl());
            json.put("categoryName",objects.getProduct().getMiniSubCategoryProperties().getCategoryname());
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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("detail/{id}/related")
    public ResponseEntity<?> getRelated(@PathVariable(value = "id") Long id) {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        JSONObject mainCategory = new JSONObject();
        List<RelatedProduct> listHotProduct = relatedProductService.getByProductId(id);
        for(RelatedProduct objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listUser = new JSONObject();
            json.put("id", objects.getProductRelated().getId());
            json.put("productName", objects.getProductRelated().getName());
            json.put("price", objects.getProductRelated().getPrice());
            json.put("thumbnail",objects.getProductRelated().getThumNailUrl());

            json.put("category",mainCategory);
            mainCategory.put("id",objects.getProductRelated().getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getProductRelated().getMiniSubCategoryProperties().getCategoryname());

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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("gettAllCartId")
    public ResponseEntity<?> getAllCart() {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Cart> listHotProduct = cartService.getAllcart();
        for(Cart objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listUser = new JSONObject();
            json.put("cartId", objects.getId());
//            json.put("userId", objects.getCartItem().getId());
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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("upload")
    public ResponseEntity<Object> uploadFile(@RequestPart(value = "file") MultipartFile file) {
//        Optional<User> user = userRepository.findById(userId);

//        if (!user.isPresent()) {
//            String msg = String.format("Upload not found with with id %s!", userId);
//            return new ResponseEntity(new ResponseError("ERR_NOT_FOUND", msg),
//                    HttpStatus.NOT_FOUND);
//        }
        JSONObject resp = new JSONObject();
        String path = this.amazonClient.uploadFile(file);

        Media media = new Media(file.getOriginalFilename(),"lorem", path, file.getSize(),file.getContentType());

//        media.setUser(user.get());
        Media savedMedia = mediaRepository.save(media);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedMedia.getId()).toUri();
        resp.put("success", true);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("addItemToCart")
    public  ResponseEntity<?> insertAddToCartIten(@RequestBody CartItemDto cartItemDto, Long cartId, Long productId, Long amount){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();



        cartItemService.insertAddTocartItem(cartItemDto.getCartId(cartId),cartItemDto.getProductId(productId),cartItemDto.getAmount(amount));
        if(cartItemService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Cart Doesnt Exist", 204));
        }
//        return this.cartItemService.insertAddTocartItem(cartItemService);
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }



    @PostMapping("addToCart")
    public  ResponseEntity<?> insertAddToCart(@RequestBody CartDto cartDto, Long userId){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();



        cartService.insertAddTocart(cartDto.getUserId(userId));
//        cartService.getByid(id);
        if(cartService != null) {

            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Cart Doesnt Exist", 204));
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }


    @PostMapping("newsLatter")
    public  ResponseEntity<?> insertNewsLatter(@RequestBody NewsLatterDto newsLatterDto, String email){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();



        newsLatterService.insertNewsLatter(newsLatterDto.getEmail(email));
//        cartService.getByid(id);
        if(newsLatterService != null) {

            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("newsLatterService Doesnt Exist", 204));
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }

    @PostMapping("addRefund")
    public  ResponseEntity<?> insertRefund(@RequestBody RefundDto refundDto, String fullName,String phone,String place,String noOrder,String email, String reason){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();



        refundService.insertRefund(refundDto.getFullName(fullName),refundDto.getPhone(phone),refundDto.getPlace(place),refundDto.getNoOrder(noOrder),refundDto.getEmail(email),refundDto.getReason(reason));
//        cartService.getByid(id);
        if(refundService != null) {

            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("newsLatterService Doesnt Exist", 204));
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }

//    @PostMapping("order")
//    public  ResponseEntity<?> inserOrder(@RequestBody OrderDto orderDto,Long userId,Long userAddressId,Long total,Long subTotal,Long status,String session,String paymentType){
//        JSONObject resp = new JSONObject();
//        JSONObject result = new JSONObject();
//        JSONObject listArtis = new JSONObject();
//
//
//
//        orderService.insertAddTocart(orderDto.getUserId(userId),orderDto.getUserAddressId(userAddressId),orderDto.getTotal(total),orderDto.getSubTotal(subTotal),orderDto.getStatus(status),orderDto.getSession(session),orderDto.getPaymentType(paymentType));
////        cartService.getByid(id);
//        if(orderService != null) {
//
//            resp.put("success", true);
//            resp.put("error", null);
//        } else {
//            resp.put("data", null);
//            resp.put("success", false);
//            resp.put("error",  new ErrorResponse("Cart Doesnt Exist", 204));
//        }
//        return new ResponseEntity<>(resp, HttpStatus.OK);
//
//    }

    @PostMapping("orderItems")
    public  ResponseEntity<?> orderItem(@RequestBody OrderItemDto orderItemDto, Long[] orderId, Long[] productId, Long[] qty){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();


        orderService.insertOrderItem(orderItemDto.getOrderId(orderId),orderItemDto.getProductId(productId),orderItemDto.getQty(qty));
        if(orderService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Cart Item Qty Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }

    @GetMapping("{id}/getCartByUserId")
    public  ResponseEntity<?>  getAddToCart(@PathVariable (value = "id") Long id) {
        List<Cart> cart = cartRepository.findByUserId(id);

        JSONObject listSub = new JSONObject();
        JSONObject resp = new JSONObject();

        List<JSONObject> listItem = new ArrayList<>();

        int subTotal = 0;
        List<CartItem> listHotProduct = cartItemService.getByCartId(id);
        for(CartItem objects : listHotProduct) {
            JSONObject json = new JSONObject();

            Total = objects.getCartProduct().getPrice()*objects.getAmount();
            subTotal += Total;

            listSub.put("listItems",listItem);

            listSub.put("cartId",objects.getCart().getId());
            listSub.put("subTotal",subTotal);

            json.put("itemsId",objects.getId());
            json.put("productId",objects.getCartProduct().getId());

            json.put("name",objects.getCartProduct().getName());
            json.put("thumbnail",objects.getCartProduct().getThumNailUrl());
            json.put("price",objects.getCartProduct().getPrice());
            json.put("totalItemPrice",objects.getCartProduct().getPrice()*objects.getAmount());
            json.put("qty",objects.getAmount());

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
            resp.put("error",  new ErrorResponse("Product Doesnt Exist", 500));
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @GetMapping("{id}/getOrderByUserId")
    public  ResponseEntity<?>  getOrder(@PathVariable (value = "id") Long id) {
        List<Cart> cart = cartRepository.findByUserId(id);

        JSONObject listSub = new JSONObject();
        JSONObject resp = new JSONObject();

        List<JSONObject> listItem = new ArrayList<>();

        int total = 0;
//        Cart cart = user.getCart();
//        Product product = productRepository.findOne(productId);
//        List<CartItem> cartItems = cart.getCartItems();

//        List<CartItem> cartItem = (List<CartItem>) new JSONObject();
        List<Order> listHotProduct = orderService.getByUserId(id);
        for(Order objects : listHotProduct) {
            JSONObject json = new JSONObject();
//            Total = objects.getCartProduct().getPrice()*objects.getAmount();
//            subTotal += total += Total ;


//            List<JSONObject> total = new ArrayList<>();

//           Total = objects.getProductCart(objects.getCartProduct().getPrice()*objects.getAmount());

            listSub.put("order",listItem);

//            listSub.put("totalPrice",Total);
            json.put("orderId",objects.getId());
            json.put("subTotal",objects.getSubTotal());
            json.put("total",objects.getTotal());
            json.put("date",objects.getCreatedAt());
            json.put("productTotal",objects.getProductTotal());
            json.put("session",objects.getSession());
            json.put("paymentType",objects.getPaymentType());
            json.put("status",objects.getStatus());
//            json.put("productTotal",objects.get);

//            json.put("userAddressId",objects.getUserAddress().getId());
//            json.put("firstName",objects.getUserAddress().getFirstName());

//            json.put("lastName",objects.getUserAddress().getLastName());
//            json.put("email",objects.getUserAddress().getEmail());
//            json.put("phone",objects.getUserAddress().getPhone());
//            json.put("address",objects.getUserAddress().getAddress());
//            json.put("company",objects.getUserAddress().getCompanyName());
//            json.put("city",objects.getUserAddress().getCity());
//            json.put("country",objects.getUserAddress().getCountry());
//            json.put("postCode",objects.getUserAddress().getPostCode());

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
            resp.put("error",  new ErrorResponse("Product Doesnt Exist", 500));
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("detail/{id}/order")
    public ResponseEntity<?> getOrderId(@PathVariable (value = "id") Long Id) throws IOException {

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        List<JSONObject> pictures = new ArrayList<>();
        List<JSONObject> couriers = new ArrayList<>();
        List<JSONObject> variants = new ArrayList<>();
        JSONObject result = new JSONObject();
        JSONObject useradress = new JSONObject();
        JSONObject picture = new JSONObject();
        JSONObject courier = new JSONObject();

        Optional<Order> listHotProduct = orderService.getByid(Id);

//        Product existingProduct = listHotProduct.get();
        if(listHotProduct != null){
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
            JSONObject category = new JSONObject();
            JSONObject json = new JSONObject();
            JSONObject variant = new JSONObject();
            JSONObject variant1 = new JSONObject();


            Order existingProduct = listHotProduct.get();

            jsonArrayString = existingProduct.getProductItem();
            ObjectMapper objectMapper = new ObjectMapper();
            json.put("total",existingProduct.getTotal());
            json.put("productTotal",existingProduct.getProductTotal());
            ProductItem[] books = objectMapper.readValue(jsonArrayString, ProductItem[].class);
            json.put("product",books);
            json.put("session",existingProduct.getSession());
            json.put("paymentType",existingProduct.getPaymentType());

            json.put("status",existingProduct.getStatus());

            json.put("userAddress",useradress);

            useradress.put("userAddressId",existingProduct.getUserAddress().getId());
            useradress.put("firstName",existingProduct.getUserAddress().getFirstName());

            useradress.put("lastName",existingProduct.getUserAddress().getLastName());
            useradress.put("createdAt",existingProduct.getCreatedAt());
            useradress.put("email",existingProduct.getUserAddress().getEmail());
            useradress.put("phone",existingProduct.getUserAddress().getPhone());
            useradress.put("address",existingProduct.getUserAddress().getAddress());
            useradress.put("company",existingProduct.getUserAddress().getCompanyName());
            useradress.put("city",existingProduct.getUserAddress().getCity());
            useradress.put("country",existingProduct.getUserAddress().getCountry());
            useradress.put("postCode",existingProduct.getUserAddress().getPostCode());

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

    @GetMapping("coupon/{coupon}")
    public ResponseEntity<?> getCoupon(@PathVariable (value = "coupon") String code){

        JSONObject resp = new JSONObject();
        List<JSONObject> listSub = new ArrayList<>();
        List<JSONObject> pictures = new ArrayList<>();
        List<JSONObject> couriers = new ArrayList<>();
        List<JSONObject> variants = new ArrayList<>();
        JSONObject result = new JSONObject();
        JSONObject useradress = new JSONObject();
        JSONObject picture = new JSONObject();
        JSONObject courier = new JSONObject();

        Optional<Discount> listHotProduct = discountService.getByUserId(code);

//        Product existingProduct = listHotProduct.get();
        if(listHotProduct != null){
            JSONObject listSubCategory = new JSONObject();
            JSONObject listMiniSubCategory = new JSONObject();
            JSONObject superMiniSubCategory = new JSONObject();
            JSONObject mainCategory = new JSONObject();
            JSONObject category = new JSONObject();
            JSONObject json = new JSONObject();
            JSONObject variant = new JSONObject();
            JSONObject variant1 = new JSONObject();


            Discount existingProduct = listHotProduct.get();

            json.put("id",existingProduct.getId());
            json.put("code",existingProduct.getCode());
//            json.put("priceCoupon",books);
            json.put("priceCoupon",existingProduct.getDiscountPrice());

            json.put("status",existingProduct.getStatus());


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

//    @GetMapping("detail/{id}/order")
//    public  ResponseEntity<?>  getDetailorder(@PathVariable (value = "id") Long id) {
//        List<Cart> cart = cartRepository.findByUserId(id);
//
//        JSONObject listSub = new JSONObject();
//        JSONObject resp = new JSONObject();
//
//        List<JSONObject> listItem = new ArrayList<>();
//        JSONObject useradress = new JSONObject();
//        List<JSONObject> listProduct = new ArrayList<>();
//
//        int total = 0;
////        Cart cart = user.getCart();
////        Product product = productRepository.findOne(productId);
////        List<CartItem> cartItems = cart.getCartItems();
//
////        List<CartItem> cartItem = (List<CartItem>) new JSONObject();
//        Optional<Order> listHotProduct = orderService.getByid(id);
//        for(Order objects : listHotProduct) {
//            JSONObject json = new JSONObject();
//            JSONObject product = new JSONObject();
//            Total = objects.getProduct().getPrice()*objects.getQty();
//            int subTotall = total += Total;;
//
//
////            List<JSONObject> total = new ArrayList<>();
//
////           Total = objects.getProductCart(objects.getCartProduct().getPrice()*objects.getAmount());
//
//
//
////            listSub.put("userAddress",listItem);
//
//
//
////            listSub.put("totalPrice",Total);
//            listSub.put("orderId",objects.getOrder().getId());
//            listSub.put("subTotal",subTotall);
//            listSub.put("total",objects.getOrder().getTotal());
//            listSub.put("productTotal",objects.getOrder().getProductTotal());
//            listSub.put("session",objects.getOrder().getSession());
//            listSub.put("paymentType",objects.getOrder().getPaymentType());
//            listSub.put("status",objects.getOrder().getStatus());
//
//
//
//
////            listItem.add(useradress);
//            listProduct.add(product);
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


    @DeleteMapping("{id}/deleteItemCart")
    public  ResponseEntity<?> deleteCartItem(@PathVariable(value = "id") Long id){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();


        cartItemService.deleteCartItem(id);
        if(cartItemService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Wishlist Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }

    @PostMapping("{id}/updateQtyItemCart")
    public  ResponseEntity<?> updateQtycartItem(@RequestBody UpdateQtyDto qtyDto, @PathVariable(value = "id") Long id, Long amount){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();


        cartItemService.updateQty(qtyDto.getAmount(amount),id);
        if(cartItemService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Cart Item Qty Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }

    @PostMapping("userAddress")
    public  ResponseEntity<?> insertuserAddress(@RequestBody AddressDto addressDto,Long userId ,String firstName, String lastName, String company,String country , String city, String address, String email, String phone, String postCode){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();

//        UserAddress savedCustomer = customerRepository.save(customer);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(userAddressService.getId()).toUri();


        userAddressService.insertAddress(addressDto.getUserId(userId),addressDto.getFirstName(firstName),addressDto.getLastName(lastName),addressDto.getCompanyName(company),addressDto.getCountry(country),addressDto.getCity(city),addressDto.getAddress(address),addressDto.getEmail(email),addressDto.getPhone(phone),addressDto.getPostCode(postCode));
        if(userAddressService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Cart Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }

//    @PostMapping("userAddress")
//    public ResponseEntity<?> createClient(@RequestBody UserAddress userAddress) {
//        UserAddress savedClient = userAddressRepository.save(userAddress);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(savedClient.getId()).toUri();
//
//        return ResponseEntity.created(location).body(new ResponseSuccess(location,"Clients successfully created!"));
//
//    }


//    @PostMapping("order")
//    public  ResponseEntity<?> inserOrder(@RequestBody OrderDto orderDto, Long userId, Long userAddressId, Long total, Long subTotal, Long status, Long productTotal, String session, String paymentType,  ArrayList<ProductItem> productItem){
//        JSONObject resp = new JSONObject();
//        JSONObject result = new JSONObject();
//        JSONObject listArtis = new JSONObject();
//
//
//
//        orderService.insertAddTocart(orderDto.getUserId(userId),orderDto.getUserAddressId(userAddressId),orderDto.getTotal(total),orderDto.getSubTotal(subTotal),orderDto.getStatus(status),orderDto.getProductTotal(productTotal),orderDto.getSession(session),orderDto.getPaymentType(paymentType),orderDto.getProductItem(productItem));
//
//        if(orderService != null) {
//
//            resp.put("success", true);
//            resp.put("error", null);
//        } else {
//            resp.put("data", null);
//            resp.put("success", false);
//            resp.put("error",  new ErrorResponse("Cart Doesnt Exist", 204));
//        }
//        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);
//
//    }


    @PostMapping("order")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> createVehicle(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.createVehicle(orderDto), HttpStatus.CREATED);
    }


//
//    @PostMapping("order")
//    public ResponseEntity<?> createOrder(@RequestBody Order order) {
//        Order savedClient = orderRepository.save(order);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(savedClient.getId()).toUri();
//
//        return ResponseEntity.created(location).body(new ResponseSuccess(location,"Order successfully created!"));
//
//    }

//    @PostMapping()
//    public ResponseEntity<Object> createCustomer(@PathVariable(value = "id") Long userId,@RequestBody Customer customer)  {
//        Optional<User> user = userRepository.findById(userId);
//
//        if (!user.isPresent()) {
//            String msg = String.format("User not found with with id %s!", userId);
//            return new ResponseEntity(new ResponseError("ERR_NOT_FOUND", msg),
//                    HttpStatus.NOT_FOUND);
//        }
//
//
//
//        return ResponseEntity.created(location).body(new ResponseSuccess(customerCreateResponse));
//    }

    @GetMapping("/{id}/getUserAddressByUserId")
    public ResponseEntity<?> getAddressByUserId(@PathVariable(value = "id") Long id) {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<UserAddress> listHotProduct = userAddressService.getByUserId(id);
        for(UserAddress objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listUser = new JSONObject();
            json.put("idAddress", objects.getId());
            json.put("firstName", objects.getFirstName());
            json.put("lastName", objects.getLastName());
            json.put("company",objects.getCompanyName());
            json.put("country",objects.getCountry());
            json.put("city",objects.getEmail());
            json.put("address",objects.getAddress());
            json.put("email",objects.getEmail());
            json.put("phone",objects.getPhone());
            json.put("postCode",objects.getPostCode());
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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<?> getAddressByUserId() {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        List<Order> listHotProduct = orderService.getAllOrder();
        for(Order objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listUser = new JSONObject();
            json.put("orderId", objects.getId());
            json.put("session", objects.getSession());
            json.put("paymentType", objects.getPaymentType());
            json.put("status",objects.getStatus());
            json.put("total",objects.getTotal());
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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/{id}/updateStatusOrder")
    public  ResponseEntity<?> changeIsLive(@RequestBody UpdateStatusDto updateStatusDto, @PathVariable(value = "id") Long id, Long status){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();

        orderService.changeStatus(updateStatusDto.getStatus(status),id);
        if(orderService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Live Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }

    @GetMapping("{id}/getArticleByCategoryId")
    public ResponseEntity<?> getArticel(@PathVariable(value = "id") Long id) {

        JSONObject listSub = new JSONObject();
        JSONObject resp = new JSONObject();

        List<JSONObject> listItem = new ArrayList<>();

        List<Articel> listHotProduct = articelService.getByArticelCategoryId(id);
        JSONObject listUser = new JSONObject();
        for(Articel objects : listHotProduct) {
            JSONObject json = new JSONObject();

            listSub.put("listArticle",listItem);

            json.put("title",objects.getTitle());
            json.put("subtitle",objects.getSubTitle());
            json.put("description",objects.getDescription());
            json.put("descriptionSecond",objects.getDescription());
            json.put("imageUrl",objects.getImageBannerUrl());


            listSub.put("idArticleCategory",objects.getArticelCategory().getId());
            listSub.put("nameCategory",objects.getArticelCategory().getName());

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
            resp.put("error",  new ErrorResponse("Article Doesnt Exist", 500));
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/categoryArticle")
    public ResponseEntity<?> getAllBanner() {

        JSONObject listSub = new JSONObject();
        JSONObject resp = new JSONObject();

        List<JSONObject> listItem = new ArrayList<>();

        List<ArticelCategory> listHotProduct = articelService.getAllCategoryArticel();
        JSONObject listUser = new JSONObject();
        for(ArticelCategory objects : listHotProduct) {
            JSONObject json = new JSONObject();

            listSub.put("categoryArticle",listItem);


            json.put("idArticleCategory",objects.getId());
            json.put("nameCategory",objects.getName());

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
            resp.put("error",  new ErrorResponse("Article Doesnt Exist", 500));
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}/variant")
    public ResponseEntity<?> getVariant(@PathVariable(value = "id") Long id) {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        JSONObject mainCategory = new JSONObject();
        List<VariantProduct> listHotProduct = variantService.getByProductId(id);
        for(VariantProduct objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listUser = new JSONObject();
            json.put("id", objects.getProductVariant().getId());
            json.put("productName", objects.getProductVariant().getName());
            json.put("price", objects.getProductVariant().getPrice());
            json.put("thumbnail",objects.getProductVariant().getThumNailUrl());

            json.put("category",mainCategory);
            mainCategory.put("id",objects.getProductVariant().getMiniSubCategoryProperties().getId());
            mainCategory.put("name",objects.getProductVariant().getMiniSubCategoryProperties().getCategoryname());

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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}/color")
    public ResponseEntity<?> getColor(@PathVariable(value = "id") Long id) {

        JSONObject response = new JSONObject();
        JSONObject resp = new JSONObject();
        List<JSONObject> listResult = new ArrayList<>();
        List<JSONObject> listSub = new ArrayList<>();
        JSONObject result = new JSONObject();
        JSONObject mainCategory = new JSONObject();
        List<ColorProduct> listHotProduct = productService.getColorProduct(id);
        for(ColorProduct objects : listHotProduct) {
            JSONObject json = new JSONObject();
            JSONObject listUser = new JSONObject();
            json.put("id", objects.getProductRelated().getId());
            json.put("colorName", objects.getProductRelated().getColor());

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
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping("{id}/deleteCart")
    public  ResponseEntity<?> deleteCart(@PathVariable(value = "id") Long id){
        JSONObject resp = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject listArtis = new JSONObject();


        cartItemService.deleteCart(id);
        if(cartItemService != null) {
            resp.put("success", true);
            resp.put("error", null);
        } else {
            resp.put("data", null);
            resp.put("success", false);
            resp.put("error",  new ErrorResponse("Wishlist Doesnt Exist", 204));
        }
        return new ResponseEntity<JSONObject>(resp, HttpStatus.OK);

    }
//
//    @GetMapping("{id}/addToCart")
//    public ResponseEntity<?> getAddToCartItems(@PathVariable (value = "id") Long id) {
//
//        JSONObject response = new JSONObject();
//        JSONObject resp = new JSONObject();
//        List<JSONObject> listResult = new ArrayList<>();
//        List<JSONObject> listSub = new ArrayList<>();
//        JSONObject result = new JSONObject();
//        List<CartItem> listHotProduct = cartItemService.getByUserId(id);
//        for(CartItem objects : listHotProduct) {
//            JSONObject json = new JSONObject();
//            JSONObject listSubCategory = new JSONObject();
//            JSONObject listMiniSubCategory = new JSONObject();
//            JSONObject superMiniSubCategory = new JSONObject();
//            JSONObject mainCategory = new JSONObject();
////            JSONObject json = new JSONObject();
//            json.put("id", objects.getId());
////
////            json.put("user",mainCategory);
////            mainCategory.put("id",objects.getProductCart().getCartUser().getId());
////            mainCategory.put("name",objects.getProductCart().getCartUser().getFullname());
////            mainCategory.put("email",objects.getProductCart().getCartUser().getEmail());\
//
//
//
//            json.put("listItems",listSubCategory);
//            listSubCategory.put("id",objects.getCartProduct().getId());
//            listSubCategory.put("name",objects.getCartProduct().getName());
//            listSubCategory.put("price",objects.getCartProduct().getPrice());
//            listSubCategory.put("amount", objects.getAmount());
//
//            listSub.add(json);
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

}
