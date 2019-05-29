$(function () {
    var shopId = getQueryString('shopId');
    var shopInfoUrl = '/o2o/shop/getshopmanagementinfo.do?shopId='+shopId;
    $.getJSON(shopInfoUrl,function (data) {
        if(data.redirect){
            window.location.href=data.url;
        }else {
            if(data.shopid!=undefined&&data.shopid!=null){
                shopId = data.shopid;
            }
            $('#shopInfo').attr('href','/o2o/shopadmin/shopoperation.do?shopId='+shopId);
        }
    })
})