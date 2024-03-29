package com.oa.model;

public class TAuthority {
    private Integer id;

    private String aicon;

    private String aurl;

    private Integer parentid;

    private String title;

    private Integer type;

    @Override
    public String toString() {
        return "TAuthority{" +
                "id=" + id +
                ", aicon='" + aicon + '\'' +
                ", aurl='" + aurl + '\'' +
                ", parentid=" + parentid +
                ", title='" + title + '\'' +
                ", type=" + type +
                '}';
    }

    public TAuthority() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAicon() {
        return aicon;
    }

    public void setAicon(String aicon) {
        this.aicon = aicon == null ? null : aicon.trim();
    }

    public String getAurl() {
        return aurl;
    }

    public void setAurl(String aurl) {
        this.aurl = aurl == null ? null : aurl.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}