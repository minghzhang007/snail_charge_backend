/**
 *
 */
package com.netease.yuedu.snail.biz.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ContentProvider implements Serializable {

    private static final long serialVersionUID = -7347496392822428710L;
    /**
     * 作者用户Id
     */
    private Long userId;
    /**
     * 作者名(昵称/笔名/机构名称)
     */
    private String name;
    /**
     * 是否个人身份 0-企业身份 1-个人授权
     */
    private Integer isPersonal;
    /**
     * 在线联系地址
     */
    private String contactAddr;
    /**
     * 账户状态 -1-冻结，0-正常
     */
    private Integer status;
    /**
     * 认证材料的DFS Id
     */
    private Long credentialId;
    /**
     * 认证材料文件名
     */
    private String credentialName;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 用户资料修改时间
     */
    private Long updateTime;
    /**
     * 真实姓名/公司名称
     */
    private String realName;
    /**
     * 出版图书购买附赠二维码屏蔽, 0:否1:是
     */
    private Integer dimensionalCodeFilter;
    /**
     * 合作标记,0:合作中, -1:非合作
     */
    private Integer cooperateFlag;
    /**
     * 过期时间
     */
    private Long expireTime;


}
