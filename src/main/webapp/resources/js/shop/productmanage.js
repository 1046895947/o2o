$(function() {
    var shopId = 1;
    var listUrl = '/o2o/shopadmin/getproductlistbyshop.do?pageIndex=1&pageSize=999';
    var statusUrl = '/o2o/shopadmin/modifyproduct.do';

    function getList() {
        $.getJSON(listUrl, function(data) {
            shopId = data.shopId;
            $("#my_back").attr("href","/o2o/shopadmin/shopmanagement.do?shopId="+shopId);
            if (data.success) {
                var productList = data.productList;
                var tempHtml = '';
                productList.map(function(item, index) {
                    var textOp = "下架";
                    var contraryStatus = 0;
                    if (item.productstatus == 0) {
                        //如果是上架状态显示为下架按钮
                        textOp = "上架";
                        contraryStatus = 1;
                    } else {
                        contraryStatus = 0;
                    }
                    tempHtml += '' + '<div class="row row-product">'
                        + '<div class="col-33">'
                        + item.productname
                        + '</div>'
                        + '<div class="col-20">'
                        + item.productpriority
                        + '</div>'
                        + '<div class="col-47">'
                        + '<a href="#" class="edit" data-id="'
                        + item.productid
                        + '" data-status="'
                        + item.productstatus
                        + '">编辑</a>'
                        + '<a href="#" class="status" data-id="'
                        + item.productid
                        + '" data-status="'
                        + item.productstatus
                        + '">'
                        + textOp
                        + '</a>'
                        + '<a href="#" class="preview" data-id="'
                        + item.productid
                        + '" data-status="'
                        + item.productstatus
                        + '">预览</a>'
                        + '</div>'
                        + '</div>';
                });
                $('.product-wrap').html(tempHtml);
            }
        });
    }

    getList();

    // function deleteItem(id, enableStatus) {
    //     var product = {};
    //     product.productId = id;
    //     product.enableStatus = enableStatus;
    //     $.confirm('确定么?', function() {
    //         $.ajax({
    //             url : deleteUrl,
    //             type : 'POST',
    //             data : {
    //                 productStr : JSON.stringify(product),
    //                 statusChange : true
    //             },
    //             dataType : 'json',
    //             success : function(data) {
    //                 if (data.success) {
    //                     $.toast('操作成功！');
    //                     getList();
    //                 } else {
    //                     $.toast('操作失败！');
    //                 }
    //             }
    //         });
    //     });
    // }

    $('.product-wrap')
        .on(
            'click',
            'a',
            function(e) {
                var target = $(e.currentTarget);
                if (target.hasClass('edit')) {
                    window.location.href = '/o2o/shopadmin/productoperation.do?productId='
                        + e.currentTarget.dataset.id;
                } else if (target.hasClass('status')) {
                    changeItemStatus(e.currentTarget.dataset.id,
                        e.currentTarget.dataset.status);
                } else if (target.hasClass('preview')) {
                    window.location.href = '/o2o/frontend/productdetail?productId='
                        + e.currentTarget.dataset.id;
                }
            });

    function changeItemStatus(id,enableStatus){
        var product = {};
        product.productid = id;
        if(enableStatus==0){
            enableStatus=1;
        }else {
            enableStatus=0;
        }
        product.productstatus = enableStatus;
        $.confirm('确定么?', function() {
            $.ajax({
                url : statusUrl,
                type : 'POST',
                data : {
                    productStr : JSON.stringify(product),
                    statusChange : true
                },
                dataType : 'json',
                success : function(data) {
                    if (data.success) {
                        $.toast('操作成功！');
                        getList();
                    } else {
                        $.toast('操作失败！');
                    }
                }
            });
        });
    }

    $('#new').click(function() {
        window.location.href = '/o2o/shopadmin/productoperation.do';
    });
});