package com.codestates.soloprojectkihong.api.v1.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Post{

        private String name;
        private String password;
        private Character sex;
        private String companyName;
        private String companyType;
        private String companyLocation;

        @Builder
        public Post(String name, String password, Character sex, String companyName, String companyType, String companyLocation) {
            this.name = name;
            this.password = password;
            this.sex = sex;
            this.companyName = companyName;
            this.companyType = companyType;
            this.companyLocation = companyLocation;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ResponseDto{
        private String name;
        private Character sex;
        private String companyName;
        private String companyType;
        private String companyLocation;

        @Builder
        public ResponseDto(String name, Character sex, String companyName, String companyType, String companyLocation) {
            this.name = name;
            this.sex = sex;
            this.companyName = companyName;
            this.companyType = companyType;
            this.companyLocation = companyLocation;
        }
    }

}
