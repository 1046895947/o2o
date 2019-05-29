$(function () {
    getList();
    function getList() {
        $.ajax({
            url:"/o2o/shop/getshoplist.do",
            type:"get",
            dataType:"json",
            success:function (data) {
                if (data.success){
                    handleList(data.shopList);
                    handleUser(data.user);
                }
            }
        });
    }
    function handleUser(data) {
        $("#user-name").text(data.name);
    }
    function handleList(data) {
        var html="";
        data.map(function (value, index) {
            html+='<div class="row row-shop"><div class="col-40">'
                +value.shopname+'</div><div class="col-40">'
                +shopStatus(value.shopstatus)+'</div><div class="col-20">'
                +goShop(value.shopstatus,value.shopid)+'</div></div>'
        });
        $(".shop-wrap").html(html);
    }
    function shopStatus(status) {
        if(status == 0){
            return '审核中';
        }else if (status == -1){
            return '店铺非法';
        }else if (status == 1){
            return '审核通过';
        }
    }
    function goShop(status,id) {
        if(status==1){
            return '<a href="/o2o/shopadmin/shopmanagement.do?shopId='+id+'">进入</a>'
        }else {
            return "";
        }
    }
});