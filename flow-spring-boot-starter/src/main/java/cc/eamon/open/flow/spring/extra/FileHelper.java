package cc.eamon.open.flow.spring.extra;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileHelper {
    public static File getFileFromMultipartFile(MultipartFile file) {
        File extraJar = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            try {
                InputStream ins = null;
                ins = file.getInputStream();
                extraJar = new File(file.getOriginalFilename());
                OutputStream os = new FileOutputStream(extraJar);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                ins.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return extraJar;
    }

}
