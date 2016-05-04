package net.shopin.active.service.impl;

import java.util.List;

import net.shopin.active.mapper.firstMapper;
import net.shopin.active.model.first;
import net.shopin.active.service.IfirstService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("IfirstService")
public class firstServiceImpl implements IfirstService{

	@Autowired
	private firstMapper first;
	
	public String first() {
		List<first> firstList = first.select();
		for(first first:firstList){
			System.out.println(first.msg);
		}
		
		return null;
	}

}
