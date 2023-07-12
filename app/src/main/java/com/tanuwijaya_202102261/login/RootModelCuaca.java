package com.tanuwijaya_202102261.login;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootModelCuaca {
    @SerializedName("list")
    private List<ListModel> listModelList;

    public  RootModelCuaca(){
    }

    public List<ListModel> getListModelList() {
        return listModelList;
    }

    public void setListModelList(List<ListModel> listModelList) {
        this.listModelList = listModelList;
    }
}