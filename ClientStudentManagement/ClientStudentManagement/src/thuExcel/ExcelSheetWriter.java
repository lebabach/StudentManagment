/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thuExcel;

import java.io.File;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ExcelSheetWriter {

    private void writeExcelFile(String fileName) {
        FileOutputStream fileOutputStream = null;
        HSSFWorkbook sampleWorkbook = null;
        HSSFSheet sampleDataSheet = null;
        try {
            sampleWorkbook = new HSSFWorkbook();
            sampleDataSheet = sampleWorkbook.createSheet("SampleDataSheet1");

            HSSFRow headerRow = sampleDataSheet.createRow(0);
            HSSFRow dataRow1 = sampleDataSheet.createRow(1);

            HSSFCellStyle cellStyle = setHeaderStyle(sampleWorkbook);

            HSSFCell firstHeaderCell = headerRow.createCell(0);
            firstHeaderCell.setCellStyle(cellStyle);
            firstHeaderCell.setCellValue(new HSSFRichTextString("Employer Name"));

            HSSFCell secondHeaderCell = headerRow.createCell(1);
            secondHeaderCell.setCellStyle(cellStyle);
            secondHeaderCell.setCellValue(new HSSFRichTextString("Designation"));

            HSSFCell thirdHeaderCell = headerRow.createCell(2);
            thirdHeaderCell.setCellStyle(cellStyle);
            thirdHeaderCell.setCellValue(new HSSFRichTextString("Country"));

            dataRow1.createCell(0).setCellValue(new HSSFRichTextString("Gift Sam"));
            dataRow1.createCell(1).setCellValue("Software Engineer");
            dataRow1.createCell(2).setCellValue("Malaysia");

            fileOutputStream = new FileOutputStream(fileName);
            sampleWorkbook.write(fileOutputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private HSSFCellStyle setHeaderStyle(HSSFWorkbook sampleWorkBook) {
        HSSFFont font = sampleWorkBook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setColor(IndexedColors.PLUM.getIndex());
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle cellStyle = sampleWorkBook.createCellStyle();
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static void main(String[] args) {
        new ExcelSheetWriter().writeExcelFile("d:/sampleexcel.xls");

    }
}
