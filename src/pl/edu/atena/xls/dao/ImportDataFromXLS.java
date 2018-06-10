package pl.edu.atena.xls.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import pl.edu.atena.rest.AttributeBean;
import pl.edu.atena.rest.ObjectBean;
import pl.edu.atena.rest.RelationBean;
import pl.edu.atena.utilities.InputFileBean;
import pl.edu.atena.utilities.XLSCellValue;

public class ImportDataFromXLS {
	private static int firstDataRow = 4;
	private static int firstAttributeColumn = 5;
	

	public ArrayList<ObjectBean> importObjects() throws InvalidFormatException, IOException {
		ArrayList<ObjectBean> objectList = new ArrayList<>();
		InputFileBean inputFile = new InputFileBean();
		XSSFSheet sheet = inputFile.getSheet("Object");
		for (int i = firstDataRow; i <= sheet.getLastRowNum(); i++) {
			XSSFRow actualRow = sheet.getRow(i);
			ObjectBean object = new ObjectBean();
			object.setId(actualRow.getCell(0).getStringCellValue());
			object.setName(actualRow.getCell(2).getStringCellValue());
			object.setAttributeList(this.importAttributes(actualRow));
			objectList.add(object);
		}
		return objectList;
	}
	public ArrayList<ObjectBean> importObjects(String filename) throws InvalidFormatException, IOException {
		ArrayList<ObjectBean> objectList = new ArrayList<>();
		InputFileBean inputFile = new InputFileBean(filename);
		XSSFSheet sheet = inputFile.getSheet("Object");
		for (int i = firstDataRow; i <= sheet.getLastRowNum(); i++) {
			XSSFRow actualRow = sheet.getRow(i);
			ObjectBean object = new ObjectBean();
			object.setId(actualRow.getCell(0).getStringCellValue());
			object.setName(actualRow.getCell(2).getStringCellValue());
			object.setAttributeList(this.importAttributes(actualRow));
			objectList.add(object);
		}
		return objectList;
	}

	public ArrayList<AttributeBean> importAttributes(XSSFRow objectRow) {
		ArrayList<AttributeBean> attributeList = new ArrayList<AttributeBean>();
		for (int i = firstAttributeColumn; i < objectRow.getLastCellNum(); i++) {
			XSSFCell actualcell = objectRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			if (actualcell.getCellTypeEnum() != CellType.BLANK) {
				AttributeBean attribute = new AttributeBean();
				
				attribute.setSymbol(objectRow.getSheet().getRow(1).getCell(i).getStringCellValue());
				XLSCellValue cellconvert = new XLSCellValue();
				attribute.setValue(cellconvert.getCellStringValue(actualcell));
				attributeList.add(attribute);
			}
		}

		return attributeList;
	}
	public ArrayList<RelationBean> importRelations() throws InvalidFormatException, IOException{
		ArrayList<RelationBean> relationList = new ArrayList<>();
		InputFileBean inputFile = new InputFileBean();
		XSSFSheet sheet = inputFile.getSheet("Testcases");
		for (int i = 2; i <= sheet.getLastRowNum(); i++) {
			XSSFRow actualRow = sheet.getRow(i);
			
			XLSCellValue cellconvert = new XLSCellValue();
			
			String target = cellconvert.getCellStringValue(actualRow.getCell(2));
			String[] lista = target.split(";");
			for (String actualtarget:lista) {
				RelationBean relation = new RelationBean();
				relation.setSourceId(cellconvert.getCellStringValue(actualRow.getCell(1)));
				relation.setTargetId(actualtarget);
				relationList.add(relation);
			}
			
			
		}
		
		return relationList;
	}
	public ArrayList<RelationBean> importRelations(String filename) throws InvalidFormatException, IOException{
		ArrayList<RelationBean> relationList = new ArrayList<>();
		InputFileBean inputFile = new InputFileBean(filename);
		XSSFSheet sheet = inputFile.getSheet("Testcases");
		for (int i = 2; i <= sheet.getLastRowNum(); i++) {
			XSSFRow actualRow = sheet.getRow(i);
			
			XLSCellValue cellconvert = new XLSCellValue();
			
			String target = cellconvert.getCellStringValue(actualRow.getCell(2));
			String[] lista = target.split(";");
			for (String actualtarget:lista) {
				RelationBean relation = new RelationBean();
				relation.setSourceId(cellconvert.getCellStringValue(actualRow.getCell(1)));
				relation.setTargetId(actualtarget);
				relationList.add(relation);
			}
			
			
		}
		
		return relationList;
	}
}
