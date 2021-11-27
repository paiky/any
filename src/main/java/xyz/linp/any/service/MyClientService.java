package xyz.linp.any.service;

import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Request;
import com.dtflys.forest.callback.OnLoadCookie;
import com.dtflys.forest.callback.OnSaveCookie;
import com.dtflys.forest.http.ForestResponse;
import org.springframework.stereotype.Service;

/**
 * @author linp
 * @date 2021/11/23 21:14
 * @Description
 */
@Service
public interface MyClientService {
    /**
     * 注册
     * @param email
     * @param name
     * @param passwd
     * @param repasswd
     * @param code
     * @return
     */
    @Post("https://www.jafiyun.icu/auth/register")
    ForestResponse register(@Body("email") String email,
                    @Body("name") String name,
                    @Body("passwd") String passwd,
                    @Body("repasswd") String repasswd,
                    @Body("code") String code
    );

    /**
     * 登录获取cookie
     * @param email
     * @param passwd
     * @param onSaveCookie
     * @return
     */
    @Post("https://www.jafiyun.icu/auth/login")
    ForestResponse login(@Body("email") String email, @Body("passwd") String passwd, OnSaveCookie onSaveCookie);

    /**
     * 带cookie进入用户页面
     * @param onLoadCookie
     * @return
     */
    @Get("https://www.jafiyun.icu/user")
    ForestResponse user(OnLoadCookie onLoadCookie);

    /**
     * 签到
     * @param onLoadCookie
     * @return
     */
    @Post("https://www.jafiyun.icu/user/checkin")
    ForestResponse checkin(OnLoadCookie onLoadCookie);
}
