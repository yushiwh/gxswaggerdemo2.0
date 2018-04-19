/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: CityServiceImpl
 * Author:   yushi
 * Date:     2018/4/19 15:16
 * Description: 用mybatis
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jztey.demo.service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.jztey.demo.domain.City;
import com.jztey.demo.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;


/**
 * 〈一句话功能简述〉<br>
 * 〈用mybatis3来实现〉
 *
 * @author yushi
 * @create 2018/4/19
 * @since 1.0.0
 */
@Named
@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Inject
    CityMapper cityMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CacheTemplate<City> cacheTemplate;

    @Override
    public City findCityById(@PathVariable Long id) {
        return cityMapper.findCityById(id);
    }

    @Override
    public City findCityById_Zj(@PathVariable Long id) {
        return cityMapper.findCityById_Zj(id);
    }

    @Override
    public City findCityByIdByJGS(@PathVariable Long id) {
        // 设置Key值
        String queryKey = "city_" + id;
        City result = cacheTemplate.getCache(queryKey, 60, TimeUnit.SECONDS, null, new CacheLoadable<City>() {

            @Override
            public City load() {
                City city = cityMapper.findCityById(id);
                return city;
            }

        });
        return result;
    }

    @Override
    public Long saveCity(City city) {
        return cityMapper.saveCity(city);
    }

    @Override
    public Long updateCity(Long id, City city) {
        return null;
    }

    @Override
    public Long deleteCity(Long id) {
        return null;
    }

    @Override
    public City findCityByIdZj(Long id) {
        return null;
    }

    @Override
    public City findCityByIdZjOther(Long id) {
        return null;
    }

    @Override
    public Long updateCityZj(Long id, City city) {
        return null;
    }

    @Override
    public Long deleteCityZj(Long id) {
        return null;
    }

    @Override
    public String getcache(Long id) {
        return null;
    }
}