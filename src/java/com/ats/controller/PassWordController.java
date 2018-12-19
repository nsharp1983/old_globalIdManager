package com.ats.controller;


import com.ats.aempi.util.SecurityUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PassWordController {
    private static final byte[] KEY = {9, -1, 0, 5, 39, 8, 6, 19};
    protected Log logger = LogFactory.getLog(getClass());

    /**
     * 加密
     * @param response
     */
    @RequestMapping("/passWord/encryptPass.do")
    public void encryptPass(HttpServletResponse response,String passWord) {
        try {
        String encrypt = SecurityUtil.encryptDes(passWord, KEY);
            response.getWriter().print(encrypt);
        } catch (Exception e) {
            logger.error("加密失败", e);
        }
    }

    /**
     * 解密
     * @param response
     */
    @RequestMapping("/passWord/decryptPass.do")
    public void decryptPass(HttpServletResponse response,String passWord) {
        try {
            String decrypt = SecurityUtil.decryptDes(passWord, KEY);
            response.getWriter().print(decrypt);
        } catch (Exception e) {
            logger.error("解密失败", e);
        }
    }



}
