package cn.coderme.stockview.controller;

import cn.coderme.stockview.dataobtain.baseinfo.dto.StockInfoCrawlDto;
import cn.coderme.stockview.dto.Result;
import cn.coderme.stockview.dto.hchart.ChartDto;
import cn.coderme.stockview.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统计
 * Created By Administrator
 * Date:2018/6/20
 * Time:10:56
 */
@Controller
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @RequestMapping(value = "/increaseRange", method = RequestMethod.GET)
    public String increaseRange() {
        statisticService.increaseRange();
        return "statistic/increaseRange";
    }

    /**
     * 涨跌家数统计
     * @param date
     * @return
     */
    @RequestMapping(value = "/increaseRange", method = RequestMethod.POST)
    @ResponseBody
    public Result<ChartDto> increaseRange(String date) {
        Result<ChartDto> result = new Result<ChartDto>();
        try {
            result.setData(statisticService.increaseRange());
        } catch (Exception e) {

        }
        return result;
    }
}