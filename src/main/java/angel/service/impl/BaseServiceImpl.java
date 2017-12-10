package angel.service.impl;

import angel.model.mapper.base.BaseMapper;
import angel.service.BaseService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author hongql
 *
 * @Date Created by blm on 26/10/17.
 * @Description 描述
 */
@Service
public class BaseServiceImpl implements BaseService{

    @Autowired
    private BaseMapper baseMapper;

    @Override
    public JsonObject findAll() {
        List<String> results = baseMapper.findAll();
        System.out.println(results);
        return null;
    }
}
