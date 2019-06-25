package com.hurbao.sso.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hurbao.sso.sys.domain.CityInfoDomain;
import com.hurbao.sso.sys.entity.City;
import com.hurbao.sso.sys.entity.Province;

import java.util.Set;

/**
 * <p>
 * 城市表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-25
 */
public interface CityService extends IService<City> {

    /**
     * 根据地区查询城市
     * @param provinceId
     * @return
     */
    Set<City> selectCitysByProvinceId(String provinceId);

    /**
     * 查询所有地区
     * @return
     */
    Set<Province> selectAllProvices();


    /**
     * 根据地区ID查询地区
     * @return
     */
    Province selectProvinceById(String provinceId);


    /**
     * 根据身份证号取得省份或信息
     * @param idcode
     * @return
     */
    public CityInfoDomain getAreaByIdcode(Integer idcode);
}
