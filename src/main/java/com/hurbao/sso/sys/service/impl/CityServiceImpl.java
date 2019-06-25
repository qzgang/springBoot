package com.hurbao.sso.sys.service.impl;

import com.hurbao.sso.sys.domain.CityInfoDomain;
import com.hurbao.sso.sys.domain.CountryDomain;
import com.hurbao.sso.sys.domain.ProvinceDomain;
import com.hurbao.sso.sys.entity.City;
import com.hurbao.sso.sys.dao.CityDao;
import com.hurbao.sso.sys.entity.Province;
import com.hurbao.sso.sys.service.CityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * 城市表 服务实现类
 * </p>
 * @author ${author}
 * @since 2019-06-25
 */
@Service
@Slf4j
public class CityServiceImpl extends ServiceImpl<CityDao, City> implements CityService {
    @Autowired
    CityDao cityDao;
    // province cache
    private Map<String, ProvinceDomain> cache_province_info = null;
    // city cache
    private Map<String, CityInfoDomain> cache_city_info = null;
    private Map<Integer, CityInfoDomain> cache_idcode_provinceAndCity = null;
    // county cache
    private CountryDomain cache_country_info = null;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void initCity() {
        try {
            initCityInfo();
        } catch (Exception e) {
            log.error("city info init=>", e);
        }
    }

    @Override
    public Set<City> selectCitysByProvinceId(String provinceId) {
        if (StringUtils.isBlank(provinceId) || cache_province_info == null) {
            return null;
        }
        try {
            lock.readLock().lock();
            ProvinceDomain loa = cache_province_info.get(provinceId);
            if (loa == null) {
                return null;
            }
            return loa.getCitys();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Set<Province> selectAllProvices() {
        if (cache_country_info == null) {
            return null;
        }
        try {
            lock.readLock().lock();
            return cache_country_info.getProvinces();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Province selectProvinceById(String provinceId) {

        if (StringUtils.isBlank(provinceId) || cache_province_info == null) {
            return null;
        }
        try {
            lock.readLock().lock();
            ProvinceDomain loa = cache_province_info.get(provinceId);
            return loa;
        } finally {
            lock.readLock().unlock();
        }

    }
    /**
     * 根据身份证号取得省份或信息
     * @param idcode
     * @return
     */
    @Override
    public CityInfoDomain getAreaByIdcode(Integer idcode){
        try {
            lock.readLock().lock();
            CityInfoDomain loa = cache_idcode_provinceAndCity.get(idcode);
            if (loa == null) {
                return null;
            }
            try {
                return loa;
            } catch (Exception e) {
                return null;
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    private void initCityInfo() {
        List<CityInfoDomain> cityInfoDomainList = cityDao.selectAllCityInfo();
        try {
            lock.writeLock().lock();
            cache_province_info = new HashMap<String, ProvinceDomain>();
            cache_city_info = new HashMap<String, CityInfoDomain>();
            cache_idcode_provinceAndCity= new HashMap<Integer, CityInfoDomain>();
            cache_country_info = null;
            for (CityInfoDomain cityInfoDomain : cityInfoDomainList) {
                if (cache_city_info.get(cityInfoDomain.getCityId()) == null) {
                    cache_city_info.put(cityInfoDomain.getCityId(),cityInfoDomain);
                }
                if (cache_country_info == null) {
                    cache_country_info = new CountryDomain();
                    cache_country_info.setSjpname(cityInfoDomain.getCjp());
                    cache_country_info.setSname(cityInfoDomain.getCname());
                    cache_country_info.setSpyname(cityInfoDomain.getCpn());
                }
                if(StringUtils.isNotBlank(cityInfoDomain.getPidcode())){
                    //省份身份证号对应的信息
                    cache_idcode_provinceAndCity.put(Integer.parseInt(cityInfoDomain.getPidcode()), cityInfoDomain);
                }
                if(StringUtils.isNotBlank(cityInfoDomain.getCidcode())){
                    //省份身份证号对应的信息
                    cache_idcode_provinceAndCity.put(Integer.parseInt(cityInfoDomain.getCidcode()), cityInfoDomain);
                }

                ProvinceDomain provinceDomain = cache_province_info.get(cityInfoDomain.getProviceId());
                if (provinceDomain == null) {
                    provinceDomain = new ProvinceDomain();
                    provinceDomain.setId(cityInfoDomain.getProviceId());
                    provinceDomain.setIsort(cityInfoDomain.getPsort());
                    provinceDomain.setSname(cityInfoDomain.getPname());
                    provinceDomain.setSidcode(cityInfoDomain.getPidcode());
                    provinceDomain.setSjpname(cityInfoDomain.getPjp());
                    provinceDomain.setSpyname(cityInfoDomain.getPjp());
                    cache_province_info.put(cityInfoDomain.getProviceId(), provinceDomain);
                }

                cache_country_info.putProvince(provinceDomain);
                City city = new City();
                city.setId(cityInfoDomain.getCityId());
                city.setSprovinceid(cityInfoDomain.getProviceId());
                city.setBisprovincecity(cityInfoDomain.getCispc());
                if(cityInfoDomain.getCsort() !=null){
                    city.setIsort(cityInfoDomain.getCsort().longValue());
                }
                city.setSareacode(cityInfoDomain.getCacode());
                city.setSidcode(cityInfoDomain.getCidcode());
                city.setSname(cityInfoDomain.getCname());
                city.setSpyname(cityInfoDomain.getCpn());
                city.setSjpname(cityInfoDomain.getCjp());
                provinceDomain.putCity(city);
            }
        } finally {
            lock.writeLock().unlock();
        }
        log.info("city info load ok !");
    }
}
