package com.company;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) throws  FileNotFoundException, IOException {
        // write your code here
        int startYear = 2017, numberOfYears = 1;
        for (int i = 0; i < numberOfYears; i++, startYear++) {
            System.out.println("year : " + startYear);
            for (int j = 0; j < 12; j++) {
                System.out.println("month : " + (j + 1));

                int numberOfDays = (new GregorianCalendar(startYear, j, 1)).getActualMaximum(Calendar.DAY_OF_MONTH);

                for (int k = 0; k < numberOfDays; k++) {
                    System.out.println("day : " + (k + 1));

                    FileOutputStream fos = new FileOutputStream(k + ".zip");
                    ZipOutputStream zos = new ZipOutputStream(fos);

                    File abc = new File(String.format( "%s//%s//%s", startYear, j, k));
                    for (File file : abc.listFiles()) {
                        addToZipFile(file.getAbsolutePath(), zos);
                    }

                    zos.close();
                    fos.close();
                }
            }
        }
    }

    public static void addToZipFile(String fileName, ZipOutputStream zos) throws IOException {

        System.out.println("Writing '" + fileName + "' to zip file");

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }
}
