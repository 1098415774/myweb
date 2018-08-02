package top.zhkumanage.equip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.common.Page.PageQueryBean;
import top.zhkumanage.equip.dao.EquipMSGMapper;
import top.zhkumanage.equip.entity.EquipMSG;

import java.util.List;

@Service("equipServiceIMP")
public class EquipServiceIMP implements EquipService{
    @Autowired
    private EquipMSGMapper msgMapper;


    @Override
    @Transactional
    public PageQueryBean queryAllEquip() {
        PageQueryBean pageQueryBean = new PageQueryBean();
        List<EquipMSG> list = msgMapper.selectAll();
        pageQueryBean.setItems(list);
        return pageQueryBean;
    }

    @Override
    @Transactional
    public int insertEquip(EquipMSG equipMSG) {
        return msgMapper.insert(equipMSG);
    }

    @Override
    public EquipMSG selectByPrimaryKey(Integer idEquip) {
        return msgMapper.selectByPrimaryKey(idEquip);
    }

    @Override
    public int updateByPrimaryKey(EquipMSG record) {
        return msgMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<EquipMSG> selectAllByUserId(Integer idUser) {
        return msgMapper.selectAllByUserId(idUser);
    }
}
