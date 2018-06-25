package cn.coderme.stockview.service;

import cn.coderme.stockview.dto.hchart.ChartDto;

/**
 * Created By Administrator
 * Date:2018/6/20
 * Time:11:26
 */
public interface StatisticService {

    /**
     * 涨跌家数统计
     * @param date
     * @return
     */
    ChartDto increaseRange();
}
