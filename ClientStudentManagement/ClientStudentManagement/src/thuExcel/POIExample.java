/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thuExcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.hssf.record.formula.eval.EvaluationException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import sun.org.mozilla.javascript.internal.EvaluatorException;

/**
 * A simple POI example of opening an Excel spreadsheet
 * and writing its contents to the command line.
 * @author  Tony Sintes
 */
public class POIExample {

    public static void main(String[] args){
        try {
            boolean test = false;
            //InputStream input = POIExample.class.getResourceAsStream("D:\\Book1.xls");
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("D:\\Gameloft_Job_Application_Form.doc"));
            HSSFSheet sheet = wb.getSheet("Sheet1");
            Iterator rows1 = sheet.rowIterator();
            //rows1.next();
            while (rows1.hasNext()) {
                HSSFRow row = (HSSFRow) rows1.next();
                System.out.println("int i: " + row.getCell(5).toString() + " so cot: " + row.getLastCellNum());

//                for(int i=0;i<row.getLastCellNum())
                int i = Integer.parseInt(row.getCell(5).toString());
                i = Integer.parseInt(row.getCell(4).toString());
                i = Integer.parseInt(row.getCell(3).toString());
                i = Integer.parseInt(row.getCell(2).toString());
                if (i >= 0) {
                    String masv= row.getCell(0).toString();
                    
                }
            }


//            // Iterate over each row in the sheet
//            Iterator rows = sheet.rowIterator();
//            while (rows.hasNext()) {
//                HSSFRow row = (HSSFRow) rows.next();
//                System.out.println("Row #" + row.getRowNum());
//                // Iterate over each cell in the row and print out the cell's content
//                Iterator cells = row.cellIterator();
//                while (cells.hasNext()) {
//                    HSSFCell cell = (HSSFCell) cells.next();
//                    System.out.println("Cell #" + cell.getCellNum());
//                    switch (cell.getCellType()) {
//                        case HSSFCell.CELL_TYPE_NUMERIC:
//                            System.out.println(cell.getNumericCellValue());
//                            break;
//                        case HSSFCell.CELL_TYPE_STRING:
//                            System.out.println(cell.getStringCellValue());
//                            break;
//                        default:
//                            System.out.println("unsuported sell type");
//                            break;
//                    }
//                }
//
//            }

        } catch (Exception e) {
            //ex.printStackTrace();
            System.out.println("lỗi đọc file");
        }
    }
}
