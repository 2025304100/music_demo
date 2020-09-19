package com.music_demo.entity.information;

public class PageView {
    private Integer index;
    private Long visitor_volume;
    private PageType pageType;
    private String explain;

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getExplain() {
        return explain;
    }

    public Long getVisitor_volume() {
        return visitor_volume;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public PageType getPageType() {
        return pageType;
    }

    public void setVisitor_volume(Long visitor_volume) {
        this.visitor_volume = visitor_volume;
    }

    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }

    public Integer getIndex() {
        return index;
    }


}
