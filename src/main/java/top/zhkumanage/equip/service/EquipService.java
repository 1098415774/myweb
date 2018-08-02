package top.zhkumanage.equip.service;

import top.common.Page.PageQueryBean;
import top.zhkumanage.equip.entity.EquipMSG;

import java.util.List;

public interface EquipService {
    PageQueryBean queryAllEquip();
    int insertEquip(EquipMSG equipMSG);
    EquipMSG selectByPrimaryKey(Integer idEquip);
    int updateByPrimaryKey(EquipMSG record);
    List<EquipMSG> selectAllByUserId(Integer idUser);

}
