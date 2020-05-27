package com.wdy.seckill.web.limit;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

/**
 * User: yanghongguang
 * Date: 2020/3/31
 * Time: 14:55
 * Description:
 */
@Component
public class AccessLimitService {

    final RateLimiter rateLimiter = RateLimiter.create(50.0);

    public boolean tryAcquire(){
        return rateLimiter.tryAcquire();
    }
}
