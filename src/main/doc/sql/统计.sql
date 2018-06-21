# 按省统计上市公司数量
SELECT
  t.province AS province,
  COUNT(1)
FROM stock_info t
GROUP BY t.province
ORDER BY COUNT(1) DESC;

# 涨跌家数统计
SELECT CASE WHEN sr.auctionPrice=0 AND sr.bidPrice>0 THEN '01*涨停'
       WHEN sr.increaseRate>=0.07 AND sr.auctionPrice!=0 THEN '02*> 7%'
       WHEN sr.increaseRate>=0.05 AND sr.increaseRate<0.07 THEN '03*5%~7%'
       WHEN sr.increaseRate>=0.03 AND sr.increaseRate<0.05 THEN '04*3%~5%'
       WHEN sr.increaseRate>=0 AND sr.increaseRate<0.03 THEN '05*0%~3%'
       WHEN sr.increaseRate>=-0.03 AND sr.increaseRate<0 THEN '06*-3%~0%'
       WHEN sr.increaseRate>=-0.05 AND sr.increaseRate<-0.03 THEN '07*-3%~-5%'
       WHEN sr.increaseRate>=-0.07 AND sr.increaseRate<0.05 THEN '08*-7%~-5%'
       WHEN sr.increaseRate<-0.07 AND sr.bidPrice!=0 THEN '09*> -7%'
       WHEN sr.bidPrice=0 AND sr.auctionPrice>0 THEN '10*跌停'
       ELSE '11*停牌' END AS rate,COUNT(1) AS rs FROM stock_realtime sr GROUP BY rate ORDER BY rate