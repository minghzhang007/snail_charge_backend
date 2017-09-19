var VM;

VM = new Vue({
    el: "#app",
    data: {
        pageSize: 10,
        // 搜索表单
        queryForm: {},
        // 结果对象
        result: {
            data: [],
            paginator: {
                currentPage: 0,
                totalCount: 0,
                totalPage: 0
            }
        },
    },
    mounted: function () {
        this.$nextTick(function () {
            this.query();
        })
    }
    ,
    methods: {
        query: function () {
            var _this = this;
            var contextPath = $("#contextPath").text();
            var url = window.location.protocol + "//" + window.location.host + contextPath;
            var pageInfo = {
                "currentPage": _this.result.paginator.currentPage,
                "pageSize": _this.pageSize,
                "totalCount": _this.result.paginator.totalCount
            };
            var formData = {
                "tradeStatus": $("#tradeStatus").val(),
                "startTime": '' != $("#startTime").val() ? moment($("#startTime").val()).format('X') * 1000 : null,
                "endTime": '' != $("#endTime").val() ? moment($("#endTime").val()).format('X') * 1000 : null
            }
            var data2 = $.extend(formData, pageInfo);
            $.ajax({
                url: url + "query",
                type: "GET",
                dataType: "JSON",
                data: data2,
                success: function (data) {
                    _this.result = data;
                }
            });
        },

        exportToExcel: function () {
            window.location.href = "http://localhost:8060/vue/export";
        }
    }
});

