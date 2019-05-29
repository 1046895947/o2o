function changeVerifyCode(img) {
    img.src="../kaptcha.jpg?" + Math.floor(Math.random()*100);
}
//根据URL中的key获取其值如?userId=1001,,根据userId获取1001
//找魏伟解释
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return '';
}