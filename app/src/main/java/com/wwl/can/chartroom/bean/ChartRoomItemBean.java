package com.wwl.can.chartroom.bean;

/**
 * Created by wangwenliang on 2017/12/8.
 */

public class ChartRoomItemBean {
    private String nickname;
    private String chartcontent;
    private int type; //布局类型 1：左侧 2：右侧

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getChartcontent() {
        return chartcontent;
    }

    public void setChartcontent(String chartcontent) {
        this.chartcontent = chartcontent;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChartRoomItemBean{" +
                "nickname='" + nickname + '\'' +
                ", chartcontent='" + chartcontent + '\'' +
                ", type=" + type +
                '}';
    }
}
