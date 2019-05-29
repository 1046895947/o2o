/**
 *直接定义一个function，加载到该js就会执行
 */
$(function () {
    var shopId = getQueryString('shopId');
    var isEdit = shopId?true:false;
    //根据该url获取区域信息和店铺分类信息
    var initUrl = '/o2o/shop/getshopinitinfo.do';
    //根据该url注册店铺
    var registerShopUrl = '/o2o/shop/registershop.do';
    //根据该url获取修改的店铺的详细信息
    var shopInfoUrl = "/o2o/shop/getshopbyid.do?shopId="+shopId;
    //根据该url更新店铺信息
    var editShopUrl = '/o2o/shop/modifyshop.do';
    //判断是否有shopId做出相应操作
    if(!isEdit){
        getShopInitInfo();
    }else {
        getShopInfo(shopId);
    }
    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function(data) {
            if (data.success) {
                var shop = data.shop;
                $('#shop-name').val(shop.shopname);
                $('#shop-place').val(shop.shopplace);
                $('#shop-phone-number').val(shop.shopphonenumber);
                $('#shop-desc').val(shop.shopdesc);
                var shopCategory = '<option data-id="'
                    + shop.shopcategoryid + '" selected>'
                    + data.shopCategoryName + '</option>';
                var tempPlaceHtml = '';
                data.placeList.map(function(value, index) {
                    tempPlaceHtml += '<option data-id="'+value.placeid+'">'+value.placename+'</option>';
                });
                //到这了到这了
                $('#shop-category').html(shopCategory);
                //attr设置选中标签中元素的属性值
                //设置不可更改
                $('#shop-category').attr('disabled','disabled');
                $('#shop-place-id').html(tempPlaceHtml);
                //设置默认选择
                $('#shop-place-id').attr('data-id',shop.placeid);
            }
        });
    }
    /**
     * 获取店铺分类信息和区域信息填充入前端中
     */
    function getShopInitInfo() {
        //jQuery.getJSON(url,data,success(data,status,xhr))
        // url	                        必需。规定将请求发送的哪个 URL。
        // data	                        可选。规定连同请求发送到服务器的数据。
        // success(data,status,xhr)     可选。规定当请求成功时运行的函数。
        //下面函数是回调的方法即success
        $.getJSON(initUrl,function (data) {
            if(data.success){
                var tempHtml = '';
                var tempPlaceHtml = '';
                //遍历shopCategoryList
                data.shopCategoryList.map(function (value, index) {
                    tempHtml += '<option data-id="'+value.shopcategoryid+'">'+value.shopcategoryname+'</option>';
                });
                data.placeList.map(function (value, index) {
                    tempPlaceHtml += '<option data-id="'+value.placeid+'">'+value.placename+'</option>';
                });
                //将数据加入前台
                $('#shop-category').html(tempHtml);
                $('#shop-place-id').html(tempPlaceHtml);
            }
        })
    }

    /**
     * 获取到表单中的信息中，提交，添加店铺
     */
    $('#submit').click(function () {
        var shop = {};
        shop.shopid=shopId;
        shop.shopname = $('#shop-name').val();
        shop.shopplace = $('#shop-place').val();
        shop.shopphonenumber = $('#shop-phone-number').val();
        shop.shopdesc = $('#shop-desc').val();
        shop.shopcategoryid = $('#shop-category').find('option').not(function () {
            return !this.selected;
        }).data('id');
        shop.placeid=$('#shop-place-id').find('option').not(function () {
            return !this.selected;
        }).data('id');
        var shopimg = $('#shop-img')[0].files[0];
        var formData = new FormData();
        formData.append('shopimg',shopimg);
        formData.append('shopStr',JSON.stringify(shop));
        var verofyCodeActual=$('#j_captcha').val();
        if(!verofyCodeActual){
            $.toast("请输入验证码！");
            return;
        }
        formData.append("verifyCodeActual",verofyCodeActual);
        $.ajax({
            url:(isEdit?editShopUrl:registerShopUrl),
            type:'POST',
            data:formData,
            contentType:false,
            processData:false,
            cache:false,
            success:function (data) {
                if(data.success){
                    $.toast('提交成功');
                }else {
                    $.toast('提交失败'+data.errMsg);
                }
                $('#captcha_img').click();
            }
        })
    })
})