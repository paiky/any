package xyz.linp.any.controller;

import com.dtflys.forest.http.ForestCookie;
import com.dtflys.forest.http.ForestResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.linp.any.service.MyClientService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author linp
 * @date 2021/11/23 21:13
 * @Description
 */
@RestController
@RequestMapping("/vpn")
public class MyClientController {

    @Autowired
    private MyClientService myClientService;

    private static String email;
    private static String name;
    private static String passwd;
    private static String repasswd;
    private static final String code = "gifP";

    private static List<ForestCookie> allCookies = new ArrayList<>();

    @RequestMapping("/craw")
    public void craw() {
        ForestResponse register = myClientService.register(email, name, passwd, repasswd, code);
        System.out.println("注册成功=============>" + register.getContent());

        ForestResponse login = myClientService.login(email, passwd, ((forestRequest, forestCookies) -> {
            allCookies.addAll(forestCookies.allCookies());
        }));
        System.out.println("获得cookies================>" + allCookies);

        ForestResponse user = myClientService.user((forestRequest, needCookies) -> {
            needCookies.addAllCookies(allCookies);
        });
        System.out.println("登录状态===============>" + user.getStatusCode());

        ForestResponse checkin = myClientService.checkin((forestRequest, needCookies) -> {
            needCookies.addAllCookies(allCookies);
        });
        System.out.println("签到结果===========>" + checkin.getContent());

//        System.out.println("===========================请求结果================================");
//        System.out.println(user.getContent());

        Document document = Jsoup.parse(user.getContent());
        Elements elements = document.getElementsByClass("btn-v2ray");
        if(!elements.isEmpty()){
            String subscription = elements.get(0).attr("data-clipboard-text");
            System.out.println("v2ray==================>" + subscription);
        }
    }

    @PostConstruct
    public void init(){
        String uuid = UUID.randomUUID().toString().substring(0, 10);
        email = uuid + "@gmail.com";
        name = uuid;
        passwd = uuid;
        repasswd = uuid;
    }
}
