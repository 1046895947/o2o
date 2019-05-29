$(function () {
    //从URL里获取productId参数的值
    var productId = getQueryString('productId');
    //通过productId获取商品信息的URL
    var infoUrl = '/o2o/shopadmin/getproductbyid.do?productId='+productId;
    //获取当前店铺设定的商品类别的URL
    var categoryUrl = '/o2o/shopadmin/getproductcategorylist.do';
    //更新商品信息的URL
    var productPostUrl = '/o2o/shopadmin/modifyproduct.do';
    //由于商品添加和编辑使用的是同一个页面
    //该标识符用来标明本次是添加还是编辑操作
    var isEdit = false;
    if(productId){
        //有productId则为编辑操作
        getInfo(productId);
        idEdit = true;
    }else {
        getCategory();
        productPostUrl = '/o2o/shopadmin/insertproduct.do';
    }

    //获取需要编辑的商品信息并赋值给表单
    function getInfo() {
        $.getJSON(infoUrl,function (data) {
            if(data.success){
                //从返回的JSON中获取product对象信息，并赋值给表单
                var product = data.product;
                $('#product-name').val(product.productname);
                $('#product-desc').val(product.productdesc);
                $('#product-oldprice').val(product.productoldprice);
                $('#product-newprice').val(product.productnewprice);
                $('#product-priority').val(product.productpriority);
                //获取原本的商品类别以及该店铺的所有商品类别列表
                var optionHtml = "";
                var optionArr = data.productCategoryList;
                var optionSelected = product.productcategoryid;
                //生成前端的HTML商品类别列表，并默认选择编辑前的商品类别
                optionArr
                    .map(function(item, index) {
                        var isSelect = optionSelected === item.productcategoryid ? 'selected'
                            : '';
                        optionHtml += '<option data-value="'
                            + item.productcategoryid
                            + '"'
                            + isSelect
                            + '>'
                            + item.productcategoryname
                            + '</option>';
                    });
                $('#category').html(optionHtml);
            }
        })
    }

    //为商品添加操作提供该店铺下的所有商品类别列表
    function getCategory() {
        $.getJSON(categoryUrl, function(data) {
            if (data.success) {
                var productCategoryList = data.productCategoryList;
                var optionHtml = '';
                productCategoryList.map(function(item, index) {
                    optionHtml += '<option data-value="'
                        + item.productcategoryid + '">'
                        + item.productcategoryname + '</option>';
                });
                $('#category').html(optionHtml);
            }
        });
    }

    //给.detail-img下的最后一个标签添加事件
    $('#detail-img').on('change', '.detail-img:last-child', function() {
        if ($('.detail-img').length < 6) {
            $('#detail-img').append('<input type="file" class="detail-img">');
        }
    });


    //提交验证
    $('#submit').click(
        function() {
            var product = {};
            product.productname = $('#product-name').val();
            product.productdesc = $('#product-desc').val();
            product.productpriority = $('#product-priority').val();
            product.productoldprice = $('#product-oldprice').val();
            product.productnewprice = $('#product-newprice').val();
            product.productcategoryid = $('#category').find('option').not(
                function() {
                    return !this.selected;
                }).data('value');
            //添加就是空，修改就是有值
            product.productid = productId;
            //获取缩略图文件流
            var thumbnail = $('#small-img')[0].files[0];

            //生成表单对象，用于接收参数并传递给后台
            var formData = new FormData();
            formData.append('thumbnail', thumbnail);
            $('.detail-img').map(
                function(index, item) {
                    if ($('.detail-img')[index].files.length > 0) {
                        formData.append('productImg' + index,
                            $('.detail-img')[index].files[0]);
                    }
                });
            formData.append('productStr', JSON.stringify(product));
            var verifyCodeActual = $('#j_captcha').val();
            if (!verifyCodeActual) {
                $.toast('请输入验证码！');
                return;
            }
            //将验证码加入表单里面
            formData.append("verifyCodeActual", verifyCodeActual);
            $.ajax({
                url : productPostUrl,
                type : 'POST',
                data : formData,
                contentType : false,
                processData : false,
                cache : false,
                success : function(data) {
                    if (data.success) {
                        $.toast('提交成功！');
                        $('#captcha_img').click();
                    } else {
                        $.toast(data.errMsg);
                        $('#captcha_img').click();
                    }
                }
            });
        });

});