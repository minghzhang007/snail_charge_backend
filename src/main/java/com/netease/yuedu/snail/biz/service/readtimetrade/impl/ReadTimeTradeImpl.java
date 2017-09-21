package com.netease.yuedu.snail.biz.service.readtimetrade.impl;

import com.google.common.collect.Lists;
import com.netease.yuedu.snail.biz.dao.snail.ReadTimeTradeMapper;
import com.netease.yuedu.snail.biz.model.constants.TradeStatusEnum;
import com.netease.yuedu.snail.biz.model.entity.ReadTimeTrade;
import com.netease.yuedu.snail.biz.model.queryObject.ReadTimeTradeQueryObject;
import com.netease.yuedu.snail.biz.model.vo.ReadTimeTradeVO;
import com.netease.yuedu.snail.biz.service.readtimetrade.ReadTimeTradeService;
import com.netease.yuedu.snail.common.excel.AbstractExportExcelTemplate;
import com.netease.yuedu.snail.common.page.AbstractPageTemplate;
import com.netease.yuedu.snail.common.page.PageList;
import com.netease.yuedu.snail.common.page.Paginator;
import com.netease.yuedu.snail.common.utils.DateUtil;
import com.netease.yuedu.snail.common.utils.TimeTokenUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ReadTimeTradeImpl implements ReadTimeTradeService {

    @Resource
    private ReadTimeTradeMapper tradeMapper;


    @Override
    public PageList<ReadTimeTradeVO> queryByPage(final ReadTimeTradeQueryObject queryObject) {

        PageList<ReadTimeTradeVO> itemsByPage = new AbstractPageTemplate<ReadTimeTradeVO>() {
            @Override
            protected List<ReadTimeTradeVO> queryItems() {
                return tradeMapper.queryReadTimeRecords(queryObject);
            }
        }.getItemsByPage(queryObject);
        return itemsByPage;
    }

    @Override
    public void export(ReadTimeTradeQueryObject queryObject, HttpServletResponse response) {
        List<ReadTimeTradeVO> readTimeTrades = tradeMapper.queryReadTimeRecords(queryObject);
        final String[] rowNames = {"订单号", "流水号", "用户ID(蜗牛ID)", "昵称", "金额(元)", "时长(天)", "付款方式", "状态", "交易时间"};
        final ArrayList<Object[]> dataList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(readTimeTrades)) {
            for (ReadTimeTradeVO readTimeTrade : readTimeTrades) {
                Object[] columnsOfOneRow = new Object[rowNames.length];
                columnsOfOneRow[0] = readTimeTrade.getTradeNo();
                columnsOfOneRow[1] = readTimeTrade.getTradeId();
                columnsOfOneRow[2] = readTimeTrade.getUserId() + "(" + readTimeTrade.getUserName() + ")";
                columnsOfOneRow[3] = readTimeTrade.getNickName();
                columnsOfOneRow[4] = readTimeTrade.getMoney();
                columnsOfOneRow[5] = readTimeTrade.getDays();
                columnsOfOneRow[6] = readTimeTrade.getPayMethod();
                columnsOfOneRow[7] = TradeStatusEnum.of(readTimeTrade.getTradeStatus()).getDesc();
                columnsOfOneRow[8] = DateUtil.toString(readTimeTrade.getCreateTime());
                dataList.add(columnsOfOneRow);
            }
        }
        new AbstractExportExcelTemplate(response) {
            @Override
            protected String getFileName() {
                return "购买记录" + TimeTokenUtil.createTimeToken();
            }

            @Override
            protected List<Object[]> getDataList() {
                return dataList;
            }

            @Override
            protected String[] getRowNames() {
                return rowNames;
            }

            @Override
            protected String getTitle() {
                return "阅读时长购买记录";
            }
        }.exportExcel();
    }
}
