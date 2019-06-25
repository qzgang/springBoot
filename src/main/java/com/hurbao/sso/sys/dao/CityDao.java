package com.hurbao.sso.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hurbao.sso.sys.domain.CityInfoDomain;
import com.hurbao.sso.sys.entity.City;

import java.util.List;

/**
 * <p>
 * 城市表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-06-25
 */
public interface CityDao extends BaseMapper<City> {
    List<CityInfoDomain> selectAllCityInfo();
}
