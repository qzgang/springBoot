package com.hurbao.sso.sys.domain;

import com.hurbao.sso.sys.entity.Country;
import com.hurbao.sso.sys.entity.Province;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author Hunter
 *
 */
public class CountryDomain extends Country {
	private Set<Province> provinces = new TreeSet<Province>(new ProComparator());

	public void putProvince(Province pro) {
		if (pro == null) {
			return;
		}
		Iterator<Province> iterator = provinces.iterator();
		boolean hasProvince = false;
		while (iterator.hasNext()) {
			Province province = iterator.next();
			if (pro.getId().equals(province.getId())) {
				hasProvince = true;
				break;
			}
		}

		if (!hasProvince) {
			provinces.add(pro);
		}
	}
	
	

	public Set<Province> getProvinces() {
		return provinces;
	}



	class ProComparator implements Comparator<Province> {

		@Override
		public int compare(Province o1, Province o2) {
			if (o1.getIsort() == null || o2.getIsort() == null) {
				return -1;
			}
			if (o1.getIsort() > o2.getIsort()) {
				return 1;
			} else {
				return -1;
			}
		}

	}
}
