package com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.bean;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Lixixiang on 2023/2/14 15:21
 */
public class KeepPlanBean extends BaseExpandNode {

    private String DeviceId;
    private String depart;
    private String people;
    private String nearKeep;
    private int progress;
    private List<BaseNode> childNode;


    public KeepPlanBean( List<BaseNode> childNode,String deviceId, String depart, String people, String nearKeep, int progress) {
        DeviceId = deviceId;
        this.depart = depart;
        this.people = people;
        this.nearKeep = nearKeep;
        this.progress = progress;
        this.childNode = childNode;
        setExpanded(false);
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setChildNode(List<BaseNode> childNode) {
        this.childNode = childNode;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getNearKeep() {
        return nearKeep;
    }

    public void setNearKeep(String nearKeep) {
        this.nearKeep = nearKeep;
    }

    public int getProgress() {
        return progress;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }
}
