package angel.rest;

import angel.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author hongql
 *
 * @Date Created by blm on 26/10/17.
 * @Description 描述
 */
@RestController
@RequestMapping("/accountant/v1/base")
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String test() {
        baseService.findAll();
        return "hello world";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return null;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout() {
        return null;
    }
}
