package cn.coderme.stockview.job;

import cn.coderme.stockview.Constants;
import cn.coderme.stockview.base.StockApiProperties;
import cn.coderme.stockview.service.StockHistoryService;
import cn.coderme.stockview.service.StockInfoService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 调用网易股票接口 导入记录交易csv
 * Created By Administrator
 * Date:2018/6/25
 * Time:10:32
 */
@Service
public class NeteaseImportHisCsvJob implements BaseJob {

    @Autowired
    private StockApiProperties stockApiProperties;
    @Autowired
    private StockHistoryService stockHistoryService;
    @Autowired
    private StockInfoService stockInfoService;

    /**
     * 导入记录交易csv
     * 每周一至周五凌晨2点 0 0 2 ? * MON-FRI
     */
    public void importCsv() {
        for (Constants.STOCK_TYPE stockType : Constants.STOCK_TYPE.values()) {
            File file = new File(stockApiProperties.getNeteaseHistoryCsvFilePath() + stockType.getValue());
//            String fileName = dealCsvFile(file, stockType.getValue());
            String fileName = writeFile(file, stockType.getValue());
//            if (file.isDirectory()) {
//                String[] filelist = file.list();
//                for (int i = 0; i < filelist.length; i++) {
//                    String filePath = file.getAbsolutePath() + "\\" + filelist[i];
//                    stockHistoryService.importCsv(filePath, stockType.getValue());
//                    new File(filePath).delete();
//                }
//            }
            if (StringUtils.hasText(fileName)) {
                stockHistoryService.importCsv(fileName, stockType.getValue());
                stockInfoService.updateLastHistoryDate(stockType.getValue());
            }
        }
    }

    public static void main(String[] args) {
        new NeteaseImportHisCsvJob().writeFile(new File("E:\\stock-view\\history-csv\\2"), 2);
//        new NeteaseImportHisCsvJob().dealCsvFile(new File("E:\\stock-view\\history-csv\\1\\test"), 1);
//        try {
//            List<String> lines = Files.readAllLines(Paths.get("E:\\stock-view\\history-csv\\1\\test\\002188.csv"),
//                    Charset.forName("gb2312"));
//            for (String line : lines) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private String dealCsvFile(File file, Integer stockType) {
        FileInputStream in = null;
        FileOutputStream out = null;
        long start = System.currentTimeMillis();
        String fileName = stockApiProperties.getNeteaseHistoryCsvFilePath() + "/" + stockType + "-all.csv";
        try {
            if (file.isDirectory()) {
                // 获取源文件和目标文件的输入输出流
                out = new FileOutputStream(fileName);
                FileChannel fcOut = out.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                for (File file1 : file.listFiles()) {
//                    in = new FileInputStream(file1);
                    // 获取输入输出通道
//                    FileChannel fcIn = in.getChannel();
                    List<String> lines = Files.readAllLines(Paths.get(file1.getPath()),
                            Charset.forName("gb2312"));
                    for (String line : lines) {
                        if (!line.startsWith("日期")) {
                            buffer.clear();
                            line += "\r\n";
                            buffer.put(line.getBytes());
                            buffer.flip();
                            fcOut.write(buffer);
                        }
                        System.out.println(line);
                    }
                    file1.delete();
//                    while (true) {
//                        // clear方法重设缓冲区，使它可以接受读入的数据
//                        buffer.clear();
//                        // 从输入通道中将数据读到缓冲区
//                        int r = fcIn.read(buffer);
//                        if (r == -1) {
//                            break;
//                        }
//                        // flip方法让缓冲区可以将新读入的数据写入另一个通道
//                        buffer.flip();
//                        fcOut.write(buffer);
//                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null && out != null) {
                try {
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println("nio: " + (end1 - start));
        return fileName;
    }

    private String writeFile(File file, Integer stockType) {
        String fileName = stockApiProperties.getNeteaseHistoryCsvFilePath() + stockType + "-all.csv";
//        String fileName = "E:/stock-view/history-csv/2-all.csv";
        String line = "";
//        File file = new File("E:/stock-view/history-csv/2");
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    String filePath = file.getAbsolutePath() + "/" + filelist[i];
                    InputStreamReader fReader = new InputStreamReader(new FileInputStream(filePath), "gb2312");
                    BufferedReader in = new BufferedReader(fReader);
                    line = in.readLine();
                    while (line != null) {
                        if (!line.startsWith("日期")) {
                            out.write(line);
                            out.newLine();
                        }
                        line = in.readLine();
                    }
                    in.close();
                    fReader.close();
                    new File(filePath).delete();
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            fileName = null;
        }
        return fileName;
    }

    // 将 UTF-8 编码的字符串转换为 GB2312 编码格式：
    public static String utf8Togb2312(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '+':
                    sb.append(' ');
                    break;
                case '%':
                    try {
                        sb.append((char) Integer.parseInt(
                                str.substring(i + 1, i + 3), 16));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException();
                    }
                    i += 2;
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        String result = sb.toString();
        String res = null;
        try {
            byte[] inputBytes = result.getBytes("8859_1");
            res = new String(inputBytes, "UTF-8");
        } catch (Exception e) {
        }
        return res;
    }

    // 将 GB2312 编码格式的字符串转换为 UTF-8 格式的字符串：
    public static String gb2312ToUtf8(String str) {
        String urlEncode = "";
        try {
            urlEncode = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlEncode;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        importCsv();
    }
}