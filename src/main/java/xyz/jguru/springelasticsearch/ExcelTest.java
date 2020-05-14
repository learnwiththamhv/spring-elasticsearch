package xyz.jguru.springelasticsearch;

import org.springframework.stereotype.Component;
import xyz.jguru.springelasticsearch.utils.excel.ExcelPOIHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ExcelTest {

//    private ExcelPOIHelper excelPOIHelper;
//    private static String FILE_NAME = "sc_karaoke.xlsx";
//    private String fileLocation;

    public static void main(String[] args) throws IOException {

        ExcelPOIHelper excelPOIHelper;
        String FILE_NAME = "sc_karaoke.xlsx";
        String fileLocation;


        File currDir = new File("C:\\temp\\");
        String path = currDir.getAbsolutePath();
        //fileLocation = path.substring(0, path.length() - 1) + FILE_NAME;
        fileLocation = path + "\\" + FILE_NAME;

        excelPOIHelper = new ExcelPOIHelper();
        excelPOIHelper.writeExcel();

        Map<Integer, List<String>> data
                = excelPOIHelper.readExcel(fileLocation);

        for (Map.Entry<Integer, List<String>> entry : data.entrySet()) {
            for(String s : entry.getValue()) {
                System.out.println(entry.getKey() + "/" + s);
            }
        }
    }

//    public void readFile() throws IOException {
//
//        File currDir = new File("C:/temp/");
//        String path = currDir.getAbsolutePath();
//        fileLocation = path.substring(0, path.length() - 1) + FILE_NAME;
//
//        excelPOIHelper = new ExcelPOIHelper();
//        excelPOIHelper.writeExcel();
//
//        Map<Integer, List<String>> data
//                = excelPOIHelper.readExcel(fileLocation);
//
//        for (Map.Entry<Integer, List<String>> entry : data.entrySet()) {
//            for(String s : entry.getValue()) {
//                System.out.println(entry.getKey() + "/" + s);
//            }
//        }
//
//    }
}