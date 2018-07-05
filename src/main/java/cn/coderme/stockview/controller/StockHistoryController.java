package cn.coderme.stockview.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 历史交易数据 前端控制器
 * </p>
 *
 * @author Coderme
 * @since 2018-06-21
 */
@Controller
@RequestMapping("/stockHistory")
public class StockHistoryController {

//    @Autowired
//    private NeteaseJob neteaseJob;
//
//    /**
//     * 下载csv
//     */
//    @RequestMapping("/downloadCsvFromNetease")
//    @ResponseBody
//    public String downloadCsvFromNetease() {
//        neteaseJob.downloadCsvFromNetease();
//        return "success";
//    }
//
//    /**
//     * csv 导入数据库
//     */
//    @RequestMapping("/importCsv")
//    @ResponseBody
//    public String importCsv() {
//        neteaseJob.importCsv();
//        return "success";
//    }
}

