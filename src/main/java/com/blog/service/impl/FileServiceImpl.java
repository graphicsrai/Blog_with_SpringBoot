package com.blog.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.service.FileService;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		//File NAme
		String name=file.getOriginalFilename();
		// abc.png
		
		//  name generation file
		String rendomID= UUID.randomUUID().toString();
		String fileName1=rendomID.concat(name.substring(name.lastIndexOf(".")));
		
		// fill path
		String filePath = path + File.separator+fileName1;
		
		//create folder if not created 
		File f=new File(path);
		if(!f.exists())
		{
			f.mkdir();
			
		}
		 //file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+File.separator+fileName;
		InputStream is=new FileInputStream(fullPath);
		
		return is;
	}

}