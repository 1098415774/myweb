$(function gettable() {
    $.getJSON("/equip/equiplist",function (data) {
        $.each(data.items,function (i,item) {
            var table = document.getElementById("table EquipmentStatus");
            var text = "<tr><td>" + item.idEquip + "</td><td>" + item.typeEquip + "</td><td>" + item.statusEquip + "</td></tr>";
            $(text).appendTo("table");
        })
    })
})