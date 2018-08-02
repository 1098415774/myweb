package top.zhkumanage.equip.vo;

import top.common.Page.PageQueryBean;

public class EquipQueryCondition extends PageQueryBean{
    private Integer equipid;

    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getEquipid() {
        return equipid;
    }

    public void setEquipid(Integer equipid) {
        this.equipid = equipid;
    }
}
