package xyz.lycwood.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dl/")
public class DownloadContorller {
	/*@RequestMapping("index")
	public String index(){
		return "download";
	}*/
	
	
	@RequestMapping("download")
	@ResponseBody
	public Object download(HttpServletRequest request, HttpServletResponse response,String bookUrl) throws Exception{
		//System.out.println(bookUrl);
		String []  name = bookUrl.split("/");
		String filename = name[1];
		
		//String filename = "1.mobi";
		//System.out.println(filename);
		//设置文件MIME类型
		response.setContentType(request.getServletContext().getMimeType(filename));
		//System.out.println(filename+request.getServletContext().getMimeType(filename));
		//设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		//读取目标文件，通过response将目标文件写到客户端
		//获取目标文件的绝对路径
		String fullFileName = (request.getSession().getServletContext().getRealPath("")+"/"+bookUrl);
		//System.out.println(fullFileName);
		//读取文件
		InputStream in = new FileInputStream(fullFileName);
		OutputStream out = response.getOutputStream();
		//写文件
		int b;
		while((b=in.read())!= -1)
		{
			out.write(b);
		}
		
		in.close();
		out.close();
		return null;
	}
	

}
