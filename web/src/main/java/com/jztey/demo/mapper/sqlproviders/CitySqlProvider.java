/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: CitySqlProvider
 * Author:   yushi
 * Date:     2018/4/19 15:26
 * Description: 城市的具体dao实现
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jztey.demo.mapper.sqlproviders;

import com.jztey.demo.domain.City;

/**
 * 〈一句话功能简述〉<br>
 * 〈城市的具体dao实现〉
 *
 * @author yushi
 * @create 2018/4/19
 * @since 1.0.0
 */
public class CitySqlProvider {
    public String findCityById(Long id) {
        StringBuffer sql = new StringBuffer();
        sql.append("select id as id,province_id as provinceId, city_name as cityName,description as description from city where 1=1 ");
        if (id > 0) {
            sql.append(" and id=" + id);
        }
        return sql.toString();
    }
}


