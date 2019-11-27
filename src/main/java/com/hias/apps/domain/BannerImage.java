package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "banner_image")
public class BannerImage {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name = "product_id", nullable = true)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Product productProperties;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "data")
    @Lob
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "banner_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Banner banner;

    public BannerImage() {

    }

    public BannerImage(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

//    public Product getProductProperties() {
//        return productProperties;
//    }
//
//    public void setProductProperties(Product productProperties) {
//        this.productProperties = productProperties;
//    }


    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }
}
