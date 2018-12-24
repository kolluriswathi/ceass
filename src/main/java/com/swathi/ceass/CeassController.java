package com.swathi.ceass;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.swathi.assignment.client.api.DocumentsApi;
import com.swathi.assignment.client.model.CloudFile;
import com.swathi.assignment.client.model.CloudLink;

@RestController
public class CeassController {
	
	
	@Value("${auth}")
	private String authorization;
	
	@RequestMapping(value = "/getFoldersContents", method = RequestMethod.GET)
    public List<CloudFile> getFoldersContents(@RequestParam(value="path", required=true) String path) {
		
      DocumentsApi apiInstance = new DocumentsApi();
      List<CloudFile> result = null;
        //String authorization = "Element IZIPhDNhBn0wIEstnkpzHaYbHEaRdcsMmF+Rd0SF31o=, User oWMKkbCL+6Ki1adXq6NyCRgQA9i5sIrGQFYlSuJpq74=, Organization 3cad7973621fc9c247f7f1dbfb4bd271"; // String | The authorization tokens. The format for the header value is 'Element &lt;token&gt;, User &lt;user secret&gt;'
        try {
            result = apiInstance.getFoldersContents(authorization,path,false,false, (long)200, (long)1,null);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Exception when calling DocumentsApi#createFile");
            e.printStackTrace();
        }
        return result;
    }
    
    @RequestMapping(value = "/getFilesLinks", method = RequestMethod.GET)
    public CloudLink getFilesLinks(@RequestParam(value="path", required=true) String path) {
      DocumentsApi apiInstance = new DocumentsApi();
        //String authorization = "Element IZIPhDNhBn0wIEstnkpzHaYbHEaRdcsMmF+Rd0SF31o=, User oWMKkbCL+6Ki1adXq6NyCRgQA9i5sIrGQFYlSuJpq74=, Organization 3cad7973621fc9c247f7f1dbfb4bd271"; // String | The authorization tokens. The format for the header value is 'Element &lt;token&gt;, User &lt;user secret&gt;'
        CloudLink result=null;
        try {
        	result = apiInstance.getFilesLinks(authorization,path);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Exception when calling DocumentsApi#createFile");
            e.printStackTrace();
        }
        return result;
    }
    
    @RequestMapping(value = "/createFile" , method = RequestMethod.POST)
    public CloudFile createFile(@RequestParam(value="file", required=true)MultipartFile file,@RequestParam(value="path", required=true) String path) {
      DocumentsApi apiInstance = new DocumentsApi();
        //String authorization = "Element IZIPhDNhBn0wIEstnkpzHaYbHEaRdcsMmF+Rd0SF31o=, User oWMKkbCL+6Ki1adXq6NyCRgQA9i5sIrGQFYlSuJpq74=, Organization 3cad7973621fc9c247f7f1dbfb4bd271"; // String | The authorization tokens. The format for the header value is 'Element &lt;token&gt;, User &lt;user secret&gt;'
//        String path = "/sample/test.jpg"; // String | The full path to the file (e.g. /myDirectory/myFile.txt)
        // File | The file to be uploaded
        Long size = 789L; // Long | The size of the file in bytes (required only for SharePoint)
        List<String> tags = null; // List<String> | Tags associated with the file
        String description = null; // String | Description of the file
        Boolean overwrite = true; // Boolean | Overwrite the file if the file exists
        CloudFile result = null;
        try {
        	File file1 = convert(file);
            result = apiInstance.createFile(authorization, path, file1, size, tags, description, overwrite);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Exception when calling DocumentsApi#createFile");
            e.printStackTrace();
        }
        return result;
    }
    
    public File convert(MultipartFile file) throws IOException
    {    
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile(); 
        FileOutputStream fos = new FileOutputStream(convFile); 
        fos.write(file.getBytes());
        fos.close(); 
        return convFile;
    }
}
