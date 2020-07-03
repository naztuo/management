$(function () {
    //

    findPage(0);
    //定义一个全局变量


});


var str = "";


layui.use('laypage', function () {
    var laypage = layui.laypage;
    //执行一个laypage实例
    laypage.render({
        elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
        , count: cc //数据总数，从服务端得到
        , limit: 5
        , groups: 2
        , jump: function (obj, first) {
            if (!first) {
                $('#td').text('');
                str = "";
                findPage((obj.curr - 1) * 5);

            }
        }
    });
});


function findPage(n) {
    $.ajax({
        url: contextPath + "/news/findPage.do",
        type: "post",
        data: {
            "Fenye": n
        },
        success: function (s) {
            var arry = JSON.parse(s);
            console.log(arry);
            newslists(arry);
            $('#td').append(str)
            dd();
        }
    });
}


function newslists(arry) {
    for (var i = 0; i < arry.length; i++) {
        str += "<tr>";
        str += "<td>" + (i + 1) + "</td>"
        str += "<td>" + arry[i]["title"] + "</td>";
        str += "<td>" + arry[i]["sort"] + "</td>";
        str += "<td>" + arry[i]["author"] + "</td>";
        str += "<td>" + arry[i]["datas"] + "</td>";
        str += "<td>";
        str += "<a href='news_update.html?result="+arry[i]["id"]+"' class='layui-btn layui-btn-xs'><i class='layui-icon layui-icon-edit'></i>修改</a>";
        str += "<a class='layui-btn layui-btn-danger layui-btn-xs new_dels' rel='" + arry[i]["id"] + "'><i class='layui-icon layui-icon-delete'></i>删除</a>";
        str += "</td>";
        str += "<td> <input type='checkbox' value='" + arry[i]["id"] + "'></td>"
        str += "</tr>";
    }


}

$('#btnsd').click(function () {
    console.log(1)
    var $listc = $('input:checkbox');
    $listc.each(function (index, element) {
        if($listc.get(0).checked){
            $listc.get(index).checked=true;
        }else{
            $listc.get(index).checked=false;
        }
    });
});


function dd() {
    $(".new_dels").click(function () {
        var ids = $(this).attr("rel");
        layer.open({
            icon: 3,
            content: "确认删除 ID 为：" + $(this).attr("rel") + " 的信息吗？",
            title: "确认删除",
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                $.ajax({
                    url: contextPath+"/news/delnews.do",
                    type: "post",
                    data: {
                        "result": ids,
                    },
                    success: function (m) {
                        var arry = JSON.parse(m);
                        console.log(arry);
                        if (arry.state == 1) {
                            layer.msg("删除成功", {
                                icon: 1, time: 2000
                            });
                            setTimeout("location.href='news.html'", 1000);
                        } else {
                            layer.msg('删除失败', {icon: 5, time: 800});
                        }
                    }
                });

            }
            , btn2: function (index, layero) {
                layer.msg("已取消", {
                    icon: 2, time: 1000
                });
            }
        });
        return false;
    });
}


var num = 0;
$('#btn1').click(function () {
    var arr = "";
    var $listc =
        $('#td input:checkbox');
    $listc.each(function (index, element) {
        if($listc.get(index).checked){
            num++;
            arr+=$(this).val()+",";//拼接值
        }
    });
    console.log(arr)
    if (num == 0) {
        layer.msg('请先选中数据', {icon: 5, time: 800});
        return false;
    } else {
        layer.open({
            icon: 3,
            content: "确认删除该" + num + "条信息吗？",
            title: "确认删除",
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                $.ajax({
                    url: contextPath+"/delsn.do",
                    type: "post",
                    data: {
                        "result": arr
                    },
                    success: function (m) {
                        var arry = JSON.parse(m);
                        console.log(arry);
                        if (arry.state == 1) {
                            layer.msg("删除成功", {
                                icon: 1, time: 2000
                            });
                            setTimeout("location.href='news.html'", 1000);
                        } else {
                            layer.msg('删除失败', {icon: 5, time: 800});
                        }
                    }
                });

            }
            , btn2: function (index, layero) {
                num = 0;
                arr = "";
                layer.msg("已取消", {
                    icon: 2, time: 1000
                });
            }
        });
    }
});


$('#btns').click(function () {
    var tit = $('#keyowrds').val();
    if (tit.trim().length == 0) {
        location.href = "news.html";
    } else {
        location.href = "searchNews.html?stitle=" + tit;
    }

});