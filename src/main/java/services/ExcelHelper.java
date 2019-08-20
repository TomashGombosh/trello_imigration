package services;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ExcelHelper {
    private static Logger log = Logger.getLogger("log4j.properties");

    private HashMap<String, String> excelReader(String fileName) {
        HashMap<String, String> map = new HashMap<String, String>();
        try {

            FileInputStream excelFile = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    log.info("currentCell.getColumnIndex(): " + currentCell.getColumnIndex());
                    if (currentCell.getColumnIndex() == 0) {
                        map.put("id", currentCell.getStringCellValue());
                        log.info("Id value in hash map " + currentCell.getStringCellValue());
                    } else if (currentCell.getColumnIndex() == 1) {
                        map.put("title", currentCell.getStringCellValue());
                        log.info("title value in hash map " + currentCell.getStringCellValue());
                    } else {
                        map.put("priority", currentCell.getStringCellValue());
                        log.info("Priority value in hash map " + currentCell.getStringCellValue());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(map);
        return map;
    }

    public ArrayList<String> getColumn(String fileName, int column) {
        ArrayList<String> columnData = new ArrayList<>();
        try {
            FileInputStream excelFile = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet dtSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = dtSheet.iterator();


            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    log.info(currentCell.toString());
                    if (currentCell.getColumnIndex() == column && !currentCell.getStringCellValue().equals("") && !currentCell.getStringCellValue().equals(" ")) {
                        columnData.add(currentCell.getStringCellValue());
                        log.info("Add to the array " + currentCell.getStringCellValue());

                    }
                }
            }
        } catch (FileNotFoundException e) {
            log.info("File Not Found Exception");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Skip the first row of the Excel because it is a data provider for users
        columnData.remove(0);
        log.info(columnData);
        return columnData;
    }

    private void excelWriter(Object[][] object, String fileName) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Datatypes in Java");

        int rowNum = 0;
        log.info("Creating excel");

        for (Object[] datatype : object) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("Done");
    }


    public HashMap<String, String> readFromExcel(String fileName) {
        return excelReader(fileName);
    }

    public void writeToExcel(Object[][] objects, String fileName) {
        excelWriter(objects, fileName);
    }
}
