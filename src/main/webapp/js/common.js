function _change() {
    $("#vCode").attr("src", "/goods/captcha/getCaptchaImage.action?timestamp=" +new Date().getTime());
}
