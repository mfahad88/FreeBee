package com.example.freebee.models.CallRates;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Packages {

@SerializedName("data")
@Expose
private List<Datum> data = null;
@SerializedName("count")
@Expose
private Integer count;

public List<Datum> getData() {
return data;
}

public void setData(List<Datum> data) {
this.data = data;
}

public Integer getCount() {
return count;
}

public void setCount(Integer count) {
this.count = count;
}

}
