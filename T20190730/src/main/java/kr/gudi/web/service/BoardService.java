package kr.gudi.web.service;

import org.springframework.stereotype.Service;

@Service
public class BoardService implements BoardServiceInterface {

	@Override
	public void callData(String key) {
		System.out.println("callData : " + key);
	}

}
