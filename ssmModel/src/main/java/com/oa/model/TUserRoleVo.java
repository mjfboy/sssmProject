package com.oa.model;

import java.util.ArrayList;
import java.util.List;

public class TUserRoleVo extends TUser {
    //用户拥有的角色名字
    private List<String> role=new ArrayList<>();
    //用户所拥有的角色id集合
    private List<Integer> rids=new ArrayList<>();
    //用户所拥有的角色name集合
    private List<String> rnames=new ArrayList<>();
    //用户所拥有的角色集合
    private List<TRole> roleList=new ArrayList<>();//;

    @Override
    public String toString() {
        return "TUserRoleVo{" +
                "role=" + role +
                ", rids=" + rids +
                ", rnames=" + rnames +
                ", roleList=" + roleList +
                '}';
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public List<Integer> getRids() {
        return rids;
    }

    public void setRids(List<Integer> rids) {
        this.rids = rids;
    }

    public List<String> getRnames() {
        return rnames;
    }

    public void setRnames(List<String> rnames) {
        this.rnames = rnames;
    }

    public List<TRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<TRole> roleList) {
        this.roleList = roleList;

    }

    public TUserRoleVo() {

    }
}
