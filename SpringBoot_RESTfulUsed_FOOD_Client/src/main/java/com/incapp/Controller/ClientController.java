package com.incapp.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.webresources.FileResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.bean.Food;

@Controller
public class ClientController {
	
	RestTemplate rest = new RestTemplate();
	
	  
	String URI = "http://localhost:9843/";
	
	
	@RequestMapping("/")
	public String homePage() {
		return "index";
	}
	
	@ModelAttribute
	public void commonValue(Model model) {
		String API ="getIDs";
		
		List<Integer> Ids = rest.getForObject(URI+API, List.class);
		model.addAttribute("Ids", Ids);
		
	}
	
	
	
	@RequestMapping("/addFood")
	public String addFood(@ModelAttribute ("Food") Food f , @RequestPart ("image")  MultipartFile image , Model model ) {
		
		String API ="addFood";	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		LinkedMultiValueMap<String, Object> data = new  LinkedMultiValueMap<>();
		data.add("image",convert(image));
		data.add("Food", f);
		
		HttpEntity<LinkedMultiValueMap<String, Object> > entity = new HttpEntity<>(data , headers);
		
	    ResponseEntity<String> addFood = rest.postForEntity(URI+API, entity, String.class);
	    
	    String add = addFood.getBody();
	    
	    model.addAttribute("add", add);
	    
	    return "index";
	}
	
	//FileSystemResources use For Conversion 
	public static FileSystemResource convert(MultipartFile image) {
		File convFile=new File(image.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos=new FileOutputStream(convFile);
			fos.write(image.getBytes());
			fos.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return new FileSystemResource(convFile);
	}
	
	@RequestMapping("/getAllFood")
	public String getAllFood(Model model){
     
		String API = "getAllFood";
		List<Food> allFood = rest.getForObject(URI+API, List.class);
		
		model.addAttribute("allFood",allFood);
		
		return "getAllFood";
		
	}
	
	@RequestMapping("/getImage")
	public void getImage(int id , HttpServletResponse httpServletResponse ) {
		
     String API ="getImage/"+id;
     
    try {
    	 byte [] image = rest.getForObject(URI+API, byte[].class);
    	 httpServletResponse.getOutputStream().write(image);
		
	} catch (Exception e) {
		e.printStackTrace();
	}	
}
	@RequestMapping("/updateImage")
	public String updateImage(MultipartFile image , int id , Model model) {
		
		String API = "updateImage";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		LinkedMultiValueMap<String, Object> values = new LinkedMultiValueMap<>();
		
		values.add("image",convert(image));
		values.add("id",id);
		
		HttpEntity<LinkedMultiValueMap<String , Object>> requestEntity= new HttpEntity<>(values,headers);
		
		ResponseEntity<String> update = rest.exchange(URI+API, HttpMethod.PUT, requestEntity, String.class);
		
        String imageupdated = update.getBody();
        model.addAttribute("update",imageupdated);
        
        //add API
        
        API = "getAllFood";
		List<Food> allFood = rest.getForObject(URI+API, List.class);
		
		model.addAttribute("allFood",allFood);
		
		return "getAllFood";
        
	}
	
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

