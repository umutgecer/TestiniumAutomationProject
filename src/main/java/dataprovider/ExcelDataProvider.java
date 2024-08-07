package dataprovider;

import org.testng.annotations.DataProvider;
import utils.ExcelUtils;

public class ExcelDataProvider {
    @DataProvider(name = "sendTextSearchBox")
    public static Object[][] getTextData() {
        ExcelUtils excel = new ExcelUtils("src/test/resources/ExcelFile.xlsx", "Sheet1");

        String cellData1 = excel.getCellDataString(0, 0);
        String cellData2 = excel.getCellDataString(0, 1);

        return new Object[][]{{cellData1, cellData2}};
    }
}