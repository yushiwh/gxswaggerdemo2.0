package com.jztey.demo.mapper;


import com.jztey.demo.domain.City;
import com.jztey.demo.mapper.sqlproviders.CitySqlProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface CityMapper {

    /**
     * 自定义查询
     *
     * @param id
     * @return
     */
    @SelectProvider(type = CitySqlProvider.class, method = "findCityById")
    City findCityById(Long id);


    /**
     * //直接注解方式查询
     *
     * @param id
     * @return
     */
    @Select("select id as id,province_id as provinceId, city_name as cityName,description as description from city where 1=1 and id=#{id}")
    City findCityById_Zj(Long id);


    @Insert("INSERT INTO `city` (`province_id`, `city_name`, `description`) VALUES (#{provinceId}, #{cityName}, #{description});")
    Long saveCity(City city);


}
