package com.example.tie_five_citi.Entity;

public class Shares {
    private String RIC;
    private String sharesName;
    private int classId;
    private float sharesPrice;
    private String currencyId;
    private int sharesNum;
    private int tradeLimit;
    private int sharesFlag;

    public float getSharesPrice() {
        return sharesPrice;
    }

    public int getClassId() {
        return classId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public int getSharesFlag() {
        return sharesFlag;
    }

    public int getSharesNum() {
        return sharesNum;
    }

    public int getTradeLimit() {
        return tradeLimit;
    }

    public String getRIC() {
        return RIC;
    }

    public String getSharesName() {
        return sharesName;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public void setRIC(String RIC) {
        this.RIC = RIC;
    }

    public void setSharesFlag(int sharesFlag) {
        this.sharesFlag = sharesFlag;
    }

    public void setSharesName(String sharesName) {
        this.sharesName = sharesName;
    }

    public void setSharesNum(int sharesNum) {
        this.sharesNum = sharesNum;
    }

    public void setSharesPrice(float sharesPrice) {
        this.sharesPrice = sharesPrice;
    }

    public void setTradeLimit(int tradeLimit) {
        this.tradeLimit = tradeLimit;
    }

    @Override
    public String toString() {
        return "shares{" +
                "RIC='" + RIC + '\'' +
                ", sharesName='" + sharesName + '\'' +
                ", classId=" + classId +
                ", sharesPrice=" + sharesPrice +
                ", currencyId=" + currencyId +
                ", sharesNum=" + sharesNum +
                ", tradeLimit=" + tradeLimit +
                ", sharesFlag=" + sharesFlag +
                '}';
    }
}
