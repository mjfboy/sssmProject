package com.oa.model;

import java.util.List;

public class TauthorityVo extends TAuthority {
    private List<TauthorityVo> childs;

    public TauthorityVo() {
    }

    @Override
    public String toString() {
        return "TauthorityVo{" +
                "childs=" + childs +
                '}';
    }

    public List<TauthorityVo> getChilds() {
        return childs;
    }

    public void setChilds(List<TauthorityVo> childs) {
        this.childs = childs;
    }
}
