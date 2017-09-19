Vue.filter("toDateTime",
    function (value) {
        if (value) {
            return moment(value).format('YYYY-MM-DD HH:mm:ss');
        } else {
            return value;
        }
    }
);

Vue.filter("toDate",
    function (value) {
        if (value) {
            return moment(value).format('YYYY-MM-DD');
        } else {
            return value;
        }
    }
);

Vue.filter("tradeStatusEnum", function (value) {
    if(value === 0){
        return '未付款';
    }else if(value === 10){
        return '已付款';
    }else if(value == 20){
        return '交易关闭';
    }else{
        return '状态异常';
    }
});