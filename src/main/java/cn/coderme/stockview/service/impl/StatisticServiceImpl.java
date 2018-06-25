package cn.coderme.stockview.service.impl;

import cn.coderme.stockview.Constants;
import cn.coderme.stockview.dto.hchart.ChartDataDto;
import cn.coderme.stockview.dto.hchart.ChartDto;
import cn.coderme.stockview.dto.hchart.CountDto;
import cn.coderme.stockview.mapper.StockRealtimeMapper;
import cn.coderme.stockview.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Administrator
 * Date:2018/6/20
 * Time:11:26
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    @Qualifier(value = "stockRealtimeMapper")
    private StockRealtimeMapper stockRealtimeMapper;

    @Override
    public ChartDto increaseRange() {
        ChartDto chartDto = new ChartDto();
        chartDto.setChartTitle("涨跌家数统计");
        chartDto.setyTitle("只");
        chartDto.setRenderTo("increaseRange");
        chartDto.setType(Constants.CHART_TYPE.COLUMN.getValue());
        List<CountDto> countDtos =  stockRealtimeMapper.increaseRange();

        dealCountDto(chartDto, countDtos, "数量");
        return chartDto;
    }

    private void dealCountDto(ChartDto chartDto, List<CountDto> countDtos, String dataName) {
        chartDto.setxAxis(new String[countDtos.size()]);

        List<ChartDataDto> chartDataDtos = new ArrayList<ChartDataDto>();
        ChartDataDto dataDto = new ChartDataDto();
        dataDto.setName(dataName);
        dataDto.setData(new Integer[countDtos.size()]);
        for (int i = 0; i < countDtos.size(); i++) {
            dataDto.getData()[i] = countDtos.get(i).getCount();
            chartDto.getxAxis()[i] = countDtos.get(i).getItem();
        }
        chartDataDtos.add(dataDto);
        chartDto.setChartDataDtos(chartDataDtos);
    }
}