$(function() {
    //定义后台接口url，获取头条列表以及一级类别列表
    var url = '/o2o/frontend/listmainpageinfo.do';
    //获取信息
    $.getJSON(url, function (data) {
        if (data.success) {
            var headLineList = data.headLineList;
            var swiperHtml = '';
            //遍历头条列表，并拼接出轮播图组
            headLineList.map(function (item, index) {
                swiperHtml += ''
                    + '<div class="swiper-slide img-wrap">'
                    +      '<img class="banner-img" src="'+ item.lineimg +'" alt="'+ item.linename +'">'
                    + '</div>';
            });
            //将轮播图组赋值给前端HTML控件
            $('.swiper-wrapper').html(swiperHtml);
            //设定轮播图轮播时间为3秒
            $(".swiper-container").swiper({
                autoplay: 1000,
                //用户对轮播图进行操作时，是否自动停止autoplay
                autoplayDisableOnInteraction: false
            });
            //获取后台传递过来的大类列表
            var shopCategoryList = data.shopCategoryList;
            var categoryHtml = '';
            //遍历大类列表，拼接出col-50一行的类别
            shopCategoryList.map(function (item, index) {
                categoryHtml += ''
                    +  '<div class="col-50 shop-classify" data-category='+ item.shopcategoryid +'>'
                    +      '<div class="word">'
                    +          '<p class="shop-title">'+ item.shopcategoryname +'</p>'
                    +          '<p class="shop-desc">'+ item.shopcategorydesc +'</p>'
                    +      '</div>'
                    +      '<div class="shop-classify-img-warp">'
                    +          '<img class="shop-img" src="'+ item.shopcategoryimg +'">'
                    +      '</div>'
                    +  '</div>';
            });
            //将拼接好的类别赋值给前端HTML进行展示
            $('.row').html(categoryHtml);
        }
    });

    //若点击“我的”，则显示侧栏
    $('#me').click(function () {
        $.openPanel('#panel-left-demo');
    });

    //大类点击之后执行的代码
    $('.row').on('click', '.shop-classify', function (e) {
        var shopCategoryId = e.currentTarget.dataset.category;
        var newUrl = '/o2o/frontend/shoplist.do?parentId=' + shopCategoryId;
        window.location.href = newUrl;
    });

});
