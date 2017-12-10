package angel.util;

import angel.model.bo.OrderBo;
import angel.model.bo.OrderStyleBo;
import angel.model.vto.CompleteOrderVto;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by 磷啊 on 2017/12/10.
 */
public class ExcelUtil {
    private static final String[] orderTitle = new String[] {
            "单号","制单人","工作日期","款号","款式",
            "摘要","人数","数量","计价类型","工价",
            "加价","总价","是否验收","验收人","备注"
    };

    public static void exportOrderExcel(List<CompleteOrderVto> orders, OutputStream outputStream) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计");
        int index = 1;
        HSSFRow title =  sheet.createRow(0);
        for (int i =0;i < orderTitle.length;i++) {
            HSSFCell cell = title.createCell(i);
            cell.setCellValue(orderTitle[i]);
        }
        for (CompleteOrderVto order : orders) {
            HSSFRow row = sheet.createRow(index);
            Class clazz = order.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0,len = fields.length; i < len; i++) {
                String name = fields[i].getName();
                name = name.substring(0, 1).toUpperCase() +
                        name.substring(1);
                String type = fields[i].getGenericType().toString();
                if ("class java.lang.String".equals(type)) {
                    getStringTypeValue(clazz,order,name,row,i);
                }else if ("class java.lang.Integer".equals(type)) {
                    getIntegerTypeValue(clazz,order,name,row,i);
                }else if ("class java.lang.Double".equals(type)) {
                    getDoubleTypeValue(clazz,order,name,row,i);
                }
            }
            index++;
        }
        workbook.write(outputStream);
    }


    private static void getStringTypeValue(Class clazz, Object obj,String name, HSSFRow row, int index) {
        Method m = null;
        try {
            m = clazz.getMethod("get" + name);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (null == m) {
            throw new NoSuchElementException();
        }
        try {
            String value = (String) m.invoke(obj);
            HSSFCell cell = row.createCell(index);
            if (null == value) {
                cell.setCellValue("");
            }else {
                cell.setCellValue(value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void getIntegerTypeValue(Class clazz, Object obj, String name, HSSFRow row, int index) {
        Method m = null;
        try {
            m = clazz.getMethod("get" + name);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (null == m) {
            throw new NoSuchElementException();
        }
        try {
            Integer value = (Integer) m.invoke(obj);
            HSSFCell cell = row.createCell(index);
            if (null == value) {
                cell.setCellValue("");
            }else {
                cell.setCellValue(value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void getDoubleTypeValue(Class clazz,Object obj, String name, HSSFRow row, int index) {
        Method m = null;
        try {
            m = clazz.getMethod("get" + name);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (null == m) {
            throw new NoSuchElementException();
        }
        try {
            Double value = (Double) m.invoke(obj);
            HSSFCell cell = row.createCell(index);
            if (null == value) {
                cell.setCellValue("");
            }else {
                cell.setCellValue(value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
