package top.additional.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import top.additional.ContextUtil;
import top.common.Utils.DateUtils;
import top.common.Utils.FileUtil;
import top.zhkumanage.equip.entity.EquipMSG;
import top.zhkumanage.equip.entity.EquipMSGMap;
import top.zhkumanage.equip.service.EquipService;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketRThread implements Runnable{
    private Socket socket;
    private BufferedReader reader;
    private EquipMSG equipMSG;
    private Integer equipid;
    private String equiptype;
    private String equipdata;
    private boolean isEquip = false;
    private EquipService equipService;
    private EquipMSGMap equipMSGMap;
    private File file;
    private BufferedWriter writer;
    private SocketWThreadManage socketWThreadManage = (SocketWThreadManage) ContextUtil.getBean("socketWThreadManage");

    SocketRThread(Socket socket){
            this.socket = socket;
            equipService = (EquipService) ContextUtil.getBean("equipServiceIMP");
            equipMSGMap = (EquipMSGMap) ContextUtil.getBean("equipMSGMap");
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String str = null;
            while ((str = reader.readLine()) != null){
                equiptype = str.substring(str.indexOf("ETP:") + 4,str.indexOf("DATA:")).trim();
                if (equiptype.equals("equip")){
                    try {
                        equipid = Integer.parseInt(str.substring(str.indexOf("EID:") + 4,str.indexOf("ETP:")).trim());
                        equipdata = str.substring(str.indexOf("DATA:") + 5).trim();
                        equipdata += "TIM:" + DateUtils.getTimeByStr() + "SOCKETID:" + socket.hashCode();
                        equipMSG = new EquipMSG();
                        equipMSG.setIdEquip(equipid);
                        equipMSG.setTypeEquip(equiptype);
                        equipMSG.setStatusEquip(0);
                        equipMSG.setMessage(equipdata);
                        if (!isEquip){
                            if (null == equipService.selectByPrimaryKey(equipid)){
                                //第一次注册
                                file = FileUtil.creatXMLFile(String.valueOf(equipid),"../webapps/zhkumanager/equipdatas",equipMSG);
                                equipMSG.setStroepath(file.getCanonicalPath());
                                equipService.insertEquip(equipMSG);
                            }
                            isEquip = true;
                        }
                        equipMSGMap.put(equipid,equipMSG.getMessage());
//                    equipMSG.setMessageEquip(equipdata);
//                    equipService.updateByPrimaryKey(equipMSG);
                    }catch (Exception e){
                        e.printStackTrace();
                        break;
                    }
                }
//
//                else if (equiptype.equals("client")){
//                    equipid = Integer.parseInt(str.substring(str.indexOf("EID:") + 4,str.indexOf("ETP:")).trim());
//                    Socket csocket = (Socket) equipMSGMap.get(equipid);
//                    if (writer == null){
//                        writer = new PrintWriter(socket.getOutputStream());
//                    }
//                    if (null != csocket){
//                        BufferedReader creader = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
//                        String cstr = null;
//                        while ((cstr = creader.readLine()) != null){
//                            equipdata = cstr.substring(cstr.indexOf("DATA:") + 5).trim();
//                            writer.print(equipdata + "\n");
//                            writer.flush();
//                        }
//                    }else {
//                        writer.print("no" + "\n");
//                        writer.flush();
//                    }
//                }

                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
            List<Socket> socketList = (ArrayList<Socket>) ContextUtil.getBean("socketList");
            socketList.remove(socket);
        }finally {
            try {
                if (isEquip){
                    equipMSGMap.remove(equipid);
                }
                socketWThreadManage.remove(socket.hashCode());
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
