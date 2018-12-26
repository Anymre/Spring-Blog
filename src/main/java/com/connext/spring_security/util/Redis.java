package com.connext.spring_security.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Redis {
  @Autowired StringRedisTemplate template;
  final String validsign = "phone:";

  private void setData(String phone, String count) {
    template.opsForValue().set(validsign + phone, count, 120, TimeUnit.SECONDS);
  }

  public boolean validPhone(String phone) {
    String validPhone = template.opsForValue().get(validsign + phone);
    if (validPhone == null || validPhone.equals("")) {
      // 如果不存在就添加
      this.setData(phone, "1");
      return true;
    }
    int count = Integer.parseInt(validPhone);
    if (count < 3) {
      this.setData(phone, String.valueOf(count + 1));
      return true;
    } else {
      return false;
    }
  }

  public void setCode(String phone,String verifyCode) {
    template.opsForValue().set(phone, verifyCode, 60, TimeUnit.SECONDS);
  }

  public void getCode(String phone) {
    // 存入redis
    String oldcode=template.opsForValue().get(phone);
    if (oldcode == null) {
      String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
      setCode(phone,verifyCode);
    }
    log.info(template.opsForValue().get(phone));
  }
  public boolean vaildCode(String phone,String verifyCode){
    if(template.opsForValue().get(phone)==verifyCode||template.opsForValue().get(phone).equals(verifyCode)) {return true;}
    else{return false;}
  }
}
