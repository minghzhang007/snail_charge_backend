<!DOCTYPE>
<html>
<head>
    <title>
        freemark
    </title>
    <#include "../component/layout.ftl">
    <@css></@css>

</head>
<body>
<h1>Hello ${name} from
    resource freemark!</h1>

Date: ${time?date}
<br>
Time: ${time?time}
<br>
Message: ${message}

<#if name='倾国倾城'>
<h2>你是【倾国倾城】</h2>
</#if>
<#if (price >=90)>
<h2>价格>=90 优秀</h2>
<#elseif (price >=80)>
<h2>价格>=80 良好</h2>
<#elseif (price >= 60)>
<h2>价格>=70 合格</h2>
<#else  >
<h2>不合格</h2>
</#if>

<div>
    <table>
        <thead>
        <th>用户名称 </th>
        <th> 昵称</th>
        <th>订单号 </th>
        <th> 时长</th>
        <th> 金额</th>
        <th>交易状态 </th>
        <th> 支付方式</th>
        </thead>
        <tbody>
        <#list list as trade>
        <tr>
            <td>${trade.userName}</td>
            <td>${trade.nickName}</td>
            <td>${trade.tradeNo}</td>
            <td>${trade.days}</td>
            <td>${trade.money}</td>
            <td>${trade.tradeStatus}</td>
            <td>${trade.payMethod}</td>
        </tr>
        </#list>
        </tbody>

    </table>
    <#include "../component/foot.ftl">
</div>
<@js></@js>
</body>
</html>