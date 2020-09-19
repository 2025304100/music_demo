package com.music_demo.entity.information;

import java.io.Serializable;

public class PageType implements Serializable {
    private Long visitor_volume;
    private Long direct;
    private Long consumer;
    private Long memberPV;
    private Long supermenberpv;

    public void setConsumer(Long consumer) {
        this.consumer = consumer;
    }

    public void setSupermenberpv(Long supermenberpv) {
        this.supermenberpv = supermenberpv;
    }

    public Long getConsumer() {
        return consumer;
    }

    public Long getSupermenberpv() {
        return supermenberpv;
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

    public Long getVisitor_volume() {
        return visitor_volume;
    }

    public Long getDirect() {
        return direct;
    }

    public Long getMemberPV() {
        return memberPV;
    }
}
