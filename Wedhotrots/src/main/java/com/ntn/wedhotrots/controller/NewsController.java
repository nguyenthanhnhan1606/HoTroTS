package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.pojo.News;
import com.ntn.wedhotrots.service.NewsService;
import org.apache.http.conn.util.PublicSuffixList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NewsController {
    @Autowired
    private NewsService newsSer;

    @GetMapping("/news")
    @CrossOrigin
    public List<News> getAlls(){
        return newsSer.getAlls();
    }

    @GetMapping("/newscxd")
    @CrossOrigin
    public List<News> getNewsCxd(){
        return newsSer.getNewsCXD();
    }

    @GetMapping("/newsdh")
    @CrossOrigin
    public List<News> getNewsDH(){
        return newsSer.getNewsDH();
    }

    @GetMapping("/newspagedh")
    @CrossOrigin
    public double getPageDH(){
        double count = newsSer.getNewsDH().size();
        return Math.ceil(count/2);
    }

    @GetMapping("/newssdh")
    @CrossOrigin
    public List<News> getNewsSDH(@RequestParam Map<String, String> parmas){
        return newsSer.getNewsSDH(parmas);
    }

    @GetMapping("/newspagesdh")
    @CrossOrigin
    public double getPageSDH(){
        double count = newsSer.getNewsSDH(null).size();
        return Math.ceil(count/2);
    }

    @GetMapping("/newstt")
    @CrossOrigin
    public List<News> getNewTT(){
        return newsSer.getNewsTT();
    }

    @GetMapping("/newspagett")
    @CrossOrigin
    public double getPageTT(){
        double count = newsSer.getNewsTT().size();
        return Math.ceil(count/2);
    }

    @GetMapping("/newstx")
    @CrossOrigin
    public List<News> getNewsTX(){
        return newsSer.getNewsTX();
    }

    @GetMapping("/newspagetx")
    @CrossOrigin
    public double getPageTX(){
        double count = newsSer.getNewsTX().size();
        return Math.ceil(count/2);
    }

    @GetMapping("/news/{id}")
    @CrossOrigin
    public Optional<News> getNewsById(@PathVariable int id){
        return newsSer.getNewsById(id);
    }

    @PostMapping("/news")
    @CrossOrigin
    public boolean addNews(@RequestParam Map<String,String> params, MultipartFile file){
        return newsSer.addNews(params,file);
    }

    @PutMapping("/news/update/{id}")
    @CrossOrigin
    public boolean updateNews(@PathVariable int id, @RequestParam Map<String, String> params, @RequestParam(required = false)MultipartFile file){
        return newsSer.updateNews(id,params,file);
    }

    @PutMapping("/news/check/{id}")
    @CrossOrigin
    public boolean duyetNews(@PathVariable int id, @RequestParam String trangthai){
        return newsSer.duyetNews(id,trangthai);
    }


    @PutMapping("/news/recyclebin/{id}")
    @CrossOrigin
    public boolean RecycleBin(@PathVariable int id){
        return newsSer.recycleBin(id);
    }
}
