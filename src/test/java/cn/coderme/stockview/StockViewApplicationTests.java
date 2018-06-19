package cn.coderme.stockview;

import cn.coderme.stockview.dataobtain.baseinfo.StockInfoCrawl;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.service.StockInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockViewApplicationTests {

	@Autowired
	private StockInfoCrawl stockInfoCrawl;
	@Autowired
	private StockInfoService stockInfoService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSelect() {
		StockInfo stockInfo = stockInfoService.selectOne(new EntityWrapper().eq("stockCode", "600004"));
		System.out.println(stockInfo);
	}
	@Test
	public void crawlStockInfo() {
		EntityWrapper<StockInfo> ew = new EntityWrapper<>();
		ew.setSqlSelect("id, stockCode");//只查询2个字段
		ew.eq("market", "sh").isNull("companyNameCn");
		List<StockInfo> stockInfos = stockInfoService.selectList(ew);
		for (StockInfo stockInfo : stockInfos) {
			try {
				stockInfoCrawl.crawlInfo(stockInfo.getStockCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
