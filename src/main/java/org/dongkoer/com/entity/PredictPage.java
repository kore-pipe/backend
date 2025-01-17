package org.dongkoer.com.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class PredictPage extends Predict {
    @JSONField(name = "fromIndex")
    private Integer fromIndex;
    @JSONField(name = "toIndex")
    private Integer toIndex;


    public PredictPage(String province, String date, String city) {
        super(province, date, city);
    }

    public PredictPage(String province, String date, String city, Integer fromIndex, Integer toIndex) {
        super(province, date, city);
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }
    public PredictPage() {
        super();
    }

    public Integer getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(Integer fromIndex) {
        this.fromIndex = fromIndex;
    }

    public Integer getToIndex() {
        return toIndex;
    }

    public void setToIndex(Integer toIndex) {
        this.toIndex = toIndex;
    }
}
