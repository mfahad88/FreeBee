package com.example.freebee.models.CallRates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("packagename")
@Expose
private String packagename;
@SerializedName("validity")
@Expose
private Integer validity;
@SerializedName("maxminutes")
@Expose
private Integer maxminutes;
@SerializedName("creationdate")
@Expose
private String creationdate;
@SerializedName("filename")
@Expose
private String filename;
@SerializedName("disable")
@Expose
private Integer disable;
@SerializedName("expirationdate")
@Expose
private String expirationdate;
@SerializedName("cost")
@Expose
private Double cost;
@SerializedName("bundlename")
@Expose
private String bundlename;
@SerializedName("deleted")
@Expose
private Integer deleted;
@SerializedName("vendorpackageid")
@Expose
private Integer vendorpackageid;
@SerializedName("dialylimit")
@Expose
private Integer dialylimit;
@SerializedName("packagetype")
@Expose
private String packagetype;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getPackagename() {
return packagename;
}

public void setPackagename(String packagename) {
this.packagename = packagename;
}

public Integer getValidity() {
return validity;
}

public void setValidity(Integer validity) {
this.validity = validity;
}

public Integer getMaxminutes() {
return maxminutes;
}

public void setMaxminutes(Integer maxminutes) {
this.maxminutes = maxminutes;
}

public String getCreationdate() {
return creationdate;
}

public void setCreationdate(String creationdate) {
this.creationdate = creationdate;
}

public String getFilename() {
return filename;
}

public void setFilename(String filename) {
this.filename = filename;
}

public Integer getDisable() {
return disable;
}

public void setDisable(Integer disable) {
this.disable = disable;
}

public String getExpirationdate() {
return expirationdate;
}

public void setExpirationdate(String expirationdate) {
this.expirationdate = expirationdate;
}

public Double getCost() {
return cost;
}

public void setCost(Double cost) {
this.cost = cost;
}

public String getBundlename() {
return bundlename;
}

public void setBundlename(String bundlename) {
this.bundlename = bundlename;
}

public Integer getDeleted() {
return deleted;
}

public void setDeleted(Integer deleted) {
this.deleted = deleted;
}

public Integer getVendorpackageid() {
return vendorpackageid;
}

public void setVendorpackageid(Integer vendorpackageid) {
this.vendorpackageid = vendorpackageid;
}

public Integer getDialylimit() {
return dialylimit;
}

public void setDialylimit(Integer dialylimit) {
this.dialylimit = dialylimit;
}

public String getPackagetype() {
return packagetype;
}

public void setPackagetype(String packagetype) {
this.packagetype = packagetype;
}

}


