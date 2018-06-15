package cn.coderme.stockview;

import cn.coderme.stockview.dataobtain.baseinfo.StockInfoCrawl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockViewApplicationTests {

	@Autowired
	private StockInfoCrawl stockInfoCrawl;

	@Test
	public void contextLoads() {
	}

//	@Test
	public void crawlStockInfo() {
		stockInfoCrawl.crawlInfo(null);
	}
}
