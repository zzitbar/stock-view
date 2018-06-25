package cn.coderme.stockview.controller;


import cn.coderme.stockview.base.PageDataDto;
import cn.coderme.stockview.base.PageReqDto;
import cn.coderme.stockview.dto.Result;
import cn.coderme.stockview.dto.StockInfoDto;
import cn.coderme.stockview.entity.StockHistory;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.service.StockHistoryService;
import cn.coderme.stockview.service.StockInfoService;
import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Coderme
 * @since 2018-06-13
 */
@Controller
@RequestMapping("/stockInfo")
public class StockInfoController {

    private static final Logger logger = LoggerFactory.getLogger(StockInfoController.class);

    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private StockHistoryService stockHistoryService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "stock/stockInfo";
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageDataDto<StockInfo> page(StockInfoDto dto) {
        return stockInfoService.getPage(dto);
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String history(String stockCode, Model model) {
        model.addAttribute("stockCode", stockCode);
        return "stock/stockHistory";
    }

    /**
     * 获取股票历史交易记录
     * @param stockCode
     * @return
     */
    @RequestMapping(value = "/history/{stockCode}", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<StockHistory>> history(@PathVariable String stockCode) {
        Result<List<StockHistory>> result = new Result<List<StockHistory>>();
        try {
            result.setData(stockHistoryService.history(stockCode));
        } catch (Exception e) {
            logger.error("", e);
            result.setCode(Result.FAIL);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}

