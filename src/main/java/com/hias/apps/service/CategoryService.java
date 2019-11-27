package com.hias.apps.service;

import com.hias.apps.domain.*;

import com.hias.apps.repository.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private MainCategoryRepository mainCategoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private MiniSubCategoryRepository miniSubCategoryRepository;

    @Autowired
    private SupMiniSubCategory supMiniSubCategory;


    public Optional<MainCategory> getListMainCategoryId(Long id){
        Optional<MainCategory> result = mainCategoryRepository.findById(id);

        return result;
    }

    public List<MainCategory> getListMainCategoryAll(){
        List<MainCategory> result = mainCategoryRepository.findAll();

        return result;
    }

    public Optional<SubCategory> getListSubCategoryId(Long id){
        Optional<SubCategory> result = subCategoryRepository.findById(id);

        return result;
    }

    public List<SubCategory> getListSubCategoryAll(){
        List<SubCategory> result = subCategoryRepository.findAll();

        return result;
    }

    public List<SubCategory> getListSubCategoryByMainCategoryId(Long id){
        List<SubCategory> result = subCategoryRepository.findByMainCategoryId(id);

        return result;
    }


    public Optional<MiniSubCategory> getListMiniSubCategoryId(Long id){
        Optional<MiniSubCategory> result = miniSubCategoryRepository.findById(id);

        return result;
    }

    public List<MiniSubCategory> getLisMiniSubCategoryAll(){
        List<MiniSubCategory> result = miniSubCategoryRepository.findAll();

        return result;
    }

    public List<MiniSubCategory> getListMiniSubCategorBySubCategory(Long id){
        List<MiniSubCategory> result = miniSubCategoryRepository.findBySubCategoryId(id);

        return result;
    }


    public Optional<SupMiniCategory> getListSupMiniSubCategoryId(Long id){
        Optional<SupMiniCategory> result = supMiniSubCategory.findById(id);

        return result;
    }

    public List<SupMiniCategory> getLisSupMiniSubCategoryAll(){
        List<SupMiniCategory> result = supMiniSubCategory.findAll();

        return result;
    }

    public List<SupMiniCategory> getListSupMiniSubCategoryBySubCategory(Long id){
        List<SupMiniCategory> result = supMiniSubCategory.findBySupMiniSubCategoryId(id);

        return result;
    }

}
