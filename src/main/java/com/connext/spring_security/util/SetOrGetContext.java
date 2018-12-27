package com.connext.spring_security.util;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @Author: Marcus
 * @Date: 2018/12/25 15:02
 * @Version 1.0
 */
@Component
public class SetOrGetContext {
    private static final String PATH = "C:\\Users\\mengchao.lv\\Desktop\\spring_security\\files\\";
    private static final String FILE_SUBFIX = ".txt";

    public String readFile(String filename) throws IOException {
        InputStream is = new FileInputStream(PATH + filename + FILE_SUBFIX);
        byte[] b = new byte[is.available()];
        is.read(b);
        return new String(b);
    }

    public void writeFile(String filename, String context) throws IOException {
        File file = new File(PATH + filename + FILE_SUBFIX);
        OutputStream fos = new FileOutputStream(file);
        fos.write(context.getBytes());
        fos.close();
    }
    public String giveMeFileName(){
        return String.valueOf(System.currentTimeMillis());
    }

}