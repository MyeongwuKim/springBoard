package me.mw.springboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    public String uploadFile(MultipartFile multipartFile,int titleNumber,HttpServletRequest request) throws IOException {
        String root_path = request.getSession().getServletContext().getRealPath("/resources/static/img");
        String filename= multipartFile.getOriginalFilename();
        int index=filename.indexOf('.');
        String head=filename.substring(0,index);
        String tail=filename.substring(index);
        head+="_"+titleNumber;
        filename=head+tail;
        File file = new File(root_path+File.separator+filename);
        multipartFile.transferTo(file);
        return filename;
    }

    public String modifyFile(MultipartFile multipartFile,int titleNumber,String prevFilename)
            throws IOException {
        String filePath=getClass().getClassLoader().getResource("static/img").toString();
        String newfilename= multipartFile.getOriginalFilename();
        int index=newfilename.indexOf('.');
        String head=newfilename.substring(0,index);
        String tail=newfilename.substring(index);
        head+="_"+titleNumber;
        newfilename=head+tail;

        if(newfilename.equals(prevFilename)){
            return prevFilename;
        }
        File file =new File(filePath,prevFilename);
        file.delete();
        file = new File(filePath,newfilename);
        multipartFile.transferTo(file);
        return newfilename;
    }

    public void deleteFile(String filename){
        String filePath=getClass().getClassLoader().getResource("static/img").toString();
        File file =new File(filePath,filename);
        file.delete();
    }
}
