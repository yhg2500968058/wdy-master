package com.wdy.biz.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.biz.service.TbSellerService;
import com.wdy.commons.core.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: yanghongguang
 * Date: 2020/3/17
 * Time: 18:10
 * Description:
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SellerLongController extends BaseController {

    @Reference(version = "1.0.0")
    private TbSellerService tbSellerService;

  //  @Autowired
  //  private IAuthenticationFacade authenticationFacade;


    /*@GetMapping("/sellerInfo")
    public Wrapper<TbSeller> sellerInfo() {

       Authentication authentication = authenticationFacade.getAuthentication();

       if (authentication != null){
           if (authentication instanceof AnonymousAuthenticationToken) {
               System.out.println(authentication.getPrincipal());
               return null;
           }

           if (authentication instanceof UsernamePasswordAuthenticationToken) {
               System.out.println(authentication.getPrincipal());
               SellerUserInfo name = (SellerUserInfo) authentication.getPrincipal();
               TbSeller byLoginName = this.tbSellerService.findByLoginName(name.getUsername());
               return WrapMapper.ok(byLoginName);
           }
       }
       return null;
    }*/
}
