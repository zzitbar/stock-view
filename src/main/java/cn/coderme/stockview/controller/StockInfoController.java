package cn.coderme.stockview.controller;


import cn.coderme.stockview.base.PageDataDto;
import cn.coderme.stockview.base.PageReqDto;
import cn.coderme.stockview.dto.StockInfoDto;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.service.StockInfoService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private StockInfoService stockInfoService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "stock/stockInfo";
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageDataDto<StockInfo> page(StockInfoDto dto) {
        return stockInfoService.getPage(dto);
    }
}

