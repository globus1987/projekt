package pl.edu.atena.utilities;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;

import javax.ejb.Stateless;

@Stateless
public class XLSCellValue {
	public String getCellStringValue(XSSFCell cell) {
		String stringvalue = "";
		CellType celltype = cell.getCellTypeEnum();
		switch (celltype) {
		case BOOLEAN:
			stringvalue = cell.getBooleanCellValue() ? "true" : "false";
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				stringvalue = cell.getDateCellValue().toInstant().toString();
			} else {
				stringvalue = String.valueOf(cell.getNumericCellValue());
			}
			break;
		case STRING:
			stringvalue = cell.getStringCellValue();
			break;
		default:
			break;
		}
		return stringvalue;
	}
}
