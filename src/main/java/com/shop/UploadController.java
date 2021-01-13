//package com.shop;
//
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//
//import javax.persistence.criteria.Path;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.sun.tools.javac.util.StringUtils;
//
//@Controller
//public class UploadController {
//	
//	private final String UPLOAD_DIR = "./uploads/";
//
//    @GetMapping("/")
//    public String homepage() {
//        return "index";
//    }
//
//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
//
//        // check if file is empty
//        if (file.isEmpty()) {
//            attributes.addFlashAttribute("message", "Please select a file to upload.");
//            return "redirect:/";
//        }
//
//        // normalize the file path
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        // save the file on the local file system
//        try {
//            Path path = Paths.get(UPLOAD_DIR + fileName);
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // return success response
//        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
//
//        return "redirect:/";
//    }
//
//    
//}
