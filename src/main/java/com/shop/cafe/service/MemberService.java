package com.shop.cafe.service;

import com.shop.cafe.dao.LoginDao;
import com.shop.cafe.dao.UserMapper;
import com.shop.cafe.dto.Login;
import com.shop.cafe.dto.User;
import com.shop.cafe.util.OpenCrypt;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private LoginDao loginDao;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 사용자 등록 (비밀번호 암호화 후 DB 저장)
    public void registerUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userMapper.insertUser(user);
    }

    // 로그인 검증 및 토큰 생성
    public Login tokenLogin(User user) throws Exception {
        // 이메일로 사용자 정보 조회
        user = userMapper.getUserByEmail(user.getEmail());

        if (user != null) {
            String name = user.getName();  // 실제 이름을 가져옵니다.
            if (name != null && !name.trim().equals("")) {
                String email = user.getEmail();

                // 1. salt를 생성한다
                String salt = UUID.randomUUID().toString();

                // 2. 이메일을 해싱한다
                byte[] originalHash = OpenCrypt.getSHA256(email, salt);

                // 3. db에 저장하기 좋은 포맷으로 인코딩한다
                String myToken = OpenCrypt.byteArrayToHex(originalHash);
                System.out.println("myToken: " + myToken);

                // 4. login 테이블에 token 저장
                Login loginInfo = new Login(name, email, myToken, null);
                loginDao.insertToken(loginInfo);  // DB에 토큰 삽입

                return loginInfo;  // 로그인 정보 반환
            }
        }
        return null;
    }

    // 이메일로 사용자 정보 조회
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    // 로그아웃
    public void logout(String authorization) throws Exception {
		loginDao.deleteToken(authorization);
		
	}

    
}
