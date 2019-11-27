package com.hias.apps.domain;

//import com.fineoz.sec.model.audit.DateAudit;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

//import com.fineoz.sec.model.User;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;


@Entity
@Table(name = "media", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "fullpath"
        })
})
public class Media{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    private String name;


    private String path;


    private String fullpath;


    private long size;


    @NotBlank
    @Size(max = 40)
    @Column(name = "mimetype")
    private String mimetype;

//    @Temporal(TemporalType.DATE)
//    @Column(name = "deleted_at")
//    private Date deleted;

    public Media(){

    }

    public Media(String name, String path, String fullpath, long size, String mimetype){
        this.name = name;
        this.path = path;
        this.fullpath = fullpath;
        this.size = size;
        this.mimetype = mimetype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFullPath() {
        return fullpath;
    }

    public void setFullPath(String fullpath) {
        this.fullpath = fullpath;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }
}