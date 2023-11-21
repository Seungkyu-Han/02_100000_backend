package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Config.Jwt.JwtTokenProvider;
import Hackerton.Backend.Data.Dto.Auth.Res.AuthGetLoginRes;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Data.Enum.RoleEnum;
import Hackerton.Backend.Repository.UserRepository;
import Hackerton.Backend.Service.AuthService;
import Hackerton.Backend.Util.HashUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${kakao.auth.client_id}")
    private String client_id;

    @Value("${kakao.auth.redirect_uri}")
    private String redirect_uri;

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity<AuthGetLoginRes> login(String code) throws IOException {
        return getKakaoUserIdByKakaoAccessToken(getKakaoAccessToken(code));
    }

    @Override
    public ResponseEntity<AuthGetLoginRes> login(Authentication authentication) {
        User user = userRepository.findById(Integer.valueOf(authentication.getName()));

        if(user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        String accessToken = jwtTokenProvider.createAccessToken(user.getId());
        return new ResponseEntity<>(new AuthGetLoginRes(accessToken, user.getRefreshToken()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> logout(Authentication authentication){

        User user = userRepository.findById(Integer.valueOf(authentication.getName()));

        if(user == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        user.setRefreshToken(null);

        userRepository.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<AuthGetLoginRes> getKakaoUserIdByKakaoAccessToken(String kakaoAccessToken) throws IOException {
        JsonElement element = getJsonElementByAccessToken(kakaoAccessToken);

        int id = element.getAsJsonObject().get("id").getAsInt();

        User user = userRepository.findById(id);

        if(user == null)
            return register(id);

        String accessToken = jwtTokenProvider.createAccessToken(id);
        String refreshToken = jwtTokenProvider.createRefreshToken(id);

        user.setRefreshToken(refreshToken);

        userRepository.update(user);

        return new ResponseEntity<>(new AuthGetLoginRes(accessToken, refreshToken), HttpStatus.OK);
    }

    private ResponseEntity<AuthGetLoginRes> register(Integer id){

        String accessToken = jwtTokenProvider.createAccessToken(id);
        String refreshToken = jwtTokenProvider.createRefreshToken(id);

        User user = User.builder()
                .id(id)
                .name(HashUtil.tashName(id))
                .role(RoleEnum.USER)
                .refreshToken(refreshToken)
                .build();

        userRepository.save(user);

        return new ResponseEntity<>(new AuthGetLoginRes(accessToken, refreshToken), HttpStatus.CREATED);
    }

    private JsonElement getJsonElementByAccessToken(String token) throws IOException {
        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        URL url = new URL(reqUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + token);

        return getJsonElement(httpURLConnection);
    }

    private String getKakaoAccessToken(String code) throws IOException {
        String access_token;
        String reqURL = "https://kauth.kakao.com/oauth/token";

        URL url = new URL(reqURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
        String stringBuilder = "grant_type=authorization_code" +
                "&client_id=" + client_id +
                "&redirect_uri=" + redirect_uri +
                "&code=" + code;
        bufferedWriter.write(stringBuilder);
        bufferedWriter.flush();


        httpURLConnection.getResponseCode();

        JsonElement element = getJsonElement(httpURLConnection);

        access_token = element.getAsJsonObject().get("access_token").getAsString();

        bufferedWriter.close();

        return access_token;
    }

    private JsonElement getJsonElement(HttpURLConnection httpURLConnection) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String line;
        StringBuilder result = new StringBuilder();

        while((line = bufferedReader.readLine()) != null){
            result.append(line);
        }

        bufferedReader.close();

        return JsonParser.parseString(result.toString());
    }
}
