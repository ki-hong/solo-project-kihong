package com.codestates.soloprojectkihong.api.v1.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private Character sex;
    private String companyName;
    private String companyType;
    private String companyLocation;
    private LocalDateTime createdAt;

    public enum MemberStatus{
        PRE_AUTH(1,"인증 전"),
        AFTER_AUTH(2,"인증완료");

        @Getter
        private int stepNum;
        private String description;

        MemberStatus(int stepNum, String description) {
            this.stepNum = stepNum;
            this.description = description;
        }
    }

    @Builder
    public Member(Long id, String name, String password, Character sex, String companyName, String companyType, String companyLocation) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.companyName = companyName;
        this.companyType = companyType;
        this.companyLocation = companyLocation;
    }
}
