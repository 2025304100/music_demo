package com.music_demo.entity.information;


public class PageSer {
    //时间序号
    private Integer index;
    //总访问量
    private Long visitor_volume;
    private Long direct;
    private Long memberPV;
    private Long superMenberPv;


    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    public Long getVisitor_volume() {
        return visitor_volume;
    }

    public Long getDirect() {
        return direct;
    }

    public Long getMemberPV() {
        return memberPV;
    }

    public Long getSuperMenberPv() {
        return superMenberPv;
    }

    public void setVisitor_volume(Long visitor_volume) {
        this.visitor_volume = visitor_volume;
    }

    public void setDirect(Long direct) {
        this.direct = direct;
    }

    public void setMemberPV(Long memberPV) {
        this.memberPV = memberPV;
    }

    public void setSuperMenberPv(Long superMenberPv) {
        this.superMenberPv = superMenberPv;
    }


}
