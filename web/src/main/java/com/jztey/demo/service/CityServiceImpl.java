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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
    public Long updateCity(@PathVariable Long id, @RequestBody City city) {
        Long ret = cityMapper.updateCity(city, id);

        // 缓存存在，删除缓存
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + city.toString());
        }

        return ret;


    }

    @Override
    public Long deleteCity(@PathVariable Long id) {
        Long ret = cityMapper.deleteCity(id);

        // 缓存存在，删除缓存
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.deleteCity() : 从缓存中删除城市 ID >> " + id);
        }
        return ret;
    }


    /**
     * 注解的方法实现redis的缓存
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "citys", key = "'user_'+#id")
    public City findCityByIdZj(@PathVariable Long id) {
        LOGGER.info("无缓存的时候调用这里");
        // 从 DB 中获取城市信息
        City city = cityMapper.findCityById(id);
        return city;
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