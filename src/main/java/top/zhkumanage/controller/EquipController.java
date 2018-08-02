package top.zhkumanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.additional.Socket.SocketWThreadManage;
import top.common.Page.PageQueryBean;
import top.zhkumanage.equip.entity.EquipMSG;
import top.zhkumanage.equip.entity.EquipMSGMap;
import top.zhkumanage.equip.service.EquipService;
import top.zhkumanage.equip.vo.EquipQueryCondition;
import top.zhkumanage.user.entity.User;
import top.zhkumanage.user.entity.UserStatus;
import top.zhkumanage.user.service.UserStatusService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("equip")
public class EquipController {
    @Autowired
    private UserStatusService userStatusService;
    @Autowired
    private EquipService equipService;
    @Autowired
    private EquipMSGMap equipMSGMap;
    @Autowired
    private SocketWThreadManage socketWThreadManage;


    @RequestMapping("equiplist")
    @ResponseBody
    public PageQueryBean QueryPageData(){
        PageQueryBean pageQueryBean = equipService.queryAllEquip();
        return pageQueryBean;
    }

    @RequestMapping("Cequiplist")
    @ResponseBody
    public List<EquipMSG> QueryPageData(@RequestBody User user){
        return equipService.selectAllByUserId(user.getIduser());
    }

    @RequestMapping("gimsg")
    @ResponseBody
    public EquipMSG getequipMsg(@RequestBody EquipMSG equipMSG){
        EquipMSG equipMSG1 = equipMSG;
        if (equipMSG1.getIduser() == null){
            return null;
        }
//        if (userStatusService.selectByPrimaryKey(equipMSG1.getIduser()).getStatus() == 0){
//            equipMSG.setMessage((String) equipMSGMap.get(equipMSG1.getIdEquip()));
//        }
        equipMSG.setMessage((String) equipMSGMap.get(equipMSG1.getIdEquip()));
        return equipMSG;
    }

    @RequestMapping("ole")
    @ResponseBody
    public List onlineequip(){
        List list = new ArrayList();
        Set set = equipMSGMap.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()){
            list.add(it.next());
        }
        return list;
    }

    @RequestMapping("smte")
    @ResponseBody
    public String sendmsgtoequip(@RequestBody EquipMSG equipMSG){
        EquipMSG equipMSG1 = equipMSG;
        if (!equipMSGMap.containsKey(equipMSG1.getIdEquip())){
            return "NO EQUIP";
        }
        String message = equipMSG1.getMessage();
        int socketId = Integer.parseInt(message.substring(message.indexOf("SOCKETID:") + 9,message.indexOf("DATA:")));
        message = message.substring(message.indexOf("DATA:"));
        try {
            socketWThreadManage.writeToSocket(socketId,message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }
}
