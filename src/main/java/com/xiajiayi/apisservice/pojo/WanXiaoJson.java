package com.xiajiayi.apisservice.pojo;

/**
 * @Author 夏佳怡
 * @Date 2023/7/27 22:54
 * @Version 1.0
 * Description:
 * 用于定义完美校园电量接口返回的json数据
 * 例如：{"quantity":"18.0","quantityunit":"度","canbuy":"true","description":"101","custparams":"","returncode":"100","returnmsg":"SUCCESS"}
 */
public class WanXiaoJson {
    private String quantity;
    private String quantityunit;
    private boolean canbuy;
    private String description;
    private String custparams;
    private String returncode;
    private String returnmsg;

    public WanXiaoJson() {
    }

    public WanXiaoJson(String quantity, String quantityunit, boolean canbuy, String description, String custparams, String returncode, String returnmsg) {
        this.quantity = quantity;
        this.quantityunit = quantityunit;
        this.canbuy = canbuy;
        this.description = description;
        this.custparams = custparams;
        this.returncode = returncode;
        this.returnmsg = returnmsg;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantityunit() {
        return quantityunit;
    }

    public void setQuantityunit(String quantityunit) {
        this.quantityunit = quantityunit;
    }

    public boolean isCanbuy() {
        return canbuy;
    }

    public void setCanbuy(boolean canbuy) {
        this.canbuy = canbuy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustparams() {
        return custparams;
    }

    public void setCustparams(String custparams) {
        this.custparams = custparams;
    }

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getReturnmsg() {
        return returnmsg;
    }

    public void setReturnmsg(String returnmsg) {
        this.returnmsg = returnmsg;
    }
}
